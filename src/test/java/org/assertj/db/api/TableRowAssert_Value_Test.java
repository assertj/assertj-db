/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

import java.lang.reflect.Field;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the assertion methods on value of {@code Row} for {@code Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableRowAssert_Value_Test extends AbstractTest {

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is less than the
   * minimum.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Index -1 out of the limits [0, 3[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_index_is_less_than_the_minimum() {
    Table table = new Table(source, "movie");
    assertThat(table).row().value(-1);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is more than the
   * maximum.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Index 3 out of the limits [0, 3[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_index_is_more_than_the_maximum() {
    Table table = new Table(source, "movie");
    assertThat(table).row().value(3);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because of reading after the last row.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Index 3 out of the limits [0, 3[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_reading_after_the_end() {
    Table table = new Table(source, "movie");
    assertThat(table)
        .row()
            .value().returnToRow()
            .value().returnToRow()
            .value().returnToRow()
            .value();
  }

  /**
   * This method should throw a {@code NullPointerException}, because the column name in parameter is null.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_because_column_name_is_null() {
    Table table = new Table(source, "movie");
    assertThat(table).row().value(null);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the column name in parameter don't exist in the list
   * of columns.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Column &lt;notexist> does not exist
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_column_dont_exist() {
    Table table = new Table(source, "movie");
    assertThat(table).row().value("notexist");
  }

  /**
   * This method tests the {@code row} method when using without parameter.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_when_get_rows_without_parameters() throws IllegalArgumentException, IllegalAccessException,
      SecurityException, NoSuchFieldException {

    Table table = new Table(source, "movie");
    TableRowAssert assertion = assertThat(table).row();

    Field field = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    Field field2 = AbstractValueAssert.class.getDeclaredField("value");
    field.setAccessible(true);
    field2.setAccessible(true);

    assertThat(field.getInt(assertion)).isEqualTo(0);
    assertThat(field2.get(assertion.value())).isSameAs(table.getRow(0).getValuesList().get(0));
    assertThat(field.getInt(assertion)).isEqualTo(1);
    assertThat(field2.get(assertion.value())).isSameAs(table.getRow(0).getValuesList().get(1));
    assertThat(field.getInt(assertion)).isEqualTo(2);
    assertThat(field2.get(assertion.value())).isSameAs(table.getRow(0).getValuesList().get(2));
    assertThat(field.getInt(assertion)).isEqualTo(3);
  }

  /**
   * This method tests the {@code getRow} method when using with {@code index} parameter.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_when_get_rows_with_parameter() throws IllegalArgumentException, IllegalAccessException,
      SecurityException, NoSuchFieldException {

    Table table = new Table(source, "movie");
    TableRowAssert assertion = assertThat(table).row();

    Field field = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    Field field2 = AbstractValueAssert.class.getDeclaredField("value");
    field.setAccessible(true);
    field2.setAccessible(true);

    assertThat(field.getInt(assertion)).isEqualTo(0);
    assertThat(field2.get(assertion.value(2))).isSameAs(table.getRow(0).getValuesList().get(2));
    assertThat(field.getInt(assertion)).isEqualTo(3);
    assertThat(field2.get(assertion.value(1))).isSameAs(table.getRow(0).getValuesList().get(1));
    assertThat(field.getInt(assertion)).isEqualTo(2);
    assertThat(field2.get(assertion.value(0))).isSameAs(table.getRow(0).getValuesList().get(0));
    assertThat(field.getInt(assertion)).isEqualTo(1);
  }

}
