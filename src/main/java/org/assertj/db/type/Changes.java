/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.type;

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.util.ChangeComparator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.assertj.db.type.Change.*;

/**
 * Changes in the database.
 * 
 * @author Régis Pouiller
 * 
 */
public class Changes extends AbstractDbElement<Changes> {

  /**
   * The list of the tables.
   */
  private List<Table> tablesList;
  /**
   * The list of the tables at start point.
   */
  private List<Table> tablesAtStartPointList;
  /**
   * The list of the tables at end point.
   */
  private List<Table> tablesAtEndPointList;
  /**
   * The request.
   */
  private Request request;
  /**
   * The request at start point.
   */
  private Request requestAtStartPoint;
  /**
   * The request at end point.
   */
  private Request requestAtEndPoint;
  /**
   * The list of the changes.
   */
  private List<Change> changesList;

  /**
   * Constructor.
   */
  public Changes() {
    super(Changes.class);
  }

  /**
   * Constructor.
   * 
   * @param source The {@link Source} to connect to the database (must be not {@code null}).
   * @throws NullPointerException If {@code source} is {@code null}.
   */
  public Changes(Source source) {
    super(Changes.class, source);
  }

  /**
   * Constructor.
   * 
   * @param dataSource The {@link DataSource} (must be not {@code null}).
   * @throws NullPointerException If {@code dataSource} is {@code null}.
   */
  public Changes(DataSource dataSource) {
    super(Changes.class, dataSource);
  }

  /**
   * Constructor.
   * 
   * @param tables Table on which are the comparison.
   */
  public Changes(Table... tables) {
    super(Changes.class);
    setTables(tables);
  }

  /**
   * Constructor.
   * 
   * @param request Request on which are the comparison.
   */
  public Changes(Request request) {
    super(Changes.class);
    setRequest(request);
  }

  /**
   * Sets the table on which are the comparison.
   * 
   * @param tables Table on which are the comparison.
   * @return {@code this} actual instance.
   */
  public Changes setTables(Table... tables) {
    request = null;
    requestAtStartPoint = null;
    requestAtEndPoint = null;
    tablesList = new ArrayList<>();
    tablesAtStartPointList = null;
    tablesAtEndPointList = null;
    changesList = null;
    for (Table table : tables) {
      if (table == null) {
        throw new NullPointerException("The tables must be not null");
      }
      Table t = getDuplicatedTable(table);
      tablesList.add(t);
    }
    if (tables.length > 0) {
      copyElement(tables[0], this);
    }
    return myself;
  }

  /**
   * Returns the list of {@link Table}.
   * @return The list of {@link Table}.
   */
  public List<Table> getTablesList() {
    return tablesList;
  }

  /**
   * Sets the {@link Request}.
   * 
   * @param request The {@link Request}.
   * @return {@code this} actual instance.
   */
  public Changes setRequest(Request request) {
    if (request == null) {
      throw new NullPointerException("The request must be not null");
    }
    tablesList = null;
    tablesAtStartPointList = null;
    tablesAtEndPointList = null;
    this.request = getDuplicatedRequest(request);
    copyElement(request, this);
    requestAtStartPoint = null;
    requestAtEndPoint = null;
    changesList = null;
    return myself;
  }

  /**
   * Returns the {@link Request}.
   * @return The {@link Request}.
   */
  public Request getRequest() {
    return request;
  }

  /**
   * Returns the list of the {@link Table}s at start point.
   * 
   * @return The list of the {@link Table}s at start point.
   * @see Changes#setStartPointNow()
   */
  public List<Table> getTablesAtStartPointList() {
    return tablesAtStartPointList;
  }

  /**
   * Returns the list of the {@link Table}s at end point.
   * 
   * @return The list of the {@link Table}s at end point.
   * @see Changes#setEndPointNow()
   */
  public List<Table> getTablesAtEndPointList() {
    return tablesAtEndPointList;
  }

  /**
   * Returns the {@link Request} at start point.
   * 
   * @return The {@link Request} at start point.
   * @see Changes#setStartPointNow()
   */
  public Request getRequestAtStartPoint() {
    return requestAtStartPoint;
  }

