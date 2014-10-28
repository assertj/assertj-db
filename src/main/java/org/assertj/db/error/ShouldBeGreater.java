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
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeGreater(Object pActual, Object pExpected) {
    return new ShouldBeGreater(pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeGreater(Object pActual, Object pExpected) {
    super("\nExpecting:\n  <%s>\nto be greater than \n  <%s>", pActual, pExpected);
  }
}
