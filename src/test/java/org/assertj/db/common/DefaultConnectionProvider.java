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
package org.assertj.db.common;

import java.sql.Connection;
import java.sql.SQLException;

import org.assertj.db.type.ConnectionProvider;
import org.assertj.db.type.SchemaMetadata;
import org.assertj.db.type.lettercase.LetterCase;

public class DefaultConnectionProvider implements ConnectionProvider {

  private final Connection connection;

  public DefaultConnectionProvider(Connection connection) {
    this.connection = connection;
  }

  @Override
  public LetterCase getTableLetterCase() {
    return null;
  }

  @Override
  public LetterCase getPrimaryKeyLetterCase() {
    return null;
  }

  @Override
  public LetterCase getColumnLetterCase() {
    return null;
  }

  @Override
  public Connection getConnection() throws SQLException {
    return this.connection;
  }

  @Override
  public SchemaMetadata getMetaData() {
    return null;
  }
}
