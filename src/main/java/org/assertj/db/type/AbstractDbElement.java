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
import java.sql.SQLException;

import org.assertj.db.type.lettercase.LetterCase;
import org.assertj.db.type.lettercase.WithLetterCase;

/**
 * This class represents element from the database (either a {@link AbstractDbData} or a {@link Change}).
 * So this class contains : the way to access the database with {@link #getConnectionProvider()}.<br>
 *
 * @param <D> Class of the subclass (an implementation of {@link AbstractDbElement}) : useful for the fluent methods
 *            (setters).
 * @author Régis Pouiller
 * @author Julien Roy
 */
public abstract class AbstractDbElement<D extends AbstractDbElement<D>> implements DbElement, WithLetterCase {

  /**
   * Class of the element.
   */
  protected final D myself;
  /**
   * Database connection provider.
   */
  private ConnectionProvider connectionProvider;
  /**
   * Letter case of the tables.
   *
   * @since 1.1.0
   */
  private LetterCase tableLetterCase = LetterCase.TABLE_DEFAULT;
  /**
   * Letter case of the columns.
   *
   * @since 1.1.0
   */
  private LetterCase columnLetterCase = LetterCase.COLUMN_DEFAULT;
  /**
   * Letter case of the primary keys.
   *
   * @since 1.1.0
   */
  private LetterCase primaryKeyLetterCase = LetterCase.PRIMARY_KEY_DEFAULT;

  /**
   * Default constructor.
   *
   * @param selfType Class of this element : a sub-class of {@code AbstractDbElement}.
   */
  AbstractDbElement(Class<D> selfType) {
    myself = selfType.cast(this);
    setLetterCases();
  }

  /**
   * Constructor.
   *
   * @param selfType           Class of this element : a subclass of {@code AbstractDbElement}.
   * @param connectionProvider The {@link ConnectionProvider} to connect to the database (must be not {@code null}).
   * @throws NullPointerException If {@code connectionProvider} is {@code null}.
   */
  AbstractDbElement(Class<D> selfType, ConnectionProvider connectionProvider) {
    this(selfType);
    this.connectionProvider = connectionProvider;
    setLetterCases();
  }

  /**
   * Sets the letter cases from information in parameters.
   *
   * @param tableLetterCase      Letter case of the tables.
   * @param columnLetterCase     Letter case of the columns.
   * @param primaryKeyLetterCase Letter case of the primary keys.
   * @return The actual instance.
   */
  D setLetterCases(LetterCase tableLetterCase, LetterCase columnLetterCase, LetterCase primaryKeyLetterCase) {
    this.tableLetterCase = tableLetterCase;
    this.columnLetterCase = columnLetterCase;
    this.primaryKeyLetterCase = primaryKeyLetterCase;
    return myself;
  }

  /**
   * Sets the letter cases from information in {@code connectionProvider}.
   */
  private void setLetterCases() {
    if (connectionProvider == null) {
      return;
    }
    tableLetterCase = connectionProvider.getTableLetterCase();
    columnLetterCase = connectionProvider.getColumnLetterCase();
    primaryKeyLetterCase = connectionProvider.getPrimaryKeyLetterCase();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LetterCase getColumnLetterCase() {
    return columnLetterCase;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LetterCase getPrimaryKeyLetterCase() {
    return primaryKeyLetterCase;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LetterCase getTableLetterCase() {
    return tableLetterCase;
  }

  /**
   * Return the connectionProvider.
   *
   * @return The {@link ConnectionProvider} to connect.
   * @see #setConnectionProvider(ConnectionProvider)
   */
  public ConnectionProvider getConnectionProvider() {
    return connectionProvider;
  }

  /**
   * Sets the connectionProvider.
   *
   * @param connectionProvider {@link ConnectionProvider} to connect to the database (must be not {@code null}).
   * @return The actual instance.
   * @throws NullPointerException If {@code connectionProvider} is {@code null}.
   */
  public D setConnectionProvider(ConnectionProvider connectionProvider) {
    if (connectionProvider == null) {
      throw new NullPointerException("connectionProvider must be not null");
    }
    this.connectionProvider = connectionProvider;
    setLetterCases();
    return myself;
  }

  /**
   * Returns a {@link Connection} from the {@link ConnectionProvider}
   *
   * @return A {@link Connection} from connectionProvider
   * @throws SQLException         SQL Exception
   * @throws NullPointerException this connection provider is null
   */
  protected Connection getConnection() throws SQLException {
    if (connectionProvider == null) {
      throw new NullPointerException("connectionProvider must be not null");
    }
    return connectionProvider.getConnection();
  }

  /**
   * Returns a {@link SchemaMetadata} from the {@link ConnectionProvider}
   *
   * @return A {@link SchemaMetadata} from connectionProvider
   * @throws NullPointerException this connection provider is null
   */
  protected SchemaMetadata getMetaData() {
    if (connectionProvider == null) {
      throw new NullPointerException("connectionProvider must be not null");
    }
    return connectionProvider.getMetaData();
  }
}
