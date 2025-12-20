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
package org.assertj.db.api.assertions.impl;

import static org.assertj.db.error.ShouldBeCompatible.shouldBeCompatible;
import static org.assertj.db.error.ShouldBeEqual.shouldBeEqual;
import static org.assertj.db.error.ShouldHaveColumnsSize.shouldHaveColumnsSize;
import static org.assertj.db.error.ShouldSatisfy.shouldSatisfy;
import static org.assertj.db.util.Values.areEqual;

import java.util.List;

import org.assertj.core.api.Condition;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.type.Value;
import org.assertj.db.type.ValueType;
import org.assertj.db.util.Values;

/**
 * Implements the assertion method on the matching with condition of a row.
 *
 * @author Julien Roy
 * @see org.assertj.db.api.assertions.AssertOnRowCondition
 */
public class AssertionsOnRowCondition {

  /**
   * To notice failures in the assertion.
   */
  private static final Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnRowCondition() {
    // Empty
  }

  /**
   * Verifies that the values of a row satisfy to conditions in parameter.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The actual value to validate.
   * @param expected   The expected conditions.
   * @return {@code this} assertion object.
   * @throws AssertionError If the columns of the primary key are different to the names in parameters.
   */
  @SuppressWarnings("unchecked")
  public static <A extends AbstractAssert<?>> A hasValuesSatisfying(A assertion, WritableAssertionInfo info,
                                                                    List<Value> valuesList, Object... expected) {

    if (valuesList.size() != expected.length) {
      throw failures.failure(info, shouldHaveColumnsSize(valuesList.size(), expected.length));
    }

    int index = 0;
    for (Value value : valuesList) {
      Object object = expected[index];

      if (object instanceof Condition) {
        Condition<Object> condition = (Condition<Object>) object;
        if (!condition.matches(value.getValue())) {
          Object actual = Values.getRepresentationFromValueInFrontOfExpected(value, object);
          throw failures.failure(info, shouldSatisfy(index, actual, condition));
        }
        index++;
        continue;
      }

      if (!value.isComparisonPossible(object)) {
        throw failures.failure(info, shouldBeCompatible(value, object));
      }

      if (!areEqual(value, object)) {
        if (value.getValueType() == ValueType.BYTES) {
          throw failures.failure(info, shouldBeEqual(index));
        } else {
          Object actual = Values.getRepresentationFromValueInFrontOfExpected(value, object);
          throw failures.failure(info, shouldBeEqual(index, actual, object));
        }
      }

      index++;
    }

    return assertion;
  }
}
