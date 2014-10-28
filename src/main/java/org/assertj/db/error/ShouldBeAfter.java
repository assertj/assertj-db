package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is after a value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeAfter extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldBeAfter}</code>.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeAfter(Object actual, Object expected) {
    return new ShouldBeAfter(actual, expected);
  }

  /**
   * Constructor.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  public ShouldBeAfter(Object actual, Object expected) {
    super("\nExpecting:\n  <%s>\nto be after \n  <%s>", actual, expected);
  }
}
