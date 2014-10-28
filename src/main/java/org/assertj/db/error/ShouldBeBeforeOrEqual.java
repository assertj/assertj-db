package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is before or equal a value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeBeforeOrEqual extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldBeBeforeOrEqual}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeBeforeOrEqual(Object pActual, Object pExpected) {
    return new ShouldBeBeforeOrEqual(pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeBeforeOrEqual(Object pActual, Object pExpected) {
    super("\nExpecting:\n  <%s>\nto be before or equal to \n  <%s>", pActual, pExpected);
  }
}
