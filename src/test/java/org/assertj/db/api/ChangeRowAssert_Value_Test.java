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

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Row;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on the assertion methods on value of row of {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeRowAssert_Value_Test extends AbstractTest {

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is less than the
   * minimum.
   * <p>
   * Message :
   * </p>
   * <p/>
   * <pre>
   * Index -1 out of the limits [0, 4[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_throw_AssertJDBException_because_index_is_less_than_the_minimum() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).changeOfModification().rowAtStartPoint().value(-1);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is more than the
   * maximum.
   * <p>
   * Message :
   * </p>
   * <p/>
   * <pre>
   * Index 4 out of the limits [0, 4[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_throw_AssertJDBException_because_index_is_more_than_the_maximum() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).changeOfModification().rowAtStartPoint().value(4);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because of reading after the last change.
   * <p>
   * Message :
   * </p>
   * <p/>
   * <pre>
   * Index 4 out of the limits [0, 4[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_throw_AssertJDBException_because_reading_after_the_end() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).changeOfModification().rowAtStartPoint()
                       .value().returnToOriginAssert()
                       .value().returnToOriginAssert().value().returnToOriginAssert().value().returnToOriginAssert()
                       .value().returnToOriginAssert();
  }

  /**
   * This method tests the {@code rowAtStartPoint} and {@code rowAtEndPoint} methods.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_when_get_values_without_parameters()
          throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeRowAssert assertion = assertThat(changes).changeOfModification().rowAtStartPoint();
    Row rowAtStartPoint = changes.getChangesList().get(3).getRowAtStartPoint();

    Field field1 = ChangeRowAssert.class.getDeclaredField("indexNextValue");
    Field field2 = ChangeValueAssert.class.getDeclaredField("value");
    field1.setAccessible(true);
    field2.setAccessible(true);

    assertThat(field1.get(assertion)).isEqualTo(0);
    assertThat(field2.get(assertion.value())).isSameAs(rowAtStartPoint.getValuesList().get(0));
    assertThat(field1.get(assertion)).isEqualTo(1);
    assertThat(field2.get(assertion.value())).isSameAs(rowAtStartPoint.getValuesList().get(1));
    assertThat(field1.get(assertion)).isEqualTo(2);
    assertThat(field2.get(assertion.value())).isSameAs(rowAtStartPoint.getValuesList().get(2));
    assertThat(field1.get(assertion)).isEqualTo(3);
    assertThat(field2.get(assertion.value())).isSameAs(rowAtStartPoint.getValuesList().get(3));
  }

  /**
   * This method tests the {@code rowAtStartPoint} and {@code rowAtEndPoint} methods.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_when_get_values_with_parameters()
          throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeRowAssert assertion = assertThat(changes).changeOfModification().rowAtStartPoint();
    Row rowAtStartPoint = changes.getChangesList().get(3).getRowAtStartPoint();

    Field field1 = ChangeRowAssert.class.getDeclaredField("indexNextValue");
    Field field2 = ChangeValueAssert.class.getDeclaredField("value");
    field1.setAccessible(true);
    field2.setAccessible(true);

    assertThat(field1.get(assertion)).isEqualTo(0);
    assertThat(field2.get(assertion.value(0))).isSameAs(rowAtStartPoint.getValuesList().get(0));
    assertThat(field1.get(assertion)).isEqualTo(1);
    assertThat(field2.get(assertion.value(1))).isSameAs(rowAtStartPoint.getValuesList().get(1));
    assertThat(field1.get(assertion)).isEqualTo(2);
    assertThat(field2.get(assertion.value(2))).isSameAs(rowAtStartPoint.getValuesList().get(2));
    assertThat(field1.get(assertion)).isEqualTo(3);
    assertThat(field2.get(assertion.value(3))).isSameAs(rowAtStartPoint.getValuesList().get(3));
  }

  /**
   * This method should throw a {@code NullPointerException}, because the column name in parameter is null.
   */
  @Test(expected = NullPointerException.class)
  @NeedReload
  public void should_throw_NullPointerException_because_column_name_is_null() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).change().rowAtEndPoint().value(null);
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
  @NeedReload
  public void should_throw_AssertJDBException_because_column_dont_exist() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).change().rowAtEndPoint().value("notexist");
  }
}
