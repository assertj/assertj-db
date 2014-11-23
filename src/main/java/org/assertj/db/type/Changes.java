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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.type;

import static org.assertj.db.type.Change.createCreationChange;
import static org.assertj.db.type.Change.createDeletionChange;
import static org.assertj.db.type.Change.createModificationChange;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.util.ChangeComparator;

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
   * @param tables Table on which are the comparison
   */
  public Changes(Table... tables) {
    super(Changes.class);
    setTables(tables);
  }

  /**
   * Constructor.
   * 
   * @param request Request on which are the comparison
   */
  public Changes(Request request) {
    super(Changes.class);
    setRequest(request);
  }

  /**
   * Sets the table on which are the comparison.
   * 
   * @param tables Table on which are the comparison
   * @return {@code this} actual instance.
   */
  public Changes setTables(Table... tables) {
    request = null;
    requestAtStartPoint = null;
    requestAtEndPoint = null;
    tablesList = new LinkedList<Table>();
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
   * Sets the {@link Request}
   * 
   * @param request The {@link Request}
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
    return r.setRequest(request.getRequest()).setParameters(request.getParameters());
  }

  /**
   * Duplicate the {@link Table} in parameter and returns it.
   * 
   * @param request The {@link Table} to duplicate
   * @return The Duplication
   */
  private static Table getDuplicatedTable(Table table) {
    Table t = new Table();
    copyElement(table, t);
    return t.setName(table.getName()).setColumnsToCheck(table.getColumnsToCheck())
        .setColumnsToExclude(table.getColumnsToExclude());
  }

  /**
   * Sets the start point for comparison.
   * 
   * @return {@code this} actual instance.
   */
  public Changes setStartPointNow() {
    if (request == null && tablesList == null) {
      try (Connection connection = getConnection()) {
        tablesList = new LinkedList<Table>();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(getCatalog(connection), getSchema(connection), null,
            new String[] { "TABLE" });
        while (resultSet.next()) {
          String tableName = resultSet.getString("TABLE_NAME");
          Table t = new Table().setName(tableName);
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
      tablesAtStartPointList = new LinkedList<Table>();
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
      tablesAtEndPointList = new LinkedList<Table>();
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
   * Returns the list of changes for the data.
   * 
   * @param dataName The name of the data.
   * @param dataAtStartPoint The data at start point.
   * @param dataAtEndPoint The data at end point.
   * @return The list of changes for the data.
   */
  private List<Change> getChangesList(String dataName, AbstractDbData<?> dataAtStartPoint,
      AbstractDbData<?> dataAtEndPoint) {

    List<Change> changesList = new ArrayList<Change>();
    if (dataAtStartPoint.getPksNameList() != null && dataAtStartPoint.getPksNameList().size() > 0) {
      // List the created rows : the row is not present at the start point
      for (Row row : dataAtEndPoint.getRowsList()) {
        Row rowAtStartPoint = dataAtStartPoint.getRowFromPksValues(row.getPksValues());
        if (rowAtStartPoint == null) {
          Change change = createCreationChange(dataName, row);
          changesList.add(change);
        }
      }
      for (Row row : dataAtStartPoint.getRowsList()) {
        Row rowAtEndPoint = dataAtEndPoint.getRowFromPksValues(row.getPksValues());
        if (rowAtEndPoint == null) {
          // List the deleted rows : the row is not present at the end point
          Change change = createDeletionChange(dataName, row);
          changesList.add(change);
        } else {
          // List the modified rows
          if (!row.haveValuesEqualTo(rowAtEndPoint)) {
            // If at least one value in the rows is different, add the change
            Change change = createModificationChange(dataName, row, rowAtEndPoint);
            changesList.add(change);
          }
        }
      }
    } else {
      // List the created rows : the row is not present at the start point
      List<Row> rowsAtStartPointList = new ArrayList<Row>(dataAtStartPoint.getRowsList());
      for (Row rowAtEndPoint : dataAtEndPoint.getRowsList()) {
        int index = -1;
        int index1 = 0;
        for (Row rowAtStartPoint : rowsAtStartPointList) {
          if (rowAtEndPoint.haveValuesEqualTo(rowAtStartPoint)) {
            index = index1;
            break;
          }
          index1++;
        }
        if (index == -1) {
          Change change = createCreationChange(dataName, rowAtEndPoint);
          changesList.add(change);
        } else {
          rowsAtStartPointList.remove(index);
        }
      }
      // List the deleted rows : the row is not present at the end point
      List<Row> rowsAtEndPointList = new ArrayList<Row>(dataAtEndPoint.getRowsList());
      for (Row rowAtStartPoint : dataAtStartPoint.getRowsList()) {
        int index = -1;
        int index1 = 0;
        for (Row rowAtEndPoint : rowsAtEndPointList) {
          if (rowAtStartPoint.haveValuesEqualTo(rowAtEndPoint)) {
            index = index1;
            break;
          }
          index1++;
        }
        if (index == -1) {
          Change change = createDeletionChange(dataName, rowAtStartPoint);
          changesList.add(change);
        } else {
          rowsAtEndPointList.remove(index);
        }
      }
    }

    Collections.sort(changesList, ChangeComparator.INSTANCE);
    return changesList;
  }

  /**
   * Returns the list of the changes.
   * 
   * @return The list of the changes.
   */
  public List<Change> getChangesList() {
    if (changesList == null) {
      if (requestAtEndPoint == null && tablesAtEndPointList == null) {
        throw new AssertJDBException("End point must be set before");
      }

      if (requestAtEndPoint != null) {
        changesList = getChangesList(requestAtStartPoint.getRequest(), requestAtStartPoint, requestAtEndPoint);
      } else {
        changesList = new ArrayList<Change>();
        Iterator<Table> iteratorAtStartPoint = tablesAtStartPointList.iterator();
        Iterator<Table> iteratorAtEndPoint = tablesAtEndPointList.iterator();
        while (iteratorAtStartPoint.hasNext() && iteratorAtEndPoint.hasNext()) {
          Table tableAtStartPoint = iteratorAtStartPoint.next();
          Table tableAtEndPoint = iteratorAtEndPoint.next();
          changesList.addAll(getChangesList(tableAtStartPoint.getName(), tableAtStartPoint, tableAtEndPoint));
        }
      }
    }
    return changesList;
  }
}
