package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is after or equal a value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeAfterOrEqual extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldBeAfterOrEqual}</code>.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeAfterOrEqual(Object actual, Object expected) {
    return new ShouldBeAfterOrEqual(actual, expected);
  }

  /**
   * Constructor.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  public ShouldBeAfterOrEqual(Object actual, Object expected) {
    super("\nExpecting:\n  <%s>\nto be after or equal to \n  <%s>", actual, expected);
  }
}
