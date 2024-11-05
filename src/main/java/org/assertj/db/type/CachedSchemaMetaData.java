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

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of SchemaMetadata that cache the metadata of the first request without any expiration.
 *
 * @author Julien Roy
 * @since 3.0.0
 */
class CachedSchemaMetaData implements SchemaMetadata {

  private final FromConnectionSchemaMetadata metadata;
  private final Map<String, Collection<String>> cache = new ConcurrentHashMap<>();

  public CachedSchemaMetaData(ConnectionProvider connectionProvider) {
    this.metadata = new FromConnectionSchemaMetadata(connectionProvider);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<String> getTablesName() {
    return cache.computeIfAbsent("TABLES", key -> this.metadata.getTablesName());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<String> getColumnsName(String tableName) {
    return cache.computeIfAbsent("COLUMNS#" + tableName, key -> this.metadata.getColumnsName(tableName));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<String> getPrimaryKeys(String tableName) {
    return cache.computeIfAbsent("PKS#" + tableName, key -> this.metadata.getPrimaryKeys(tableName));
  }
}
