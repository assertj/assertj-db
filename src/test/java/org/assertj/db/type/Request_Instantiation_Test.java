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
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.type;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.exception.AssertJDBException;
import org.junit.Test;

import javax.sql.DataSource;

/**
 * Tests on the instantiation of {@code Request}.
 * <p>
 * These tests are on the raise of some Exception depending of the case of test.
 * </p>
 * 
 * @author Régis Pouiller
 * 
 */
public class Request_Instantiation_Test extends AbstractTest {

  /**
   * This method should throw a {@code NullPointerException}, because the {@code Source} parameter is {@code null}.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_if_instantiate_with_Source_null() {
    new Request((Source) null, null);
  }

  /**
   * This method should throw a {@code NullPointerException}, because the {@code DataSource} parameter is {@code null}.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_if_instantiate_with_DataSource_null() {
    new Request((DataSource) null, null);
  }

  /**
   * This method should throw a {@code NullPointerException}, because the {@code Source} parameter is not {@code null}
   * and request is {@code null}.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_if_instantiate_with_Source_not_null_and_table_name_null() {
    new Request(source, null);
  }

  /**
   * This method should throw a {@code NullPointerException}, because the {@code DataSource} parameter is {@code null}
   * and request is {@code null}.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_if_instantiate_with_DataSource_not_null_and_table_name_null() {
    new Request(dataSource, null);
  }

  /**
   * This method should throw a {@code NullPointerException}, because the {@code DataSource} and the {@code Source}
   * fields are not set when call {@code getColumnsNameList()}.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_if_get_list_of_columns_name_without_setting_source_or_datasource() {
    new Request().getColumnsNameList();
  }

  /**
   * This method should throw a {@code NullPointerException}, because the {@code Source} field is set but not the table
   * name when call {@code getColumnsNameList()}.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_if_get_list_of_columns_name_with_setting_source_and_without_setting_table_name() {
    new Request().setSource(source).getColumnsNameList();
  }

  /**
   * This method should throw a {@code NullPointerException}, because the {@code DataSource} field is set but not the
   * table name when call {@code getColumnsNameList()}.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_if_get_list_of_columns_name_with_setting_datasource_and_without_setting_table_name() {
    new Request().setDataSource(dataSource).getColumnsNameList();
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code Source} field is set but not
   * all the information of the {@code Source}.
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_if_get_list_of_rows_with_setting_source_having_bad_user() {
    Request request = new Request(new Source(source.getUrl(), "", ""), "");
    request.getRowsList();
  }
}
