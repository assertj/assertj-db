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

import org.assertj.db.type.lettercase.LetterCase;
import org.assertj.db.util.NameComparator;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A table in the database to read to get the values.
 * <p>
 * The different informations of the table are {@link Source} or {@link DataSource}, name of the table and optionally
 * the columns to check and to exclude.
 * </p>
 * <p>
 * Examples of instantiation :
 * </p>
 * <ul>
 * <li>
 * <p>
 * This {@link Table} point to a table called {@code movie} in a H2 database in memory like indicated in the
 * {@link Source}.
 * </p>
 *
 * <pre>
 * <code class='java'>
 * Source source = new Source(&quot;jdbc:h2:mem:test&quot;, &quot;sa&quot;, &quot;&quot;);
 * Table table = new Table(source, &quot;movie&quot;);
 * </code>
 * </pre>
 *
 * </li>
 * <li>
 * <p>
 * Below the {@link Table} {@code table1} point to a table called {@code song} (but only on the columns called
 * {@code number} and {@code title}).<br>
 * And the {@link Table} {@code table2} point to a table called {@code musician} (but ignore on the column called
 * {@code birthday}).<br>
 * The {@link Table} use a {@code DataSource} instead of a {@link Source} like above.
 * </p>
 *
 * <pre>
 * <code class='java'>
 * DataSource dataSource = ...;
 * Table table1 = new Table(dataSource, &quot;song&quot;, new String[] { &quot;number&quot;, &quot;title&quot; }, null);
 * Table table2 = new Table(dataSource, &quot;musician&quot;, null, new String[] { &quot;birthday&quot; });
 * </code>
 * </pre>
 *
 * </li>
 * </ul>
 *
 * @author Régis Pouiller
 */
public class Table extends AbstractDbData<Table> {

  /**
   * The name of the table.
   */
  private String name;
  /**
   * The columns to check.
   */
  private String[] columnsToCheck;
  /**
   * The columns to exclude.
   */
  private String[] columnsToExclude;

  /**
   * Default constructor.
   */
  public Table() {
    super(Table.class, DataType.TABLE);
  }

  /**
   * Constructor with a {@link Source} and the name of the table.
   *
   * @param source {@link Source} to connect to the database.
   * @param name   Name of the table.
   */
  public Table(Source source, String name) {
    this(source, name, null, null);
  }

  /**
   * Constructor with a {@link Source}, the name of the table and the columns to check and to exclude.
   *
   * @param source           {@link Source} to connect to the database.
   * @param name             Name of the table.
   * @param columnsToCheck   Array of the name of the columns to check. If {@code null} that means to check all the
   *                         columns.
   * @param columnsToExclude Array of the name of the columns to exclude. If {@code null} that means to exclude no
   *                         column.
   */
  public Table(Source source, String name, String[] columnsToCheck, String[] columnsToExclude) {
    super(Table.class, DataType.TABLE, source);
    setName(name);
    setColumnsToCheck(columnsToCheck);
    setColumnsToExclude(columnsToExclude);
  }

  /**
   * Constructor with a dataSource and the name of the table.
   *
   * @param dataSource DataSource of the database.
   * @param name       Name of the table.
   */
  public Table(DataSource dataSource, String name) {
    this(dataSource, name, null, null);
  }

  /**
   * Constructor with a connection, the name of the table and the columns to check and to exclude.
   *
   * @param dataSource       DataSource of the database.
   * @param name             Name of the table.
   * @param columnsToCheck   Array of the name of the columns to check. If {@code null} that means to check all the
   *                         columns.
   * @param columnsToExclude Array of the name of the columns to exclude. If {@code null} that means to exclude no
   *                         column.
   */
  public Table(DataSource dataSource, String name, String[] columnsToCheck, String[] columnsToExclude) {
    super(Table.class, DataType.TABLE, dataSource);
    setName(name);
    setColumnsToCheck(columnsToCheck);
    setColumnsToExclude(columnsToExclude);
  }

  /**
   * Return the name of the table.
   *
   * @return the name of the table.
   * @see #setName(String)
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the table.
   *
   * @param name The name of the table.
   * @return The actual instance.
   * @see #getName()
   */
  public Table setName(String name) {
    if (name == null) {
      throw new NullPointerException("name can not be null");
    }
    this.name = getTableLetterCase().convert(name);
    return this;
  }

  /**
   * Returns the columns to check (which are present in {@link AbstractDbData#getColumnsNameList()}.
   *
   * @return Array of the name of the columns to check. If {@code null} that means to check all the columns.
   * @see #setColumnsToCheck(String[])
   */
  public String[] getColumnsToCheck() {
    if (columnsToCheck == null) {
      return null;
    }
    return columnsToCheck.clone();
  }

  /**
   * Sets the columns to check (which are present in {@link AbstractDbData#getColumnsNameList()}.
   *
   * @param columnsToCheck Array of the name of the columns to check. If {@code null} that means to check all the
   *                       columns.
   * @return The actual instance.
   * @throws NullPointerException If one of the name in {@code columnsToCheck} is {@code null}.
   * @see #getColumnsToCheck()
   */
  public Table setColumnsToCheck(String[] columnsToCheck) {
    if (columnsToCheck != null) {
      LetterCase letterCase = getColumnLetterCase();
      // If the parameter is not null, all the names are convert
      // before setting the instance field
      this.columnsToCheck = new String[columnsToCheck.length];
      for (int index = 0; index < columnsToCheck.length; index++) {
        String column = columnsToCheck[index];
        if (column == null) {
          throw new NullPointerException("The name of the column can not be null");
        }
        this.columnsToCheck[index] = letterCase.convert(column);
      }
    } else {
      this.columnsToCheck = null;
    }
    return this;
  }

