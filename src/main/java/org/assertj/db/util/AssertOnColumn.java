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
package org.assertj.db.util;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.error.ShouldBeValueType;
import org.assertj.db.type.ValueType;

import java.util.List;

import static org.assertj.db.error.ShouldBeValueTypeOfAny.shouldBeValueTypeOfAny;
import static org.assertj.db.error.ShouldHaveName.shouldHaveName;
import static org.assertj.db.error.ShouldHaveRowsSize.shouldHaveRowsSize;

/**
 * Utility methods related to assert on column.
 *
 * @author Régis Pouiller
 */
public class AssertOnColumn {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertOnColumn() {
    // Empty
  }

  /**
   * Verifies that the size of a {@link org.assertj.db.type.Column} is equal to the number in parameter.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param size      The size of the column.
   * @param expected  The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the size is different to the number in parameter.
   */
  public static <A extends AbstractAssert> A hasSize(A assertion, WritableAssertionInfo info, int size, int expected) {
    if (size != expected) {
      throw failures.failure(info, shouldHaveRowsSize(size, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the name of a column is equal to parameter.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Info on the object to assert.
   * @param columnName The column name.
   * @param expected   The expected column name.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column name is not equal to the parameter.
   */
  public static <A extends AbstractAssert> A hasColumnName(A assertion, WritableAssertionInfo info, String columnName,
                                                           String expected) {
    if (expected == null) {
      throw new NullPointerException("Column name must be not null");
    }
    if (!expected.equalsIgnoreCase(columnName)) {
      throw failures.failure(info, shouldHaveName(columnName, expected));
    }
    return assertion;
  }

  /**
   * Verifies that the type of the values of the column is equal to the type in parameter.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Info on the object to assert.
   * @param valuesList The list of values.
   * @param expected The expected type to compare to.
   * @param lenient {@code true} if the test is lenient : if the type of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public static <A extends AbstractAssert> A isOfType(A assertion, WritableAssertionInfo info, List<Object> valuesList, ValueType expected, boolean lenient) {
    if (lenient) {
      return isOfAnyOfTypes(assertion, info, valuesList, expected, ValueType.NOT_IDENTIFIED);
    }

    int index = 0;
    for (Object value : valuesList) {
      ValueType type = ValueType.getType(value);
      if (type != expected) {
        throw failures.failure(info, ShouldBeValueType.shouldBeValueType(index, value, expected, type));
      }
      index++;
    }
    return assertion;
  }

  /**
   * Verifies that the type of the column is equal to one of the types in parameters.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Info on the object to assert.
   * @param valuesList The list of values.
   * @param expected The expected types to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to all the types in parameters.
   */
  public static <A extends AbstractAssert> A isOfAnyOfTypes(A assertion, WritableAssertionInfo info, List<Object> valuesList, ValueType... expected) {
    int index = 0;
    loop: for (Object value : valuesList) {
      ValueType type = ValueType.getType(value);
      for (ValueType valueType : expected) {
        if (type == valueType) {
          index++;
          continue loop;
        }
      }
      throw failures.failure(info, shouldBeValueTypeOfAny(index, value, type, expected));
    }
    return assertion;
  }

}
