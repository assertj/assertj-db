package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is before a value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBefore extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldBefore}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBefore(final Object pActual, final Object pExpected) {

    return new ShouldBefore(pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBefore(final Object pActual, final Object pExpected) {
    super("\nExpecting:\n  <%s>\nto be before \n  <%s>", pActual, pExpected);
  }
}