  /**
   * Returns the columns to exclude (which are not present in {@link AbstractDbData#getColumnsNameList()}.
   *
   * @return The columns.
   * @see #setColumnsToExclude(String[])
   */
  public String[] getColumnsToExclude() {
    if (columnsToExclude == null) {
      return null;
    }
    return columnsToExclude.clone();
  }

  /**
   * Sets the columns to exclude (which are not present in {@link AbstractDbData#getColumnsNameList()}.
   *
   * @param columnsToExclude The columns.
   * @return The actual instance.
   * @see #getColumnsToExclude()
   */
  public Table setColumnsToExclude(String[] columnsToExclude) {
    if (columnsToExclude != null) {
      LetterCase letterCase = getColumnLetterCase();
      this.columnsToExclude = new String[columnsToExclude.length];
      for (int index = 0; index < columnsToExclude.length; index++) {
        String column = columnsToExclude[index];
        if (column == null) {
          throw new NullPointerException("The name of the column can not be null");
        }
        this.columnsToExclude[index] = letterCase.convert(column);
      }
    } else {
      this.columnsToExclude = null;
    }
    return this;
  }

  /**
   * Returns the SQL request.
   *
   * @return The SQL request.
   * @throws NullPointerException If the {@link #name} field is {@code null}.
   * @see AbstractDbData#getRequest()
   */
  public String getRequest() {
    if (name == null) {
      throw new NullPointerException("name can not be null");
    }

    // Get the request about the name of the table and the columns to check
    StringBuilder stringBuilder = new StringBuilder("SELECT ");
    if (columnsToCheck == null) {
      stringBuilder.append("*");
    } else {
      for (String column : columnsToCheck) {
        if (stringBuilder.length() > 7) {
          stringBuilder.append(", ");
        }
        stringBuilder.append(column);
      }
    }
    stringBuilder.append(" FROM ");
    stringBuilder.append(name);
    return stringBuilder.toString();
  }

  /**
   * Collects the columns name from the {@code ResultSet} on the table.
   * <p>
   * This method use the {@link ResultSetMetaData} from the <code>resultSet</code> parameter to list the name of the
   * columns. But the columns to exclude are not collected.
   * </p>
   *
   * @param resultSet The {@code ResultSet}.
   * @throws SQLException SQL Exception.
   */
  private void collectColumnsNameFromResultSet(ResultSet resultSet) throws SQLException {
    LetterCase letterCase = getColumnLetterCase();
    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
    List<String> columnsNameList = new ArrayList<>();
    List<String> columnsToExcludeList = null;
    if (columnsToExclude != null) {
      columnsToExcludeList = Arrays.asList(columnsToExclude);
    }

    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
      String columnName = letterCase.convert(resultSetMetaData.getColumnLabel(i));
      if (columnsToExcludeList == null
          || !NameComparator.INSTANCE.contains(columnsToExcludeList, columnName, letterCase)) {

        columnsNameList.add(columnName);
      }
    }
    setColumnsNameList(columnsNameList);
  }

  /**
   * Collects the primary key name from the {@code Connection} to the database.
   * <p>
   * This method use the {@link DatabaseMetaData} from the {@code Connection} parameter to list the primary keys of the
   * table.
   * </p>
   *
   * @param connection The {@code Connection} to the database.
   * @throws SQLException SQL Exception.
   */
  private void collectPrimaryKeyName(Connection connection) throws SQLException {
    String catalog = getCatalog(connection);
    String schema = getSchema(connection);
    List<String> pksNameList = new ArrayList<>();
    DatabaseMetaData metaData = connection.getMetaData();

    String tableName = name;
    try (ResultSet resultSet = metaData.getTables(catalog, schema, null, new String[] { "TABLE" })) {
      LetterCase letterCase = getTableLetterCase();
      while (resultSet.next()) {
        String tableResult = resultSet.getString("TABLE_NAME");
        if (letterCase.isEqual(tableName, tableResult)) {
          tableName = tableResult;
          break;
        }
      }
    }

    try (ResultSet resultSet = metaData.getPrimaryKeys(catalog, schema, tableName)) {
      LetterCase letterCase = getPrimaryKeyLetterCase();
      while (resultSet.next()) {
        String columnName = letterCase.convert(resultSet.getString("COLUMN_NAME"));
        String pkName = letterCase.convert(columnName);
        if (NameComparator.INSTANCE.contains(getColumnsNameList(), pkName, letterCase)) {
          pksNameList.add(pkName);
        }
      }
    }
    setPksNameList(pksNameList);
  }

  /**
   * Specific implementation of the loading for a {@code Table}.
   *
   * @param connection {@link Connection} to the database provided by {@link AbstractDbData#load()} private method.
   * @throws NullPointerException If the {@link #name} field is {@code null}.
   * @throws SQLException         SQL Exception.
   * @see AbstractDbData#loadImpl(Connection)
   */
  @Override
  protected void loadImpl(Connection connection) throws SQLException {
    if (name == null) {
      throw new NullPointerException("name can not be null");
    }

    try (Statement statement = connection.createStatement()) {
      try (ResultSet resultSet = statement.executeQuery(getRequest())) {
        collectColumnsNameFromResultSet(resultSet);
        collectRowsFromResultSet(resultSet);
      }
    }
    collectPrimaryKeyName(connection);
    sortRows();
  }
}
