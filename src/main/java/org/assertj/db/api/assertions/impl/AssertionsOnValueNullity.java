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
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Objects;
import org.assertj.db.api.AbstractAssert;

/**
 * Implements the assertion methods on the nullity of a value.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnValueNullity
 */
public class AssertionsOnValueNullity {

  /**
   * Assertions for {@code Object}s provided by assertj-core.
   */
  private static Objects objects = Objects.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnValueNullity() {
    // Empty
  }

  /**
   * Verifies that the value is {@code null}.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param value     The value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is not {@code null}.
   */
  public static <A extends AbstractAssert> A isNull(A assertion, WritableAssertionInfo info, Object value) {
    objects.assertNull(info, value);
    return assertion;
  }

  /**
   * Verifies that the value is not {@code null}.
   *
   * @param <A>       The type of the assertion which call this method.
   * @param assertion The assertion which call this method.
   * @param info      Info on the object to assert.
   * @param value     The value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the value is {@code null}.
   */
  public static <A extends AbstractAssert> A isNotNull(A assertion, WritableAssertionInfo info, Object value) {
    objects.assertNotNull(info, value);
    return assertion;
  }
}