  /**
   * Returns the {@link Request} at end point.
   * 
   * @return The {@link Request} at end point.
   * @see Changes#setEndPointNow()
   */
  public Request getRequestAtEndPoint() {
    return requestAtEndPoint;
  }

  /**
   * Copy a {@link AbstractDbElement} in parameter on another.
   * 
   * @param elementToCopy The {@link AbstractDbElement} to copy
   * @param element The {@link AbstractDbElement} on which is the copy
   */
  private static void copyElement(AbstractDbElement<?> elementToCopy, AbstractDbElement<?> element) {
    if (elementToCopy.getSource() != null) {
      element.setSource(elementToCopy.getSource());
    }
    if (elementToCopy.getDataSource() != null) {
      element.setDataSource(elementToCopy.getDataSource());
    }
  }

  /**
   * Duplicate the {@link Request} in parameter and returns it.
   * 
   * @param request The {@link Request} to duplicate
   * @return The Duplication
   */
  private static Request getDuplicatedRequest(Request request) {
    Request r = new Request();
    copyElement(request, r);
    return r.setLetterCases(request.getTableLetterCase(),
                            request.getColumnLetterCase(),
                            request.getPrimaryKeyLetterCase())
            .setRequest(request.getRequest())
            .setParameters(request.getParameters())
            .setPksName(request.getPksNameList().toArray(new String[0]));
  }

  /**
   * Duplicate the {@link Table} in parameter and returns it.
   * 
   * @param table The {@link Table} to duplicate
   * @return The Duplication
   */
  private static Table getDuplicatedTable(Table table) {
    Table t = new Table();
    copyElement(table, t);
    return t.setLetterCases(table.getTableLetterCase(),
                            table.getColumnLetterCase(),
                            table.getPrimaryKeyLetterCase())
            .setName(table.getName())
            .setStartDelimiter(table.getStartDelimiter())
            .setEndDelimiter(table.getEndDelimiter())
            .setColumnsToCheck(table.getColumnsToCheck())
            .setColumnsToExclude(table.getColumnsToExclude())
            .setColumnsToOrder(table.getColumnsToOrder());
  }

  /**
   * Sets the start point for comparison.
   * 
   * @return {@code this} actual instance.
   */
  public Changes setStartPointNow() {
    if (request == null && tablesList == null) {
      try (Connection connection = getConnection()) {
        tablesList = new LinkedList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(getCatalog(connection), getSchema(connection), null,
            new String[] { "TABLE" });
        while (resultSet.next()) {
          String tableName = resultSet.getString("TABLE_NAME");
          Table t = new Table().setLetterCases(getTableLetterCase(), getColumnLetterCase(), getPrimaryKeyLetterCase())
                               .setName(getTableLetterCase().convert(tableName));
          copyElement(this, t);
          tablesList.add(t);
        }
      } catch (SQLException e) {
        throw new AssertJDBException(e);
      }
    }

    if (request != null) {
      tablesAtStartPointList = null;
      requestAtStartPoint = getDuplicatedRequest(request);
      requestAtStartPoint.getRowsList();
    } else {
      requestAtStartPoint = null;
      tablesAtStartPointList = new LinkedList<>();
      for (Table table : tablesList) {
        Table t = getDuplicatedTable(table);
        t.getRowsList();
        tablesAtStartPointList.add(t);
      }
    }
    tablesAtEndPointList = null;
    requestAtEndPoint = null;
    changesList = null;

    return myself;
  }

  /**
   * Sets the end point for comparison.
   * 
   * @return {@code this} actual instance.
   * @throws AssertJDBException If the start point is not set
   */
  public Changes setEndPointNow() {
    if (requestAtStartPoint == null && tablesAtStartPointList == null) {
      throw new AssertJDBException("Start point must be set before");
    }

    if (requestAtStartPoint != null) {
      requestAtEndPoint = getDuplicatedRequest(request);
      requestAtEndPoint.getRowsList();
    } else {
      tablesAtEndPointList = new LinkedList<>();
      for (Table table : tablesList) {
        Table t = getDuplicatedTable(table);
        t.getRowsList();
        tablesAtEndPointList.add(t);
      }
    }
    changesList = null;

    return myself;
  }

