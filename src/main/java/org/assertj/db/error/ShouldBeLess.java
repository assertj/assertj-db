package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is less than another value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeLess extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldBeLess}</code>.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeLess(Object actual, Object expected) {
    return new ShouldBeLess(actual, expected);
  }

  /**
   * Constructor.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  public ShouldBeLess(Object actual, Object expected) {
    super("\nExpecting:\n  <%s>\nto be less than \n  <%s>", actual, expected);
  }
}
