package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.exception.AssertJDBException;

import static org.assertj.db.error.ShouldBeValueClass.shouldBeValueClass;

/**
 * Implements the assertion method on the class of a value.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnValueClass
 * @since 1.1.0
 */
public class AssertionsOnValueClass {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnValueClass() {
    // Empty
  }

  /**
   * Verifies that the class of the value is equal to the class in parameter.
   *
   * @param <A>          The type of the assertion which call this method.
   * @param assertion    The assertion which call this method.
   * @param info         Writable information about an assertion.
   * @param value        The value.
   * @param classOfValue The expected class to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the class of the value is different to the class in parameter.
   * @since 1.1.0
   */
  public static <A extends AbstractAssert> A isOfClass(A assertion, WritableAssertionInfo info, Object value,
                                                       Class classOfValue) {

    AssertionsOnValueNullity.isNotNull(assertion, info, value);
    if (classOfValue == null) {
      throw new AssertJDBException("Class of the value is null");
    }
    Class testedClass = value.getClass();
    if (!classOfValue.isAssignableFrom(testedClass)) {
      throw failures.failure(info, shouldBeValueClass(value, testedClass, classOfValue));
    }
    return assertion;
  }
}
