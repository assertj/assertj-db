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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.type;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.AssertProvider;
import org.assertj.db.api.Assertions;
import org.assertj.db.api.TableAssert;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.lettercase.LetterCase;
import org.assertj.db.util.NameComparator;

/**
 * A table in the database to read to get the values.
 * <p>
 * The different information of the table are  name of the table and optionally the columns to check and to exclude.
 * A table should be constructed by the fluent builder {@link Table.Builder} from a AssertDbConnection instance.
 * </p>
 * <p>
 * Examples of instantiation :
 * </p>
 * <ul>
 * <li>
 * <p>
 * This {@link Table} point to a table called {@code movie} in a H2 database in memory.
 * </p>
 *
 * <pre>
 * <code class='java'>
 * AssertDbConnection connection = AssertDbConnectionFactory.of(&quot;jdbc:h2:mem:test&quot;, &quot;sa&quot;, &quot;&quot;).create();
 * Table table = connection.table(&quot;movie&quot;).build();
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
 * The {@link AssertDbConnection} use a {@code DataSource} instead of a JDBC url like above.
 * </p>
 *
 * <pre>
 * <code class='java'>
 * DataSource dataSource = ...;
 * AssertDbConnection connection = AssertDbConnectionFactory.of(dataSource).create();
 * Table table1 = connection.table(&quot;song&quot;).columnsToCheck(new String[] { &quot;number&quot;, &quot;title&quot; }).build();
 * Table table2 = connection..table(&quot;musician&quot;).columnsToExclude(new String[] { &quot;birthday&quot; }).build();
 * </code>
 * </pre>
 *
 * </li>
 * </ul>
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Table extends AbstractDbData<Table> implements AssertProvider<TableAssert> {

  /**
   * The name of the table.
   */
  private String name;
  /**
   * The list of columns of the table.
   */
  private List<String> columnsList;
  /**
   * The columns to check.
   */
  private String[] columnsToCheck;
  /**
   * The columns to exclude.
   */
  private String[] columnsToExclude;
  /**
   * The columns to order.
   *
   * @since 1.2.0
   */
  private Order[] columnsToOrder;
  /**
   * Start delimiter for column name and table name.
   *
   * @since 1.2.0
   */
  private Character startDelimiter = null;
  /**
   * End delimiter for column name and table name.
   *
   * @since 1.2.0
   */
  private Character endDelimiter = null;


  /**
   * Constructor with a {@link ConnectionProvider}, the name of the table and the columns to check and to exclude.
   *
   * @param connectionProvider {@link ConnectionProvider} to connect to the database.
   * @param name               Name of the table.
   * @param startDelimiter     Start delimiter for column name and table name.
   * @param endDelimiter       End delimiter for column name and table name.
   * @param columnsToOrder     List of column to use as ORDER BY
   * @param columnsToCheck     Array of the name of the columns to check. If {@code null} that means to check all the
   *                           columns.
   * @param columnsToExclude   Array of the name of the columns to exclude. If {@code null} that means to exclude no
   *                           column.
   * @since 3.0.0
   */
  private Table(ConnectionProvider connectionProvider, String name, Character startDelimiter, Character endDelimiter, Order[] columnsToOrder,
                String[] columnsToCheck, String[] columnsToExclude) {
    super(Table.class, DataType.TABLE, connectionProvider);
    setName(name);
    setStartDelimiter(startDelimiter);
    setEndDelimiter(endDelimiter);
    setColumnsToOrder(columnsToOrder);
    setColumnsToCheck(columnsToCheck);
    setColumnsToExclude(columnsToExclude);
  }

  /**
   * Only used for tests.
   */
  private Table() {
    super(Table.class, DataType.TABLE);
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
  private Table setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("name can not be null");
    }
    this.name = name;
    setNameFromDb();
    return this;
  }

  /**
   * Set the name from the corresponding name in the database.
   */
  private void setNameFromDb() {
    if (name != null && this.getConnectionProvider() != null) {

      LetterCase tableLetterCase = getTableLetterCase();
      LetterCase columnLetterCase = getColumnLetterCase();
      SchemaMetadata metaData = getMetaData();
      for (String tableName : metaData.getTablesName()) {
        if (tableLetterCase.isEqual(tableName, name)) {
          name = tableLetterCase.convert(tableName);
          break;
        }
      }

      columnsList = new ArrayList<>();
      for (String column : metaData.getColumnsName(name)) {
        columnsList.add(columnLetterCase.convert(column));
      }
    }
  }

  /**
   * Returns the columns to check (which are present in {@link AbstractDbData#getColumnsNameList()}).
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
   * Sets the columns to check (which are present in {@link AbstractDbData#getColumnsNameList()}).
   *
   * @param columnsToCheck Array of the name of the columns to check. If {@code null} that means to check all the
   *                       columns.
   * @return The actual instance.
   * @throws NullPointerException If one of the name in {@code columnsToCheck} is {@code null}.
   * @see #getColumnsToCheck()
   */
  private Table setColumnsToCheck(String[] columnsToCheck) {
    if (columnsToCheck == null) {
      this.columnsToCheck = null;
      return this;
    }

    if (columnsList == null) {
      throw new AssertJDBException("The table name and the connectionProvider must be set first");
    }

    LetterCase letterCase = getColumnLetterCase();
    // If the parameter is not null, all the names are convert
    // before setting the instance field
    List<String> columnsToCheckList = new ArrayList<>();
    for (String column : columnsToCheck) {
      if (column == null) {
        throw new NullPointerException("The name of the column can not be null");
      }
      int indexOf = NameComparator.INSTANCE.indexOf(columnsList, column, letterCase);
      if (indexOf != -1) {
        columnsToCheckList.add(columnsList.get(indexOf));
      }
    }
    this.columnsToCheck = columnsToCheckList.toArray(new String[0]);
    return this;
  }

  /**
   * Returns the columns to exclude (which are not present in {@link AbstractDbData#getColumnsNameList()}).
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
   * Sets the columns to exclude (which are not present in {@link AbstractDbData#getColumnsNameList()}).
   *
   * @param columnsToExclude The columns.
   * @return The actual instance.
   * @see #getColumnsToExclude()
   */
  private Table setColumnsToExclude(String[] columnsToExclude) {
    if (columnsToExclude == null) {
      this.columnsToExclude = null;
      return this;
    }

    if (columnsList == null) {
      throw new AssertJDBException("The table name and the connectionProvider must be set first");
    }

    LetterCase letterCase = getColumnLetterCase();
    this.columnsToExclude = new String[columnsToExclude.length];
    List<String> columnsToExcludeList = new ArrayList<>();
    for (String column : columnsToExclude) {
      if (column == null) {
        throw new NullPointerException("The name of the column can not be null");
      }
      int indexOf = NameComparator.INSTANCE.indexOf(columnsList, column, letterCase);
      if (indexOf != -1) {
        columnsToExcludeList.add(columnsList.get(indexOf));
      }
    }
    this.columnsToExclude = columnsToExcludeList.toArray(new String[0]);
    return this;
  }

  /**
   * Returns the columns to order (which are used in {@code ORDER BY}).
   *
   * @return Array of the name of the columns to order. If {@code null} that means not to do order.
   * @see #setColumnsToOrder(Order[])
   */
  public Order[] getColumnsToOrder() {
    if (columnsToOrder == null) {
      return null;
    }
    return columnsToOrder.clone();
  }

  /**
   * Sets the columns to order (which are used in {@code ORDER BY}).
   *
   * @param columnsToOrder The columns.
   * @return The actual instance.
   * @see #getColumnsToOrder()
   */
  private Table setColumnsToOrder(Order[] columnsToOrder) {
    if (columnsToOrder == null) {
      this.columnsToOrder = null;
      return this;
    }

    if (columnsList == null) {
      throw new AssertJDBException("The table name and the connectionProvider must be set first");
    }

    LetterCase letterCase = getColumnLetterCase();
    this.columnsToOrder = new Order[columnsToOrder.length];
    List<Order> columnsToOrderList = new ArrayList<>();
    for (Order order : columnsToOrder) {
      if (order == null) {
        throw new NullPointerException("The order can not be null");
      }
      String column = order.getName();
      if (column == null) {
        throw new NullPointerException("The name of the column for order can not be null");
      }
      int indexOf = NameComparator.INSTANCE.indexOf(columnsList, column, letterCase);
      if (indexOf != -1) {
        String columnName = columnsList.get(indexOf);
        columnsToOrderList.add(Order.getOrder(columnName, order.getType()));
      }
    }
    this.columnsToOrder = columnsToOrderList.toArray(new Order[0]);
    return this;
  }

  /**
   * Returns the start delimiter for column name and table name.
   *
   * @return The start delimiter for column name and table name.
   * @see #setStartDelimiter(Character)
   * @since 1.2.0
   */
  public Character getStartDelimiter() {
    return startDelimiter;
  }

  /**
   * Sets the start delimiter for column name and table name.
   *
   * @param startDelimiter The start delimiter for column name and table name.
   * @return The actual instance.
   * @see #getStartDelimiter()
   * @since 1.2.0
   */
  private Table setStartDelimiter(Character startDelimiter) {
    this.startDelimiter = startDelimiter;
    return this;
  }

  /**
   * Returns the end delimiter for column name and table name.
   *
   * @return The end delimiter for column name and table name.
   * @see #setEndDelimiter(Character)
   * @since 1.2.0
   */
  public Character getEndDelimiter() {
    return endDelimiter;
  }

  /**
   * Sets the end delimiter for column name and table name.
   *
   * @param endDelimiter The end delimiter for column name and table name.
   * @return The actual instance.
   * @see #getEndDelimiter()
   * @since 1.2.0
   */
  private Table setEndDelimiter(Character endDelimiter) {
    this.endDelimiter = endDelimiter;
    return this;
  }

  /**
   * Encode the column name and table name.
   *
   * @param name The column name or table name.
   *             The encoded column name or table name.
   */
  private String encode(String name) {
    StringBuilder stringBuilder = new StringBuilder();
    if (startDelimiter != null) {
      stringBuilder.append(startDelimiter);
    }
    stringBuilder.append(name);
    if (endDelimiter != null) {
      stringBuilder.append(endDelimiter);
    }
    return stringBuilder.toString();
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
        stringBuilder.append(encode(column));
      }
    }
    stringBuilder.append(" FROM ");
    stringBuilder.append(encode(name));
    if (columnsToOrder != null) {
      for (int index = 0; index < columnsToOrder.length; index++) {
        if (index == 0) {
          stringBuilder.append(" ORDER BY ");
        } else {
          stringBuilder.append(", ");
        }
        stringBuilder.append(encode(columnsToOrder[index].getName()));
        if (columnsToOrder[index].getType() == Order.OrderType.DESC) {
          stringBuilder.append(" DESC");
        }
      }
    }
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
   */
  private void collectPrimaryKeyName() {
    List<String> pksNameList = new ArrayList<>();
    SchemaMetadata metaData = getMetaData();

    String tableName = name;
    LetterCase letterCase = getTableLetterCase();
    for (String tableResult : metaData.getTablesName()) {
      if (letterCase.isEqual(tableName, tableResult)) {
        tableName = tableResult;
        break;
      }
    }

    LetterCase pkLetterCase = getPrimaryKeyLetterCase();
    for (String primaryKey : metaData.getPrimaryKeys(tableName)) {
      if (NameComparator.INSTANCE.contains(getColumnsNameList(), primaryKey, pkLetterCase)) {
        String pkName = pkLetterCase.convert(primaryKey);
        pksNameList.add(pkName);
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
    collectPrimaryKeyName();
    if (columnsToOrder == null) {
      sortRows();
    }
  }

  /**
   * Fluent {@link Table} builder.
   * Use {@link AssertDbConnection} to construct new instance of this builder.
   * <pre>
   * <code class='java'>
   * AssertDbConnection connection = ....;
   * Table table = connection.table(&quot;movie&quot;).build();
   * Table table2 = connection.table(&quot;movie&quot;).columnToCheck(new String[] { &quot;number&quot;, &quot;title&quot; }).build();
   * </code>
   * </pre>
   *
   * @author Julien Roy
   * @since 3.0.0
   */
  public static class Builder {
    private final ConnectionProvider connectionProvider;
    private final String name;
    private Order[] columnsToOrder;
    private Character startDelimiter;
    private Character endDelimiter;
    private String[] columnsToCheck;
    private String[] columnsToExclude;

    Builder(ConnectionProvider connectionProvider, String name) {
      this.connectionProvider = connectionProvider;
      this.name = name;
    }

    /**
     * Set order to use in SQL queries.
     *
     * @param columnsToOrder List of column to use as ORDER BY.
     * @return Current builder instance.
     */
    public Builder columnsToOrder(Order[] columnsToOrder) {
      this.columnsToOrder = columnsToOrder;
      return this;
    }

    /**
     * Set delimiters to use in SQL queries.
     *
     * @param startDelimiter Start delimiter for column name and table name.
     * @param endDelimiter   End delimiter for column name and table name.
     * @return Current builder instance
     */
    public Builder delimiters(Character startDelimiter, Character endDelimiter) {
      this.startDelimiter = startDelimiter;
      this.endDelimiter = endDelimiter;
      return this;
    }

    /**
     * Set the columns to check.
     *
     * @param columnsToCheck Array of the name of the columns to check. If {@code null} that means to check all the columns.
     * @return Current builder instance.
     */
    public Builder columnsToCheck(String[] columnsToCheck) {
      this.columnsToCheck = columnsToCheck;
      return this;
    }

    /**
     * Set the columns to exclude.
     *
     * @param columnsToExclude Array of the name of the columns to exclude. If {@code null} that means to exclude no column.
     * @return Current builder instance.
     */
    public Builder columnsToExclude(String[] columnsToExclude) {
      this.columnsToExclude = columnsToExclude;
      return this;
    }

    /**
     * Build the Table instance.
     *
     * @return Table instance to use in assertThat.
     */
    public Table build() {
      return new Table(this.connectionProvider, this.name, this.startDelimiter, this.endDelimiter, this.columnsToOrder, this.columnsToCheck, this.columnsToExclude);
    }
  }

  /**
   * Indicates an order with the name on which is the order and the type.
   *
   * @see org.assertj.db.type.Table.Order.OrderType
   */
  public static class Order {
    /**
     * The name of the order.
     */
    private final String name;
    /**
     * The type of the order.
     */
    private final OrderType type;

    /**
     * Constructor.
     *
     * @param name The name of the order.
     * @param type The type of the order.
     */
    private Order(String name, OrderType type) {
      this.name = name;
      this.type = type;
    }

    /**
     * Builds an ascending order.
     *
     * @param name The name of the order.
     * @return An ascending order.
     */
    public static Order asc(String name) {
      return getOrder(name, OrderType.ASC);
    }

    /**
     * Builds the descending order.
     *
     * @param name The name of the order.
     * @return The descending order.
     */
    public static Order desc(String name) {
      return getOrder(name, OrderType.DESC);
    }

    /**
     * Builds an order.
     *
     * @param name The name of the order.
     * @param type The type of the order.
     * @return The instantiated order.
     */
    private static Order getOrder(String name, OrderType type) {
      return new Order(name, type);
    }

    /**
     * Returns the name of the order.
     *
     * @return The name of the order.
     */
    public String getName() {
      return name;
    }

    /**
     * Returns the type of the order.
     *
     * @return The type of the order.
     */
    public OrderType getType() {
      return type;
    }

    @Override
    public int hashCode() {
      int hash = 7;
      hash = 31 * hash + (type == null ? 0 : type.hashCode());
      hash = 31 * hash + (name == null ? 0 : name.hashCode());
      return hash;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Order) {
        Order order = (Order) obj;
        if (order.type == type) {
          return (name == null && order.name == null) ||
            (name != null && name.equals(order.name));
        }
      }
      return false;
    }

    /**
     * Enumeration of the type of order.
     */
    public enum OrderType {
      /**
       * Ascending order.
       */
      ASC,
      /**
       * Descending order.
       */
      DESC
    }
  }

  /**
   * Makes both AssertJ Core and AssertJ DB assertions able to coexist in the same class with {@link org.assertj.core.api.Assertions org.assertj.core.api.Assertions.assertThat} as the only import necessary.<br/><br/>
   * Also works for both <code>BDDAssertions.then</code> static methods from AssertJ Core and AssertJ DB.<br/><br/>
   * 
   * @see org.assertj.core.api.Assertions#assertThat(AssertProvider) &lt;T&gt; org.assertj.core.api.Assertions.assertThat(AssertProvider&lt;T&gt; component) 
   * @see org.assertj.core.api.BDDAssertions#then(AssertProvider) &lt;T&gt; org.assertj.core.api.BDDAssertions.then(AssertProvider&lt;T&gt; component)
   */
  @Override
  public TableAssert assertThat() {
    return Assertions.assertThat(this);
  }
}
