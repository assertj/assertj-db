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

import java.util.Collection;

/**
 * Represent access to a database schema metadata.
 *
 * @author Julien Roy
 * @since 3.0.0
 */
public interface SchemaMetadata {

  /**
   * Return list of name of tables discovered in database schema.
   *
   * @return List of table names
   */
  Collection<String> getTablesName();

  /**
   * Return list of name of columns discovered in database schema.
   *
   * @param tableName Name of table to discover.
   * @return List of table names
   */
  Collection<String> getColumnsName(String tableName);

  /**
   * Return list of primary keys discovered in database schema.
   *
   * @param tableName Name of table to discover.
   * @return List of  primary keys
   */
  Collection<String> getPrimaryKeys(String tableName);

}
