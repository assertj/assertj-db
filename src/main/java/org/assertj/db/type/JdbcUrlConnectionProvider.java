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
import java.sql.DriverManager;
import java.sql.SQLException;

import org.assertj.db.type.lettercase.LetterCase;

/**
 * The implementation of {@link ConnectionProvider} from JDBC url connection info.
 * Use {@link ConnectionProviderFactory} for instantiate.
 *
 * @author Julien Roy
 * @since 3.0.0
 */
public class JdbcUrlConnectionProvider extends AbstractConnectionProvider {

  private final String url;
  private final String user;
  private final String password;

  JdbcUrlConnectionProvider(String url, String user, String password,
                            Class<? extends SchemaMetadata> schemaMetadataType,
                            LetterCase tableLetterCase, LetterCase columnLetterCase, LetterCase primaryKeyLetterCase) {

    super(schemaMetadataType, tableLetterCase, columnLetterCase, primaryKeyLetterCase);
    this.url = url;
    this.user = user;
    this.password = password;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(this.url, this.user, this.password);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return this.user + "/" + this.url;
  }
}
