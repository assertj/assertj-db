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

import static org.assertj.db.type.Change.createCreationChange;
import static org.assertj.db.type.Change.createDeletionChange;
import static org.assertj.db.type.Change.createModificationChange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import org.assertj.core.api.AssertProvider;
import org.assertj.db.api.Assertions;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.util.ChangeComparator;

/**
 * Changes in the database.
 * <p>
 * A Changes should be constructed by the fluent builder {@link Changes.Builder} from a AssertDbConnection instance.
 * </p>
 * <p>
 * Examples of instantiation :
 * </p>
 * <ul>
 * <li>
 * <p>
 * This {@link Changes} detect all changes in database.
 * </p>
 *
 * <pre>
 * <code class='java'>
 * AssertDbConnection connection = AssertDbConnectionFactory.of(dataSource).create();
 * Changes changes = connection.changes().build();
 * changes.setStartPointNow();
 * ....
 * do some DB updates
 * ....
 * changes.setEndPointNow();
 * assertThat(changes).....
 * </code>
 * </pre>
 *
 * </li>
 * <li>
 * <p>
 * This {@link Changes} detect changes for only two table.
 * </p>
 *
 * <pre>
 * <code class='java'>
 * AssertDbConnection connection = AssertDbConnectionFactory.of(dataSource).create();
 * Changes changes = connection.changes().table(&quot;movie&quot;).table(&quot;song&quot;).build();
 * </code>
 * </pre>
 *
 * </li>
 * <li>
 * <p>
 * This {@link Changes} detect changes for row returned by a SQL query.
 * </p>
 *
 * <pre>
 * <code class='java'>
 * AssertDbConnection connection = AssertDbConnectionFactory.of(dataSource).create();
 * Changes changes = connection.changes().request(&quot;select * from movie;&quot;).build();
 * </code>
 * </pre>
 *
 * </li>
 * </ul>
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Changes extends AbstractDbElement<Changes> implements AssertProvider<ChangesAssert> {

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
   *
   * @param connectionProvider The {@link ConnectionProvider} to connect to the database (must be not {@code null}).
   * @throws NullPointerException If {@code connectionProvider} is {@code null}.
   * @since 3.0.0
   */
  private Changes(ConnectionProvider connectionProvider) {
    super(Changes.class, connectionProvider);
  }

  /**
   * Constructor.
   *
   * @param tables Table on which are the comparison.
   */
  private Changes(ConnectionProvider connectionProvider, Collection<Table> tables) {
    super(Changes.class, connectionProvider);
    setTables(tables);
  }

  /**
   * Constructor.
   *
   * @param request Request on which are the comparison.
   */
  private Changes(ConnectionProvider connectionProvider, Request request) {
    super(Changes.class, connectionProvider);
    setRequest(request);
  }

  /**
   * Only used for tests.
   */
  private Changes() {
    super(Changes.class);
  }

  /**
   * Duplicate the {@link Request} in parameter and returns it.
   *
   * @param request The {@link Request} to duplicate
   * @return The Duplication
   */
  private Request getDuplicatedRequest(Request request) {
    return new Request.Builder(this.getConnectionProvider(), request.getRequest())
      .parameters(request.getParameters())
      .pksName(request.getPksNameList().toArray(new String[0]))
      .build();
  }

  /**
   * Duplicate the {@link Table} in parameter and returns it.
   *
   * @param table The {@link Table} to duplicate
   * @return The Duplication
   */
  private Table getDuplicatedTable(Table table) {
    return new Table.Builder(this.getConnectionProvider(), table.getName())
      .delimiters(table.getStartDelimiter(), table.getEndDelimiter())
      .columnsToCheck(table.getColumnsToCheck())
      .columnsToExclude(table.getColumnsToExclude())
      .columnsToOrder(table.getColumnsToOrder())
      .build();
  }

  /**
   * Sets the table on which are the comparison.
   *
   * @param tables Table on which are the comparison.
   */
  private void setTables(Collection<Table> tables) {
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
  }

  /**
   * Returns the list of {@link Table}.
   *
   * @return The list of {@link Table}.
   */
  public List<Table> getTablesList() {
    return tablesList;
  }

  /**
   * Returns the {@link Request}.
   *
   * @return The {@link Request}.
   */
  public Request getRequest() {
    return request;
  }

  /**
   * Sets the {@link Request}.
   *
   * @param request The {@link Request}.
   */
  private void setRequest(Request request) {
    if (request == null) {
      throw new NullPointerException("The request must be not null");
    }
    tablesList = null;
    tablesAtStartPointList = null;
    tablesAtEndPointList = null;
    this.request = getDuplicatedRequest(request);
    requestAtStartPoint = null;
    requestAtEndPoint = null;
    changesList = null;
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
   * Sets the start point for comparison.
   *
   * @return {@code this} actual instance.
   */
  public Changes setStartPointNow() {
    if (request == null && tablesList == null) {
      tablesList = new LinkedList<>();
      for (String tableName : getMetaData().getTablesName()) {
        tablesList.add(new Table.Builder(this.getConnectionProvider(), getTableLetterCase().convert(tableName)).build());
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
   * @param dataName         The name of the data.
   * @param dataAtStartPoint The data at start point.
   * @param dataAtEndPoint   The data at end point.
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
   * @param dataName         The name of the data.
   * @param dataAtStartPoint The data at start point.
   * @param dataAtEndPoint   The data at end point.
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
   * @param dataName         The name of the data.
   * @param dataAtStartPoint The data at start point.
   * @param dataAtEndPoint   The data at end point.
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
   *                            start point and the end point. It is normally impossible.
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
   *
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
   *
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
   *
   * @return The new instance.
   */
  private Changes createChangesFromThis() {
    Changes changes = new Changes(this.getConnectionProvider());
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

  /**
   * Fluent {@link Changes} builder.
   * Use {@link AssertDbConnection} to construct new instance of this builder.
   * <pre>
   * <code class='java'>
   * AssertDbConnection connection = ....;
   * Changes changes = connection.changes().build();
   * Changes changes = connection.changes().table(&quot;movie&quot;, t -> t.columnToCheck(new String[] { &quot;number&quot;, &quot;title&quot; })).build();
   * </code>
   * </pre>
   *
   * @author Julien Roy
   * @since 3.0.0
   */
  public static class Builder {
    private final ConnectionProvider connectionProvider;
    private Request request;
    private List<Table> tables = new ArrayList<>();

    Builder(ConnectionProvider connectionProvider) {
      this.connectionProvider = connectionProvider;
    }

    /**
     * Force usage of {@link Request} for this {@link Changes} instead load all database objects.
     *
     * @param request Request to use to retrieve changes from DB.
     * @return Current builder instance.
     */
    public Changes.Builder request(Request request) {
      this.request = request;
      return this;
    }

    /**
     * Force usage of SQL request for this {@link Changes} instead load all database objects.
     *
     * @param request SQL Request to use to retrieve changes from DB.
     * @return Current builder instance.
     */
    public Changes.Builder request(String request) {
      this.request = new Request.Builder(this.connectionProvider, request).build();
      return this;
    }

    /**
     * Force usage of SQL request for this {@link Changes} instead load all database objects.
     * <pre>
     * <code class='java'>
     * AssertDbConnection connection = ....;
     * Changes changes = connection.changes()
     *  .request(&quot;select * from actor where id = ?&quot;, r -> r.parameters(1))
     *  .build();
     * </code>
     * </pre>
     *
     * @param request    SQL Request to use to retrieve changes from DB.
     * @param customizer Method that allow to customize the {@link Request} instance created.
     * @return Current builder instance.
     */
    public Changes.Builder request(String request, Consumer<Request.Builder> customizer) {
      Request.Builder builder = new Request.Builder(this.connectionProvider, request);
      customizer.accept(builder);
      this.request = builder.build();
      return this;
    }

    /**
     * Add new table to table list to use for this {@link Changes} instead load all database objects.
     * Each call to this method add new table to list.
     *
     * @param name Table name to use to retrieve changes from DB.
     * @return Current builder instance.
     */
    public Changes.Builder table(String name) {
      this.tables.add(new Table.Builder(this.connectionProvider, name).build());
      return this;
    }

    /**
     * Add new table to table list to use for this {@link Changes} instead load all database objects.
     * Each call to this method add new table to list.
     * <pre>
     * <code class='java'>
     * AssertDbConnection connection = ....;
     * Changes changes = connection.changes()
     *  .table(&quot;actor&quot;, t -> t.setColumnsToOrder(new Order[]{Order.asc("where")})
     *  .build();
     * </code>
     * </pre>
     *
     * @param name       Table name to use to retrieve changes from DB.
     * @param customizer Method that allow to customize the {@link Table} instance created.
     * @return Current builder instance.
     */
    public Changes.Builder table(String name, Consumer<Table.Builder> customizer) {
      Table.Builder builder = new Table.Builder(this.connectionProvider, name);
      customizer.accept(builder);
      this.tables.add(builder.build());
      return this;
    }

    /**
     * Force usage of {@link Table} list for this {@link Changes} instead load all database objects.
     *
     * @param tables Tables to use to retrieve changes from DB.
     * @return Current builder instance.
     */
    public Changes.Builder tables(Table... tables) {
      this.tables = Arrays.asList(tables);
      return this;
    }

    /**
     * Build the Changes instance.
     *
     * @return Changes instance to use in assertThat.
     */
    public Changes build() {
      if (this.tables != null && !tables.isEmpty()) {
        return new Changes(this.connectionProvider, tables);
      }
      if (this.request != null) {
        return new Changes(this.connectionProvider, request);
      }
      return new Changes(this.connectionProvider);
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
  public ChangesAssert assertThat() {
    return Assertions.assertThat(this);
  }
}
