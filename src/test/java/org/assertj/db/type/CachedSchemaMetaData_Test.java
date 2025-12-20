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

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.DefaultConnectionProvider;
import org.junit.Test;

/**
 * Tests on the cached schema metadata.
 *
 * @author Julien Roy
 */
public class CachedSchemaMetaData_Test extends AbstractTest {

  final DefaultConnectionProvider provider;

  public CachedSchemaMetaData_Test() throws SQLException {
    this.provider = new DefaultConnectionProvider(DriverManager.getConnection("jdbc:h2:mem:test", "sa", ""));
  }

  @Test
  public void test_get_tables() {
    CachedSchemaMetaData metaData = new CachedSchemaMetaData(provider);
    assertThat(metaData.getTablesName()).containsExactly("ACTOR", "INTERPRETATION", "MOVIE", "TEST", "TEST2");
  }

  @Test
  public void test_get_columns() {
    CachedSchemaMetaData metaData = new CachedSchemaMetaData(provider);
    assertThat(metaData.getColumnsName("ACTOR")).containsExactly("ID", "NAME", "FIRSTNAME", "BIRTH", "ACTOR_IMDB");
  }

  @Test
  public void test_get_primary_keys() {
    CachedSchemaMetaData metaData = new CachedSchemaMetaData(provider);
    assertThat(metaData.getPrimaryKeys("ACTOR")).containsExactly("ID");
  }
}
