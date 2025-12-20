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
import java.sql.SQLException;
import javax.sql.DataSource;

import org.assertj.db.type.lettercase.LetterCase;

/**
 * The implementation of {@link ConnectionProvider} based on java.sql.DataSource.
 * Use {@link AssertDbConnectionFactory} for instantiate.
 *
 * @author Julien Roy
 * @since 3.0.0
 */
public class DataSourceConnectionProvider extends AbstractConnectionProvider {

  private final DataSource dataSource;

  DataSourceConnectionProvider(DataSource dataSource,
                               Class<? extends SchemaMetadata> schemaMetadataType,
                               LetterCase tableLetterCase, LetterCase columnLetterCase, LetterCase primaryKeyLetterCase) {

    super(schemaMetadataType, tableLetterCase, columnLetterCase, primaryKeyLetterCase);
    this.dataSource = dataSource;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "data source";
  }
}
