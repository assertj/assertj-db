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
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeAfter(final Object pActual, final Object pExpected) {
    return new ShouldBeAfter(pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeAfter(final Object pActual, final Object pExpected) {
    super("\nExpecting:\n  <%s>\nto be after \n  <%s>", pActual, pExpected);
  }
}
