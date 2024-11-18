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

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.DefaultConnectionProvider;
import org.assertj.db.exception.AssertJDBException;
import org.junit.Test;

/**
 * Tests on the instantiation of {@code Table}.
 * <p>
 * These tests are on the raise of some Exception depending of the case of test.
 * </p>
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class Table_Instantiation_Test extends AbstractTest {

  private final ConnectionProvider connectionProvider = new DefaultConnectionProvider(null);

  /**
   * This method should throw a {@code NullPointerException}, because the {@code ConnectionProvider} parameter is
   * {@code null}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void should_throw_IllegalArgumentException_if_instantiate_with_connection_provider_null() {
    new Table.Builder(null, null).build();
  }

  /**
   * This method should throw a {@code NullPointerException}, because the {@code ConnectionProvider} parameter is not
   * {@code null} and table name is {@code null}.
   */
  @Test(expected = IllegalArgumentException.class)
  public void should_throw_IllegalArgumentException_if_instantiate_with_connection_provider_not_null_and_table_name_null() {
    new Table.Builder(connectionProvider, null).build();
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code ConnectionProvider} field is set but not
   * all the information of the {@code ConnectionProvider}.
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_if_get_list_of_rows_with_setting_connection_provider_having_bad_user() {
    Table table = AssertDbConnectionFactory.of("jdbc:h2:mem:test", "", "").create().table("test").build();
    table.getRowsList();
  }

  /**
   * This method should throw a {@code NullPointerException}, because the columns to check contains a null name.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_if_one_of_the_columns_to_check_is_null() {
    assertDbConnection.table("movie").columnsToCheck(new String[]{"id", null, "birth"}).build();
  }

  /**
   * This method should throw a {@code NullPointerException}, because the columns to exclude contains a null name.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_if_one_of_the_columns_to_exclude_is_null() {
    assertDbConnection.table("movie").columnsToExclude(new String[]{null}).build();
  }
}
