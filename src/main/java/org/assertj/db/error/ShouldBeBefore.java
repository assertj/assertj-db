package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is before a value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeBefore extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldBeBefore}</code>.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeBefore(Object actual, Object expected) {
    return new ShouldBeBefore(actual, expected);
  }

  /**
   * Constructor.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  public ShouldBeBefore(Object actual, Object expected) {
    super("\nExpecting:\n  <%s>\nto be before \n  <%s>", actual, expected);
  }
}