  /**
   * Returns the list of changes for the data when there have primary keys.
   * 
   * @param dataName The name of the data.
   * @param dataAtStartPoint The data at start point.
   * @param dataAtEndPoint The data at end point.
   * @return The list of changes for the data.
   */
  private List<Change> getChangesListWithPks(String dataName, AbstractDbData<?> dataAtStartPoint,
      AbstractDbData<?> dataAtEndPoint) {

    List<Change> changesListWithPks = new ArrayList<>();

    // List the created rows : the row is not present at the start point
    for (Row row : dataAtEndPoint.getRowsList()) {
      Row rowAtStartPoint = dataAtStartPoint.getRowFromPksValues(row.getPksValues());
      if (rowAtStartPoint == null) {
        Change change = createCreationChange(dataAtEndPoint.getDataType(), dataName, row,
                                             getTableLetterCase(), getColumnLetterCase(), getPrimaryKeyLetterCase());
        changesListWithPks.add(change);
      }
    }
    for (Row row : dataAtStartPoint.getRowsList()) {
      Row rowAtEndPoint = dataAtEndPoint.getRowFromPksValues(row.getPksValues());
      if (rowAtEndPoint == null) {
        // List the deleted rows : the row is not present at the end point
        Change change = createDeletionChange(dataAtStartPoint.getDataType(), dataName, row,
                                             getTableLetterCase(), getColumnLetterCase(), getPrimaryKeyLetterCase());
        changesListWithPks.add(change);
      } else {
        // List the modified rows
        if (!row.hasValues(rowAtEndPoint)) {
          // If at least one value in the rows is different, add the change
          Change change = createModificationChange(dataAtStartPoint.getDataType(), dataName, row, rowAtEndPoint,
                                                   getTableLetterCase(), getColumnLetterCase(), getPrimaryKeyLetterCase());
          changesListWithPks.add(change);
        }
      }
    }

    return changesListWithPks;
  }

  /**
   * Returns the list of changes for the data when there is no primary key.
   * 
   * @param dataName The name of the data.
   * @param dataAtStartPoint The data at start point.
   * @param dataAtEndPoint The data at end point.
   * @return The list of changes for the data.
   */
  private List<Change> getChangesListWithoutPks(String dataName, AbstractDbData<?> dataAtStartPoint,
      AbstractDbData<?> dataAtEndPoint) {

    List<Change> changesListWithoutPks = new ArrayList<>();

    // List the created rows : the row is not present at the start point
    List<Row> rowsAtStartPointList = new ArrayList<>(dataAtStartPoint.getRowsList());
    for (Row rowAtEndPoint : dataAtEndPoint.getRowsList()) {
      int index = -1;
      int index1 = 0;
      for (Row rowAtStartPoint : rowsAtStartPointList) {
        if (rowAtEndPoint.hasValues(rowAtStartPoint)) {
          index = index1;
          break;
        }
        index1++;
      }
      if (index == -1) {
        Change change = createCreationChange(dataAtStartPoint.getDataType(), dataName, rowAtEndPoint,
                                             getTableLetterCase(), getColumnLetterCase(), getPrimaryKeyLetterCase());
        changesListWithoutPks.add(change);
      } else {
        rowsAtStartPointList.remove(index);
      }
    }
    // List the deleted rows : the row is not present at the end point
    List<Row> rowsAtEndPointList = new ArrayList<>(dataAtEndPoint.getRowsList());
    for (Row rowAtStartPoint : dataAtStartPoint.getRowsList()) {
      int index = -1;
      int index1 = 0;
      for (Row rowAtEndPoint : rowsAtEndPointList) {
        if (rowAtStartPoint.hasValues(rowAtEndPoint)) {
          index = index1;
          break;
        }
        index1++;
      }
      if (index == -1) {
        Change change = createDeletionChange(dataAtStartPoint.getDataType(), dataName, rowAtStartPoint,
                                             getTableLetterCase(), getColumnLetterCase(), getPrimaryKeyLetterCase());
        changesListWithoutPks.add(change);
      } else {
        rowsAtEndPointList.remove(index);
      }
    }

    return changesListWithoutPks;
  }

