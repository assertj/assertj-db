package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is greater than another value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeGreater extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldBeGreater}</code>.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeGreater(Object actual, Object expected) {
    return new ShouldBeGreater(actual, expected);
  }

  /**
   * Constructor.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  public ShouldBeGreater(Object actual, Object expected) {
    super("\nExpecting:\n  <%s>\nto be greater than \n  <%s>", actual, expected);
  }
}
