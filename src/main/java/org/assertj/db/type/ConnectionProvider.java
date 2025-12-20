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

import org.assertj.db.type.lettercase.WithLetterCase;

/**
 * Represent access to a database with capacity to return schema metadata and settings for letter case management.
 *
 * @author Julien Roy
 * @since 3.0.0
 */
public interface ConnectionProvider extends WithLetterCase {

  /**
   * Return SQL connection to database.
   *
   * @return An active {@link Connection} to database.
   * @throws SQLException When access to database fail.
   */
  Connection getConnection() throws SQLException;

  /**
   * Return a accessor to retrieve database schema metadata.
   *
   * @return An {@link SchemaMetadata} to access to database schema metadata.
   */
  SchemaMetadata getMetaData();

}
