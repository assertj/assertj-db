package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.exception.AssertJDBException;

import java.util.List;

import static org.assertj.db.error.ShouldBeValueClass.shouldBeValueClass;

/**
 * Implements the assertion methods on the class of a column.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnColumnClass
 */
public class AssertionsOnColumnClass {

  /**
   * To notice failures in the assertion.
   */
  private final static Failures failures = Failures.instance();

  /**
   * Private constructor.
   */
  private AssertionsOnColumnClass() {
    // Empty
  }

  /**
   * Verifies that the class of the values of the column is equal to the class in parameter.
   *
   * @param <A>        The type of the assertion which call this method.
   * @param assertion  The assertion which call this method.
   * @param info       Writable information about an assertion.
   * @param valuesList The list of values.
   * @param expected The expected class to compare to.
   * @param lenient {@code true} if the test is lenient : if the class of a value is not identified (for example when the
   *          value is {@code null}), it consider that it is ok.
   * @return {@code this} assertion object.
   * @throws AssertionError If the class of the column is different to the class in parameter.
   * @since 1.1.0
   */
  public static <A extends AbstractAssert> A isOfClass(A assertion, WritableAssertionInfo info, List<Object> valuesList,
                                                       Class<?> expected, boolean lenient) {

    if (expected == null) {
      throw new AssertJDBException("Class of the column is null");
    }

    int index = 0;
    for (Object value : valuesList) {
      if (value == null || !expected.isAssignableFrom(value.getClass())) {
        if (!lenient || value != null) {
          throw failures.failure(info, shouldBeValueClass(index, value, expected));
        }
      }
      index++;
    }
    return assertion;
  }
}
