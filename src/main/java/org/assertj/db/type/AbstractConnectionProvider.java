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

import java.lang.reflect.InvocationTargetException;

import org.assertj.db.type.lettercase.LetterCase;

/**
 * Base implementation for ConnectionProvider that handle letter case and schema metadata management.
 *
 * @author Julien Roy
 * @since 3.0.0
 */
abstract class AbstractConnectionProvider implements ConnectionProvider {

  private final SchemaMetadata schemaMetadata;

  private final LetterCase tableLetterCase;
  private final LetterCase columnLetterCase;
  private final LetterCase primaryKeyLetterCase;

  protected AbstractConnectionProvider(Class<? extends SchemaMetadata> schemaMetadataType, LetterCase tableLetterCase, LetterCase columnLetterCase, LetterCase primaryKeyLetterCase) {
    this.schemaMetadata = instantiateSchemaMetadata(schemaMetadataType);
    this.tableLetterCase = tableLetterCase;
    this.columnLetterCase = columnLetterCase;
    this.primaryKeyLetterCase = primaryKeyLetterCase;
  }

  private SchemaMetadata instantiateSchemaMetadata(Class<? extends SchemaMetadata> schemaMetadataType) {
    try {
      return schemaMetadataType.getConstructor(ConnectionProvider.class).newInstance(this);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new IllegalArgumentException("Schema metadata instantiation failure", e);
    }
  }

  @Override
  public LetterCase getTableLetterCase() {
    return tableLetterCase;
  }

  @Override
  public LetterCase getColumnLetterCase() {
    return columnLetterCase;
  }

  @Override
  public LetterCase getPrimaryKeyLetterCase() {
    return primaryKeyLetterCase;
  }

  @Override
  public SchemaMetadata getMetaData() {
    return this.schemaMetadata;
  }
}
