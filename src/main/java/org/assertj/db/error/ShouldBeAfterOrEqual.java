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
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeAfterOrEqual(final Object pActual, final Object pExpected) {
    return new ShouldBeAfterOrEqual(pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeAfterOrEqual(final Object pActual, final Object pExpected) {
    super("\nExpecting:\n  <%s>\nto be after or equal to \n  <%s>", pActual, pExpected);
  }
}
