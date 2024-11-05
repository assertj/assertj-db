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

import javax.sql.DataSource;

import org.assertj.db.type.lettercase.LetterCase;

/**
 * Fluent factory to create a connectionProvider from different input ( Jdbc URL or DataSource ).
 * Allow to configure behavior of connection provider like letter case or schema metadata retrieval mode.
 * <p>
 * For create with JDBC URL :
 * <pre>
 * <code class='java'>
 * ConnectionProvider connectionProvider = ConnectionProviderFactory.of(&quot;jdbc:h2:mem:test&quot;, &quot;sa&quot;, &quot;&quot;).create();
 * Table table = new Table(connectionProvider, &quot;movie&quot;);
 * </code>
 * </pre>
 * <p>
 * For create with JDBC URL :
 * <pre>
 * <code class='java'>
 * DataSource dataSource = ...;
 * ConnectionProvider connectionProvider = ConnectionProviderFactory.of(dataSource).create();
 * Table table = new Table(connectionProvider, &quot;song&quot;, new String[] { &quot;number&quot;, &quot;title&quot; }, null);
 * </code>
 * </pre>
 *
 * @author Julien Roy
 * @since 3.0.0
 */
public abstract class ConnectionProviderFactory {

  private ConnectionProviderFactory() {
    throw new UnsupportedOperationException();
  }

  /**
   * Initiate factory with Java.sql.DataSource
   *
   * @param dataSource Datasource to retrieve data from database.
   * @return Factory of ConnectionProvider
   */
  public static DataSourceConnectionProviderFactory of(DataSource dataSource) {
    return new DataSourceConnectionProviderFactory(dataSource);
  }

  /**
   * Initiate factory with JDBC connection information
   *
   * @param url      JDBC url of database.
   * @param user     Connection username.
   * @param password Connection password.
   * @return Factory of ConnectionProvider
   */
  public static JdbcUrlConnectionProviderFactory of(String url, String user, String password) {
    return new JdbcUrlConnectionProviderFactory(url, user, password);
  }

  /**
   * DataSource variant of ConnectionProviderFactory
   */
  public static class DataSourceConnectionProviderFactory extends AbstractConnectionProviderFactory<DataSourceConnectionProviderFactory> {
    private final DataSource dataSource;

    private DataSourceConnectionProviderFactory(DataSource dataSource) {
      super();
      this.dataSource = dataSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConnectionProvider create() {
      return new DataSourceConnectionProvider(dataSource, this.schemaMetaDataMode.getType(), this.tableLetterCase, this.columnLetterCase, this.primaryKeyLetterCase);
    }
  }

  /**
   * Jdbc url variant of ConnectionProviderFactory
   */
  public static class JdbcUrlConnectionProviderFactory extends AbstractConnectionProviderFactory<JdbcUrlConnectionProviderFactory> {
    private final String url;
    private final String user;
    private final String password;

    private JdbcUrlConnectionProviderFactory(String url, String user, String password) {
      super();

      this.url = url;
      this.user = user;
      this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConnectionProvider create() {
      return new JdbcUrlConnectionProvider(url, user, password, this.schemaMetaDataMode.getType(), this.tableLetterCase, this.columnLetterCase, this.primaryKeyLetterCase);
    }
  }

  @SuppressWarnings("unchecked")
  private abstract static class AbstractConnectionProviderFactory<T> {

    /**
     * Schema metadata retrieval mode.
     */
    protected SchemaMetaDataMode schemaMetaDataMode = SchemaMetaDataMode.DYNAMIC;
    /**
     * Tables letter case.
     */
    protected LetterCase tableLetterCase = LetterCase.TABLE_DEFAULT;
    /**
     * Columns letter case.
     */
    protected LetterCase columnLetterCase = LetterCase.COLUMN_DEFAULT;
    /**
     * PKs letter case.
     */
    protected LetterCase primaryKeyLetterCase = LetterCase.PRIMARY_KEY_DEFAULT;

    private AbstractConnectionProviderFactory() {
    }

    /**
     * Modify the default letter case mode for connection provider.
     *
     * @param tableLetterCase      Tables letter case.
     * @param columnLetterCase     Columns letter case.
     * @param primaryKeyLetterCase PKs letter case.
     * @return the current instance of factory.
     */
    public T letterCase(LetterCase tableLetterCase, LetterCase columnLetterCase, LetterCase primaryKeyLetterCase) {
      this.tableLetterCase = tableLetterCase;
      this.columnLetterCase = columnLetterCase;
      this.primaryKeyLetterCase = primaryKeyLetterCase;
      return (T) this;
    }

    /**
     * Modify the current mode for schema metadata retrieval.
     *
     * @param mode The selected mode
     * @return the current instance of factory.
     */
    public T schemaMetaDataMode(SchemaMetaDataMode mode) {
      if (mode == null) {
        throw new IllegalArgumentException("SchemaMetaDataMode cannot be null");
      }
      this.schemaMetaDataMode = mode;
      return (T) this;
    }

    /**
     * Build the Connection Provider
     *
     * @return Connection provider to use for Table, Request or Changes
     */
    public abstract ConnectionProvider create();
  }
}
