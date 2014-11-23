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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

/**
 * A table in the database to read to get the values.
 * <p>
 * The different informations of the table are connection or data source, the SQL request and optionally the parameters
 * of the SQL request.
 * </p>
 * <p>
 * Examples of instantiation :
 * </p>
 * <ul>
 * <li>
 * <p>
 * This {@link Request} point to a request without parameter in a H2 database in memory like indicated in the
 * {@link Source}.
 * </p>
 * 
 * <pre><code class='java'>
 * Source source = new Source(&quot;jdbc:h2:mem:test&quot;, &quot;sa&quot;, &quot;&quot;);
 * Request request = new Request(source, &quot;select title from movie;&quot;);
 * </code></pre>
 * 
 * </li>
 * <li>
 * <p>
 * Below the {@link Request} point to a request with {@code 2000} in parameter.<br>
 * The {@link Request} use a {@code DataSource} instead of a {@link Source} like above.
 * </p>
 * 
 * <pre><code class='java'>
 * DataSource dataSource = ...;
 * Request request = new Request(dataSource, "select title from movie where year &gt; ?;", 2000);
 * </code></pre>
 * 
 * </li>
 * </ul>
 * 
 * @author Régis Pouiller
 * 
 */
public class Request extends AbstractDbData<Request> {

  /**
   * SQL request to get the values.
   */
  private String request;
  /**
   * Parameters of the SQL request.
   */
  private Object[] parameters;

  /**
   * Default constructor.
   */
  public Request() {
    super(Request.class);
  }

  /**
   * Constructor with a connection.
   * 
   * @param source Source to connect to the database.
   * @param request SQL Request to get the values.
   * @param parameters Parameters of the SQL request.
   */
  public Request(Source source, String request, Object... parameters) {
    super(Request.class, source);
    setRequest(request);
    this.parameters = parameters;
  }

  /**
   * Constructor with a data source.
   * 
   * @param dataSource Data source.
   * @param request SQL Request to get the values.
   * @param parameters Parameters of the SQL request.
   */
  public Request(DataSource dataSource, String request, Object... parameters) {
    super(Request.class, dataSource);
    setRequest(request);
    this.parameters = parameters;
  }

  /**
   * Returns the SQL request.
   * 
   * @return The SQL request.
   */
  public String getRequest() {
    return request;
  }

  /**
   * Sets the SQL request.
   * 
   * @param request The SQL request.
   * @return The SQL request.
   * @throws NullPointerException If the {@link #request} field is {@code null}.
   */
  public Request setRequest(String request) {
    if (request == null) {
      throw new NullPointerException("request can not be null");
    }

    this.request = request;
    return this;
  }

  /**
   * The parameters of the SQL request.
   * 
   * @return The SQL request.
   */
  public Object[] getParameters() {
    if (parameters == null) {
      return null;
    }
    return parameters.clone();
  }

  /**
   * Sets the parameters of the SQL request.
   * 
   * @param parameters The parameters of the SQL request.
   * @return The parameters of the SQL request.
   */
  public Request setParameters(Object... parameters) {
    this.parameters = parameters;
    return this;
  }

  /**
   * Sets the primary keys name.
   * 
   * @param pksName The primary keys name.
   * @return {@code this} instance.
   */
  public Request setPksName(String... pksName) {
    List<String> pksNameList = new ArrayList<String>();
    pksNameList.addAll(Arrays.asList(pksName));
    super.setPksNameList(pksNameList);
    return this;
  }

  /**
   * Collects the columns name from the {@code ResultSet} from the SQL request.
   * <p>
   * This method use the {@link ResultSetMetaData} from the <code>resultSet</code> parameter to list the name of the
   * columns.
   * </p>
   * 
   * @param resultSet The {@code ResultSet}.
   * @throws SQLException A SQL Exception
   */
  private void collectColumnsNameFromResultSet(ResultSet resultSet) throws SQLException {
    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
    List<String> columnsNameList = new ArrayList<String>();
    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
      String columnName = resultSetMetaData.getColumnName(i);
      columnsNameList.add(columnName.toUpperCase());
    }
    setColumnsNameList(columnsNameList);
    controlIfAllThePksNameExistInTheColumns();
  }

  /**
   * Specific implementation of the loading for a {@code Request}.
   * 
   * @see AbstractDbData#loadImpl(Connection)
   * @param connection {@link Connection} to the database provided by {@link AbstractDbData#load()} private method.
   * @throws NullPointerException If the {@link #request} field is {@code null}.
   * @throws SQLException SQL Exception.
   */
  @Override
  protected void loadImpl(Connection connection) throws SQLException {
    if (request == null) {
      throw new NullPointerException("request can not be null");
    }

    try (PreparedStatement statement = connection.prepareStatement(request)) {
      for (int i = 0; i < parameters.length; i++) {
        statement.setObject(i + 1, parameters[i]);
      }
      try (ResultSet resultSet = statement.executeQuery()) {
        collectColumnsNameFromResultSet(resultSet);
        collectRowsFromResultSet(resultSet);
      }
    }
  }
}
