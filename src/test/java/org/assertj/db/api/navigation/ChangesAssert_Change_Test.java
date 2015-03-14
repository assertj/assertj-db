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
package org.assertj.db.api.navigation;

import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on the assertion methods on change of {@code Changes}.
 *
 * @author Régis Pouiller
 */
public class ChangesAssert_Change_Test extends AbstractTest {

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is less than the
   * minimum.
   * <p>
   * Message :
   * </p>
   * <p/>
   * <pre>
   * Index -1 out of the limits [0, 8[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_throw_AssertJDBException_because_index_is_less_than_the_minimum() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).change(-1);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is more than the
   * maximum.
   * <p>
   * Message :
   * </p>
   * <p/>
   * <pre>
   * Index 8 out of the limits [0, 8[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_throw_AssertJDBException_because_index_is_more_than_the_maximum() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).change(8);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because of reading after the last change.
   * <p>
   * Message :
   * </p>
   * <p/>
   * <pre>
   * Index 8 out of the limits [0, 8[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_throw_AssertJDBException_because_reading_after_the_end() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).change().returnToOrigin().change().returnToOrigin().change().returnToOrigin()
                       .change().returnToOrigin().change().returnToOrigin().change().returnToOrigin()
                       .change().returnToOrigin().change().returnToOrigin().change();
  }

  /**
   * This method tests the {@code change} method when using without parameter.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   */
  @Test
  @NeedReload
  public void test_when_get_changes_without_parameters()
          throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException,
          NoSuchMethodException, InvocationTargetException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangesAssert assertion = assertThat(changes);

    Method method = ChangesAssert.class.getDeclaredMethod("getIndexNextChange", ChangeType.class, String.class);
    Field field = ChangeAssert.class.getDeclaredField("change");
    method.setAccessible(true);
    field.setAccessible(true);

    assertThat(method.invoke(assertion, null, null)).isEqualTo(0);
    assertThat(field.get(assertion.change())).isSameAs(changes.getChangesList().get(0));
    assertThat(method.invoke(assertion, null, null)).isEqualTo(1);
    assertThat(field.get(assertion.change())).isSameAs(changes.getChangesList().get(1));
    assertThat(method.invoke(assertion, null, null)).isEqualTo(2);
    assertThat(field.get(assertion.change())).isSameAs(changes.getChangesList().get(2));
    assertThat(method.invoke(assertion, null, null)).isEqualTo(3);
  }

  /**
   * This method tests the {@code change} method when using with {@code index} parameter.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   */
  @Test
  @NeedReload
  public void test_when_get_changes_with_parameter()
          throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException,
          NoSuchMethodException, InvocationTargetException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangesAssert assertion = assertThat(changes);

    Method method = ChangesAssert.class.getDeclaredMethod("getIndexNextChange", ChangeType.class, String.class);
    Field field = ChangeAssert.class.getDeclaredField("change");
    method.setAccessible(true);
    field.setAccessible(true);

    assertThat(method.invoke(assertion, null, null)).isEqualTo(0);
    assertThat(field.get(assertion.change(2))).isSameAs(changes.getChangesList().get(2));
    assertThat(method.invoke(assertion, null, null)).isEqualTo(3);
    assertThat(field.get(assertion.change(1))).isSameAs(changes.getChangesList().get(1));
    assertThat(method.invoke(assertion, null, null)).isEqualTo(2);
    assertThat(field.get(assertion.change(0))).isSameAs(changes.getChangesList().get(0));
    assertThat(method.invoke(assertion, null, null)).isEqualTo(1);
  }

}
