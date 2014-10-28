package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is less than or equal to another
 * value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeLessOrEqual extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldBeLessOrEqual}</code>.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeLessOrEqual(Object actual, Object expected) {
    return new ShouldBeLessOrEqual(actual, expected);
  }

  /**
   * Constructor.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  public ShouldBeLessOrEqual(Object actual, Object expected) {
    super("\nExpecting:\n  <%s>\nto be less than or equal to \n  <%s>", actual, expected);
  }
}