  /**
   * Returns the list of changes for the data.
   * 
   * @param dataName The name of the data.
   * @param dataAtStartPoint The data at start point.
   * @param dataAtEndPoint The data at end point.
   * @return The list of changes for the data.
   */
  private List<Change> getChangesList(String dataName, AbstractDbData<?> dataAtStartPoint,
      AbstractDbData<?> dataAtEndPoint) {

    if (dataAtStartPoint.getPksNameList().isEmpty()) {
      return getChangesListWithoutPks(dataName, dataAtStartPoint, dataAtEndPoint);
    } else {
      return getChangesListWithPks(dataName, dataAtStartPoint, dataAtEndPoint);
    }
  }

  /**
   * Returns the list of the changes.
   * 
   * @return The list of the changes.
   * @throws AssertJDBException If the changes are on all the tables and if the number of tables change between the
   *           start point and the end point. It is normally impossible.
   */
  public List<Change> getChangesList() {
    if (changesList == null) {
      if (requestAtEndPoint == null && tablesAtEndPointList == null) {
        throw new AssertJDBException("End point must be set before");
      }

      if (requestAtEndPoint != null) {
        changesList = getChangesList(requestAtStartPoint.getRequest(), requestAtStartPoint, requestAtEndPoint);
      } else {
        changesList = new ArrayList<>();
        Iterator<Table> iteratorAtStartPoint = tablesAtStartPointList.iterator();
        Iterator<Table> iteratorAtEndPoint = tablesAtEndPointList.iterator();
        while (iteratorAtStartPoint.hasNext()) {
          Table tableAtStartPoint = iteratorAtStartPoint.next();
          Table tableAtEndPoint = iteratorAtEndPoint.next();
          changesList.addAll(getChangesList(tableAtStartPoint.getName(), tableAtStartPoint, tableAtEndPoint));
        }
      }
    }

    changesList.sort(ChangeComparator.INSTANCE);
    return changesList;
  }

  /**
   * Returns {@code Changes} only on the table name in parameter.
   * @param tableName The table name
   * @return {@code Changes} instance.
   */
  public Changes getChangesOfTable(String tableName) {
    if (tableName == null) {
      throw new NullPointerException("tableName must be not null");
    }
    Changes changes = createChangesFromThis();
    List<Change> changesListOfTable = getChangesList();
    if (tablesList != null) {
      for (Change change : changesListOfTable) {
        if (getTableLetterCase().isEqual(tableName, change.getDataName())) {
          changes.changesList.add(change);
        }
      }
    }
    return changes;
  }

  /**
   * Returns {@code Changes} only on the change type in parameter.
   * @param changeType The change type
   * @return {@code Changes} instance.
   */
  public Changes getChangesOfType(ChangeType changeType) {
    if (changeType == null) {
      throw new NullPointerException("changeType must be not null");
    }
    Changes changes = createChangesFromThis();
    List<Change> changesListOfType = getChangesList();
    for (Change change : changesListOfType) {
      if (changeType.equals(change.getChangeType())) {
        changes.changesList.add(change);
      }
    }
    return changes;
  }

  /**
   * Creates a new instance of {@code Changes} from {@code this} one.
   * @return The new instance.
   */
  private Changes createChangesFromThis() {
    Changes changes = new Changes();
    if (request != null) {
      changes.request = getDuplicatedRequest(request);
    }
    if (tablesList != null) {
      changes.tablesList = new ArrayList<>();
      for (Table table : tablesList) {
        changes.tablesList.add(getDuplicatedTable(table));
      }
    }
    changes.changesList = new ArrayList<>();
    return changes;
  }
}
