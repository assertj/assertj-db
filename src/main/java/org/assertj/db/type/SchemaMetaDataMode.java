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
 * Define existing schema metadata retrieval mode.
 *
 * @author Julien Roy
 * @since 3.0.0
 */
public enum SchemaMetaDataMode {
  /**
   * Retrieve schema metadata only once and keep in memory for all duration of connection provider.
   * To use when database schema is not modified during tests.
   */
  STATIC(CachedSchemaMetaData.class),
  /**
   * Retrieve schema metadata each time is required.
   * To use when database schema is modified during tests.
   */
  DYNAMIC(FromConnectionSchemaMetadata.class);


  private final Class<? extends SchemaMetadata> type;

  SchemaMetaDataMode(Class<? extends SchemaMetadata> type) {
    this.type = type;
  }

  /**
   * Return the underline java type that implement the strategy.
   *
   * @return Class to use for instantiate the schema retriever.
   */
  Class<? extends SchemaMetadata> getType() {
    return type;
  }
}
