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
import org.assertj.db.type.Request;
import org.junit.Test;

/**
 * Test on the assertion methods on value of {@code Row} for {@code Request}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestRowAssert_Value_Test extends AbstractTest {

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is less than the
   * minimum.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Index -1 out of the limits [0, 4[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_index_is_less_than_the_minimum() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request).row().value(-1);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is more than the
   * maximum.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Index 4 out of the limits [0, 4[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_index_is_more_than_the_maximum() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request).row().value(4);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because of reading after the last column.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Index 4 out of the limits [0, 4[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_reading_after_the_end() {
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request)
        .row()
            .value().returnToRow()
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
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request).row().value(null);
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
    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    assertThat(request).row().value("notexist");
  }

  /**
   * This method tests the {@code getColumn} method when using without parameter.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_when_get_rows_without_parameters() throws IllegalArgumentException, IllegalAccessException,
      SecurityException, NoSuchFieldException {

    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    RequestRowAssert assertion = assertThat(request).row();

    Field field = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    Field field2 = AbstractValueAssert.class.getDeclaredField("value");
    field.setAccessible(true);
    field2.setAccessible(true);

    assertThat(field.getInt(assertion)).isEqualTo(0);
    assertThat(field2.get(assertion.value())).isSameAs(request.getRow(0).getValuesList().get(0));
    assertThat(field.getInt(assertion)).isEqualTo(1);
    assertThat(field2.get(assertion.value())).isSameAs(request.getRow(0).getValuesList().get(1));
    assertThat(field.getInt(assertion)).isEqualTo(2);
    assertThat(field2.get(assertion.value())).isSameAs(request.getRow(0).getValuesList().get(2));
    assertThat(field.getInt(assertion)).isEqualTo(3);
    assertThat(field2.get(assertion.value())).isSameAs(request.getRow(0).getValuesList().get(3));
    assertThat(field.getInt(assertion)).isEqualTo(4);
  }

  /**
   * This method tests the {@code getColumn} method when using with {@code index} parameter.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_when_get_rows_with_index_parameter() throws IllegalArgumentException, IllegalAccessException,
      SecurityException, NoSuchFieldException {

    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    RequestRowAssert assertion = assertThat(request).row();

    Field field = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    Field field2 = AbstractValueAssert.class.getDeclaredField("value");
    field.setAccessible(true);
    field2.setAccessible(true);

    assertThat(field.getInt(assertion)).isEqualTo(0);
    assertThat(field2.get(assertion.value(3))).isSameAs(request.getRow(0).getValuesList().get(3));
    assertThat(field.getInt(assertion)).isEqualTo(4);
    assertThat(field2.get(assertion.value(2))).isSameAs(request.getRow(0).getValuesList().get(2));
    assertThat(field.getInt(assertion)).isEqualTo(3);
    assertThat(field2.get(assertion.value(1))).isSameAs(request.getRow(0).getValuesList().get(1));
    assertThat(field.getInt(assertion)).isEqualTo(2);
    assertThat(field2.get(assertion.value(0))).isSameAs(request.getRow(0).getValuesList().get(0));
    assertThat(field.getInt(assertion)).isEqualTo(1);
  }

  /**
   * This method tests the {@code getColumn} method when using with {@code columnName} parameter.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_when_get_rows_with_column_name_parameter() throws IllegalArgumentException, IllegalAccessException,
      SecurityException, NoSuchFieldException {

    Request request = new Request(source, "SELECT actor.name, actor.firstname, movie.year, interpretation.character "
        + " FROM movie, actor, interpretation"
        + " WHERE movie.id = interpretation.id_movie"
        + " AND interpretation.id_actor = actor.id"
        + " AND movie.year > ?"
        + " ORDER BY actor.name, movie.year", 2000);
    RequestRowAssert assertion = assertThat(request).row();

    Field field = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    Field field2 = AbstractValueAssert.class.getDeclaredField("value");
    field.setAccessible(true);
    field2.setAccessible(true);

    assertThat(field.getInt(assertion)).isEqualTo(0);
    assertThat(field2.get(assertion.value("character"))).isSameAs(request.getRow(0).getValuesList().get(3));
    assertThat(field.getInt(assertion)).isEqualTo(4);
    assertThat(field2.get(assertion.value("YEAR"))).isSameAs(request.getRow(0).getValuesList().get(2));
    assertThat(field.getInt(assertion)).isEqualTo(3);
    assertThat(field2.get(assertion.value("firstname"))).isSameAs(request.getRow(0).getValuesList().get(1));
    assertThat(field.getInt(assertion)).isEqualTo(2);
    assertThat(field2.get(assertion.value("name"))).isSameAs(request.getRow(0).getValuesList().get(0));
    assertThat(field.getInt(assertion)).isEqualTo(1);
  }

}
