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
 * Copyright 2015-2024 the original author or authors.
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

import org.assertj.db.type.lettercase.LetterCase;

/**
 * A request in the database to get values.
 * <p>
 * The different information of the request are the SQL request and optionally the parameters of the SQL request.
 * A Request should be constructed by the fluent builder {@link Request.Builder} from a AssertDbConnection instance.
 * </p>
 * <p>
 * Examples of instantiation :
 * </p>
 * <ul>
 * <li>
 * <p>
 * This {@link Request} point to a request without parameter in a H2 database in memory.
 * </p>
 *
 * <pre><code class='java'>
 * AssertDbConnection connection = AssertDbConnectionFactory.of(&quot;jdbc:h2:mem:test&quot;, &quot;sa&quot;, &quot;&quot;).create();
 * Request request = connection.request(&quot;select title from movie;&quot;).build();
 * </code></pre>
 *
 * </li>
 * <li>
 * <p>
 * Below the {@link Request} point to a request with {@code 2000} in parameter.<br>
 * The {@link AssertDbConnection} use a {@code DataSource} instead of a JDBC url like above.
 * </p>
 *
 * <pre><code class='java'>
 * DataSource dataSource = ...;
 * AssertDbConnection connection = AssertDbConnectionFactory.of(dataSource).create();
 * Request request = connection.request("select title from movie where year &gt; ?;").parameters(2000).build();
 * </code></pre>
 *
 * </li>
 * </ul>
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Request extends AbstractDbData<Request> {

  /**
   * SQL request to get the values.
   */
  private final String request;
  /**
   * Parameters of the SQL request.
   */
  private final Object[] parameters;

  /**
   * Constructor with a connection.
   *
   * @param connectionProvider Connection provider to connect to the database.
   * @param request            SQL Request to get the values.
   * @param parameters         Parameters of the SQL request.
   * @since 3.0.0
   */
  private Request(ConnectionProvider connectionProvider, String request, Object[] parameters, String[] pksName) {
    super(Request.class, DataType.REQUEST, connectionProvider);
    if (request == null) {
      throw new IllegalArgumentException("request can not be null");
    }
    if (pksName != null) {
      super.setPksNameList(new ArrayList<>(Arrays.asList(pksName)));
    }
    this.request = request;
    this.parameters = parameters;
  }

  /**
   * Only used for tests.
   */
  private Request() {
    super(Request.class, DataType.REQUEST);
    this.request = null;
    this.parameters = null;
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
   * The parameters of the SQL request.
   *
   * @return The SQL request.
   */
  public Object[] getParameters() {
    if (parameters == null) {
      return new Object[0];
    }
    return parameters.clone();
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
    LetterCase letterCase = getColumnLetterCase();
    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
    List<String> columnsNameList = new ArrayList<>();
    for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
      String columnName = resultSetMetaData.getColumnLabel(i);
      columnsNameList.add(letterCase.convert(columnName));
    }
    setColumnsNameList(columnsNameList);
    controlIfAllThePksNameExistInTheColumns();
  }

  /**
   * Specific implementation of the loading for a {@code Request}.
   *
   * @param connection {@link Connection} to the database provided by {@link AbstractDbData#load()} private method.
   * @throws NullPointerException If the {@link #request} field is {@code null}.
   * @throws SQLException         SQL Exception.
   * @see AbstractDbData#loadImpl(Connection)
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

  /**
   * Fluent {@link Request} builder.
   * Use {@link AssertDbConnection} to construct new instance of this builder.
   * <pre>
   * <code class='java'>
   * AssertDbConnection connection = ....;
   * Request request = connection.request(&quot;select * from actor;&quot;).build();
   * Request request = connection.request(&quot;select * from actor where id = ?;&quot;).parameters(1).build();
   * </code>
   * </pre>
   *
   * @author Julien Roy
   * @since 3.0.0
   */
  public static class Builder {
    private final ConnectionProvider connectionProvider;
    private final String request;
    private Object[] parameters = new Object[0];
    private String[] pksName = null;

    Builder(ConnectionProvider connectionProvider, String request) {
      this.connectionProvider = connectionProvider;
      this.request = request;
    }

    /**
     * Specify parameters to use in SQL request.
     *
     * @param parameters List of SQL parameters.
     * @return Current builder instance.
     */
    public Request.Builder parameters(Object... parameters) {
      this.parameters = parameters;
      return this;
    }

    /**
     * Set the list of primary keys
     *
     * @param pksName List of column name to use as primary keys.
     * @return Current builder instance.
     */
    public Request.Builder pksName(String... pksName) {
      this.pksName = pksName;
      return this;
    }

    /**
     * Build the Request instance.
     *
     * @return Request instance to use in assertThat.
     */
    public Request build() {
      return new Request(this.connectionProvider, this.request, this.parameters, this.pksName);
    }
  }
}
