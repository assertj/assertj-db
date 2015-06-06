/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.type;

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.util.RowComparator;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * This class represents data from the database (either a {@link Table} or a {@link Request}).
 * <p>
 * That could be data from a {@link Table} or from a {@link Request}.<br>
 * So this class contains the list of columns name ({@link #getColumnsNameList()}),
 * the list of primary keys name ({@link #getPksNameList()})
 * and the list of the rows ({@link #getRowsList()}).
 * The first call to one of these methods triggers a loading from the database.
 * </p>
 * 
 * @author Régis Pouiller
 * 
 * @param <D> Class of the subclass (an implementation of {@link AbstractDbData}) : useful for the fluent methods
 *          (setters).
 */
public abstract class AbstractDbData<D extends AbstractDbData<D>> extends AbstractDbElement<D> {

  /**
   * The type of the date on which is the change.
   */
  private final DataType dataType;
  /**
   * List of the column names.
   */
  private List<String> columnsNameList;
  /**
   * List of the primary key names.
   */
  private List<String> pksNameList;
  /**
   * List of the rows.
   */
  private List<Row> rowsList;
  /**
   * Map the columns with their index in key (contains the columns already generated).
   */
  private final Map<Integer, Column> columnsMap = new HashMap<>();

  /**
   * Default constructor.
   * 
   * @param dataType The type of the data on which is the change.
   * @param selfType Class of this element : a sub-class of {@code AbstractDbData}.
   */
  AbstractDbData(Class<D> selfType, DataType dataType) {
    super(selfType);
    this.dataType = dataType;
  }

  /**
   * Constructor with a {@link Source}.
   * 
   * @param dataType The type of the data on which is the change.
   * @param selfType Class of this element : a sub-class of {@code AbstractDbData}.
   * @param source The {@link Source} to connect to the database (must be not {@code null}).
   * @throws NullPointerException If {@code source} is {@code null}.
   */
  AbstractDbData(Class<D> selfType, DataType dataType, Source source) {
    super(selfType, source);
    this.dataType = dataType;
  }

  /**
   * Constructor with a {@link DataSource}.
   * 
   * @param dataType The type of the data on which is the change.
   * @param selfType Class of this element : a sub-class of {@code AbstractDbData}.
   * @param dataSource The {@link DataSource} (must be not {@code null}).
   * @throws NullPointerException If {@code dataSource} is {@code null}.
   */
  AbstractDbData(Class<D> selfType, DataType dataType, DataSource dataSource) {
    super(selfType, dataSource);
    this.dataType = dataType;
  }

  /**
   * Returns the type of the data on which is the change.
   * 
   * @return The type of the data on which is the change.
   */
  public DataType getDataType() {
    return dataType;
  }

  /**
   * Returns the SQL request.
   * 
   * @see Table#getRequest()
   * @see Request#getRequest()
   * @return The SQL request.
   */
  public abstract String getRequest();

  /**
   * Loads the informations of the data from the database.
   * <p>
   * This method gets a {@link Connection} and calls {@link AbstractDbData#loadImpl(Connection)} for specific loading
   * depending of being a {@link Table} or a {@link Request}.
   * </p>
   * 
   * @throws NullPointerException If the {@link #dataSource} and {@link #source} fields are {@code null}.
   * @throws AssertJDBException If triggered, this exception wrap a possible {@link SQLException} during the loading.
   */
  private void load() {
    try (Connection connection = getConnection()) {
      // Call the specific loading depending of Table or Request.
      loadImpl(connection);
      Collections.sort(rowsList, RowComparator.INSTANCE);
      if (pksNameList == null) {
        pksNameList = new ArrayList<>();
      }
    } catch (SQLException e) {
      throw new AssertJDBException(e);
    }
  }

  /**
   * Implementation of the loading that depends of the kind of data.
   * <p>
   * In fact it is like in the Skeleton Design Pattern : this method is called by the {@link AbstractDbData#load()}
   * method but {@code loadImpl()} is abstract here and it is implemented in the sub-classes depending of the need of
   * the sub-class.
   * </p>
   * 
   * @see Table#loadImpl(Connection)
   * @see Request#loadImpl(Connection)
   * @param connection {@link Connection} to the database provided by {@link #load()} method.
   * @throws SQLException SQL Exception.
   */
  protected abstract void loadImpl(Connection connection) throws SQLException;

  /**
   * Collects rows from a {@link ResultSet}.
   * <p>
   * This method browse the {@link ResultSet} in parameter to get the data and fill the list of {@link Row} (
   * {@link #rowsList}) with these data.
   * </p>
   * 
   * @param resultSet The {@link ResultSet}.
   * @throws SQLException A SQL Exception.
   */
  protected void collectRowsFromResultSet(ResultSet resultSet) throws SQLException {
    ResultSetMetaData metaData = resultSet.getMetaData();
    rowsList = new ArrayList<>();
    while (resultSet.next()) {
      List<Object> objectsList = new ArrayList<>();
      for (String columnName : columnsNameList) {
        // TODO Improve the check of the type
        int index = -1;
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
          if (columnName.equalsIgnoreCase(metaData.getColumnName(i))) {
            index = i;
          }
        }
        int type = metaData.getColumnType(index + 1);
        switch (type) {
          case Types.DATE:
            objectsList.add(resultSet.getDate(columnName));
            break;
          case Types.TIME:
            objectsList.add(resultSet.getTime(columnName));
            break;
          case Types.TIMESTAMP:
            objectsList.add(resultSet.getTimestamp(columnName));
            break;

          default:
            objectsList.add(resultSet.getObject(columnName));
            break;
        }
      }
      rowsList.add(new Row(pksNameList, columnsNameList, objectsList));
    }
  }

  /**
   * Return the list of the columns name for the data from database.
   * <p>
   * If it is the first call to {@code getColumnsNameList()}, the data are loaded from database by calling the
   * {@link #load()} private method.
   * </p>
   * 
   * @return The list of the columns name.
   * @throws NullPointerException If the {@link #dataSource} and {@link #source} fields are {@code null}.
   * @throws AssertJDBException If triggered, this exception wrap a possible {@link SQLException} during the loading.
   */
  public List<String> getColumnsNameList() {
    if (columnsNameList == null) {
      load();
    }
    return columnsNameList;
  }

  /**
   * Sets the list of the columns name.
   * 
   * @param columnsNameList The list of the columns name.
   */
  protected void setColumnsNameList(List<String> columnsNameList) {
    this.columnsNameList = columnsNameList;
  }

  /**
   * Return the list of the primary key name for the data from database.
   * <p>
   * If it is the first call to {@code getIdsNameList()}, the data are loaded from database by calling the
   * {@link #load()} private method.
   * </p>
   * 
   * @return The list of the primary key name.
   * @throws NullPointerException If the {@link #dataSource} and {@link #source} fields are {@code null}.
   * @throws AssertJDBException If triggered, this exception wrap a possible {@link SQLException} during the loading.
   */
  public List<String> getPksNameList() {
    if (pksNameList == null) {
      load();
    }
    return pksNameList;
  }

  /**
   * Controls that all the primary keys name exist in the columns.
   */
  protected void controlIfAllThePksNameExistInTheColumns() {
    if (pksNameList != null) {
      for (String pkName : pksNameList) {
        // If the list of columns name is not set, the presence of the column is not tested
        if (columnsNameList != null) {
          if (columnsNameList.indexOf(pkName) == -1) {
            throw new AssertJDBException("Primary key %s do not exist in the columns %s", pkName, columnsNameList);
          }
        }
      }
    }
  }

  /**
   * Sets the list of the primary key name.
   * 
   * @param pksNameList The list of the primary keys name.
   * @throws AssertJDBException If one the primary keys do not exist in the columns name, the exception is triggered.
   */
  protected void setPksNameList(List<String> pksNameList) {
    this.pksNameList = new ArrayList<>();
    for (String pkName : pksNameList) {
      String pkNameUp = pkName.toUpperCase();
      this.pksNameList.add(pkNameUp);
    }
    if (rowsList != null) {
      for (Row row : rowsList) {
        row.setPksNameList(this.pksNameList);
      }
    }
    controlIfAllThePksNameExistInTheColumns();
  }

  /**
   * Return the list of the values for the data from database.
   * <p>
   * If it is the first call to {@code getRowsList()}, the data are loaded from database by calling the {@link #load()}
   * private method.
   * </p>
   * 
   * @return The list of the values.
   * @throws NullPointerException If the {@link #dataSource} and {@link #source} fields are {@code null}.
   * @throws AssertJDBException If triggered, this exception wrap a possible {@link SQLException} during the loading.
   */
  public List<Row> getRowsList() {
    if (rowsList == null) {
      load();
    }
    return rowsList;
  }

  /**
   * Returns the column corresponding to the column index in parameter and the values inside the column.
   * <p>
   * This method calls {@link #getColumnsNameList()} and {@link #getValuesList(int)} which calls {@link #getRowsList()}.
   * <br>
   * If it is the first call to {@link #getColumnsNameList()} or {@link #getRowsList()}, the data are loaded from
   * database by calling the {@link #load()} private method.
   * </p>
   * 
   * @param index The column index.
   * @return The column and the values
   * @throws NullPointerException If the {@link #dataSource} and {@link #source} fields are {@code null}.
   * @throws AssertJDBException If triggered, this exception wrap a possible {@link SQLException} during the loading.
   */
  public Column getColumn(int index) {
    if (columnsMap.containsKey(index)) {
      return columnsMap.get(index);
    }
    String name = getColumnsNameList().get(index);
    List<Object> valuesList = getValuesList(index);
    Column column = new Column(name, valuesList);
    columnsMap.put(index, column);
    return column;
  }

  /**
   * Returns the row corresponding to the index.
   * <p>
   * This method calls {@link #getRowsList()}.<br>
   * If it is the first call to {@link #getRowsList()}, the data are loaded from database by calling the {@link #load()}
   * private method.
   * </p>
   * 
   * @param index The index
   * @return The {@link Row}
   * @throws NullPointerException If the {@link #dataSource} and {@link #source} fields are {@code null}.
   * @throws AssertJDBException If triggered, this exception wrap a possible {@link SQLException} during the loading.
   */
  public Row getRow(int index) {
    return getRowsList().get(index);
  }

  /**
   * Returns the values of the column corresponding to the column name.
   * <p>
   * This method calls {@link #getColumnsNameList()} and {@link #getRowsList()}.<br>
   * If it is the first call to {@link #getColumnsNameList()} or {@link #getRowsList()}, the data are loaded from
   * database by calling the {@link #load()} private method.
   * </p>
   * 
   * @param index The column index
   * @return The values
   * @throws NullPointerException If the {@link #dataSource} and {@link #source} fields are {@code null}.
   * @throws AssertJDBException If triggered, this exception wrap a possible {@link SQLException} during the loading.
   */
  private List<Object> getValuesList(int index) {
    List<Object> valuesList = new ArrayList<>();
    for (Row row : getRowsList()) {
      valuesList.add(row.getColumnValue(index));
    }

    return valuesList;
  }

  /**
   * Returns the {@link Row} with the primary keys values in parameter.
   * 
   * @param pksValues The primary keys values.
   * @return The {@link Row} with the same primary keys values.
   */
  public Row getRowFromPksValues(Object... pksValues) {
    for (Row row : getRowsList()) {
      if (row.hasPksValuesEqualTo(pksValues)) {
        return row;
      }
    }
    return null;
  }
}
