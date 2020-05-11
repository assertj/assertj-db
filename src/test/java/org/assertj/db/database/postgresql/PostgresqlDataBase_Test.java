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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.database.postgresql;

import org.assertj.db.api.Assertions;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test on the Postgresql database.
 *
 * @author Julien Roy
 */
public class PostgresqlDataBase_Test extends AbstractPostgresqlTest {

  @Test
  public void test_catalog_for_data_source() throws SQLException {
    try (Connection connection = dataSource.getConnection()) {
      String catalog = connection.getCatalog();
      assertThat(catalog).isEqualTo("test");
    }
  }

  @Test
  public void test_is_equal_to_date() {
    Assertions.assertThat(new Table(dataSource, "test"))
              .row(0)
              .value("var2").isEqualTo(DateValue.of(2020, 4, 6));
  }
}
