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

/**
 * Entry point for creating database element ( Table, Request, Changes ) required to build assertion.
 * Use {@link AssertDbConnectionFactory} to construct new instance of this builder.
 * <p>
 * Provider fluent builder for create Table, Request and Changes :
 * <pre>
 * <code class='java'>
 * AssertDbConnection connection = ....;
 * Table table = connection.table(&quot;movie&quot;).build();
 * Request request = connection.request(&quot;select * from actor;&quot;).build();
 * Changes changes = connection.changes().build();
 * </code>
 * </pre>
 * <p>
 * Some more advanced examples :
 * <pre>
 * <code class='java'>
 * AssertDbConnection connection = ....;
 * Table table = connection.table(&quot;movie&quot;).columnToCheck(new String[] { &quot;number&quot;, &quot;title&quot; }).build();
 * Request request = connection.request(&quot;select * from actor where id = ?;&quot;).parameters(1).build();
 * Changes changes = connection.changes().table(&quot;movie&quot;, t -> t.columnToCheck(new String[] { &quot;number&quot;, &quot;title&quot; })).build();
 * </code>
 * </pre>
 *
 * @author Julien Roy
 * @since 3.0.0
 */
public class AssertDbConnection {

  private final ConnectionProvider connectionProvider;

  AssertDbConnection(ConnectionProvider connectionProvider) {
    if (connectionProvider == null) {
      throw new IllegalArgumentException("connectionProvider can not be null");
    }
    this.connectionProvider = connectionProvider;
  }

  /**
   * Start building Table element.
   *
   * @param name Name of the table
   * @return Table builder
   */
  public Table.Builder table(String name) {
    return new Table.Builder(this.connectionProvider, name);
  }

  /**
   * Start building Request element.
   *
   * @param request SQL request
   * @return Request builder
   */
  public Request.Builder request(String request) {
    return new Request.Builder(this.connectionProvider, request);
  }

  /**
   * Start building Changes element.
   *
   * @return Changes builder
   */
  public Changes.Builder changes() {
    return new Changes.Builder(this.connectionProvider);
  }
}
