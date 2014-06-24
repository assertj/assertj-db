package org.assertj.db.type;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

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
 * Source source = new Source(&quot;jdbc:h2:mem:test&quot;, &quot;sa&quot;, &quot;&quot;);
 * Table table = new Table(source, &quot;movie&quot;);
 * </pre>
 * 
 * </li>
 * <li>
 * <p>
 * Below the {@link Table} {@code table1} point to a table called {@code song} (but only on the columns called
 * {@code number} and {@code title}).<br/>
 * And the {@link Table} {@code table2} point to a table called {@code musician} (but ignore on the column called
 * {@code birthday}).<br/>
 * The {@link Table} use a {@code DataSource} instead of a {@link Source} like above.
 * </p>
 * 
 * <pre>
 * DataSource dataSource = ...;
 * Table table1 = new Table(dataSource, &quot;song&quot;, new String[] { &quot;number&quot;, &quot;title&quot; }, null);
 * Table table2 = new Table(dataSource, &quot;musician&quot;, null, new String[] { &quot;birthday&quot; });
 * </pre>
 * 
 * </li>
 * </ul>
 * 
 * @author Régis Pouiller
 * 
 */
public class Table extends AbstractDbDatas<Table> {

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
    // empty
  }

  /**
   * Constructor with a {@link Source} and the name of the table.
   * 
   * @param source {@link Source} to connect to the database.
   * @param name Name of the table.
   */
  public Table(Source source, String name) {
    this(source, name, null, null);
  }

  /**
   * Constructor with a {@link Source}, the name of the table and the columns to check and to exclude.
   * 
   * @param source {@link Source} to connect to the database.
   * @param name Name of the table.
   * @param columnsToCheck Array of the name of the columns to check. If {@code null} that means to check all the
   *          columns.
   * @param columnsToExclude Array of the name of the columns to exclude. If {@code null} that means to exclude no
   *          column.
   */
  public Table(Source source, String name, String[] columnsToCheck, String[] columnsToExclude) {
    super(source);
    setName(name);
    setColumnsToCheck(columnsToCheck);
    setColumnsToExclude(columnsToExclude);
  }

  /**
   * Constructor with a dataSource and the name of the table.
   * 
   * @param dataSource DataSource of the database.
   * @param name Name of the table.
   */
  public Table(DataSource dataSource, String name) {
    this(dataSource, name, null, null);
  }

  /**
   * Constructor with a connection, the name of the table and the columns to check and to exclude.
   * 
   * @param dataSource DataSource of the database.
   * @param name Name of the table.
   * @param columnsToCheck Array of the name of the columns to check. If {@code null} that means to check all the
   *          columns.
   * @param columnsToExclude Array of the name of the columns to exclude. If {@code null} that means to exclude no
   *          column.
   */
  public Table(DataSource dataSource, String name, String[] columnsToCheck, String[] columnsToExclude) {
    super(dataSource);
    setName(name);
    setColumnsToCheck(columnsToCheck);
    setColumnsToExclude(columnsToExclude);
  }

  /**
   * Return the name of the table.
   * 
   * @see #setName(String)
   * @return the name of the table.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the table.
   * 
   * @see #getName()
   * @param name The name of the table.
   * @return The actual instance.
   */
  public Table setName(String name) {
    if (name == null) {
      throw new NullPointerException("name can not be null");
    }
    this.name = name;
    return this;
  }

  /**
   * Returns the columns to check (which are present in {@link AbstractDbDatas#getColumnsNameList()}.
   * 
   * @see #setColumnsToCheck(String[])
   * @return Array of the name of the columns to check. If {@code null} that means to check all the columns.
   */
  public String[] getColumnsToCheck() {
    return columnsToCheck;
  }

  /**
   * Sets the columns to check (which are present in {@link AbstractDbDatas#getColumnsNameList()}.
   * 
   * @see #getColumnsToCheck()
   * @param columnsToCheck Array of the name of the columns to check. If {@code null} that means to check all the
   *          columns.
   * @return The actual instance.
   * @throws NullPointerException If one of the name in {@code columnsToCheck} is {@code null}.
   */
  public Table setColumnsToCheck(String[] columnsToCheck) {
    if (columnsToCheck != null) {
      // If the parameter is not null, all the names are transformed to get upper case
      // before setting the instance field
      this.columnsToCheck = new String[columnsToCheck.length];
      for (int index = 0; index < columnsToCheck.length; index++) {
        String column = columnsToCheck[index];
        if (column == null) {
          throw new NullPointerException("The name of the column can not be null");
        }
        this.columnsToCheck[index] = column.toUpperCase();
      }
    } else {
      this.columnsToCheck = null;
    }
    return this;
  }

  /**
   * Returns the columns to exclude (which are not present in {@link AbstractDbDatas#getColumnsNameList()}.
   * 
   * @see #setColumnsToExclude(String[])
   * @return The columns.
   */
  public String[] getColumnsToExclude() {
    return columnsToExclude;
  }

  /**
   * Sets the columns to exclude (which are not present in {@link AbstractDbDatas#getColumnsNameList()}.
   * 
   * @see #getColumnsToExclude()
   * @param columnsToExclude The columns.
   * @return The actual instance.
   */
  public Table setColumnsToExclude(String[] columnsToExclude) {
    if (columnsToExclude != null) {
      this.columnsToExclude = new String[columnsToExclude.length];
      for (int index = 0; index < columnsToExclude.length; index++) {
        String column = columnsToExclude[index];
        if (column == null) {
          throw new NullPointerException("The name of the column can not be null");
        }
        this.columnsToExclude[index] = column.toUpperCase();
      }
    } else {
      this.columnsToExclude = null;
    }
    return this;
  }

  /**
   * Returns the SQL request.
   * 
   * @see AbstractDbDatas#getRequest()
   * @return The SQL request.
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
    String request = stringBuilder.toString();
    return request;
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
    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
    List<String> columnsNameList = new ArrayList<String>();
    List<String> columnsToExcludeList = null;
    if (columnsToExclude != null) {
      columnsToExcludeList = Arrays.asList(columnsToExclude);
    }

    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
      String columnName = resultSetMetaData.getColumnName(i).toUpperCase();
      if (columnsToExcludeList == null || !columnsToExcludeList.contains(columnName)) {

        columnsNameList.add(columnName);
      }
    }
    setColumnsNameList(columnsNameList);
  }

  /**
   * Specific implementation of the loading for a {@code Table}.
   * 
   * @see AbstractDbDatas#loadImpl(Connection)
   * @param connection {@link Connection} to the database provided by {@link AbstractDbDatas#load()} private method.
   * @throws SQLException SQL Exception.
   */
  @Override
  protected void loadImpl(Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    try {
      ResultSet resultSet = statement.executeQuery(getRequest());
      try {
        collectColumnsNameFromResultSet(resultSet);
        collectRowsFromResultSet(resultSet);
      } finally {
        resultSet.close();
      }
    } finally {
      statement.close();
    }
  }
}
