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
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeLessOrEqual(Object pActual, Object pExpected) {
    return new ShouldBeLessOrEqual(pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeLessOrEqual(Object pActual, Object pExpected) {
    super("\nExpecting:\n  <%s>\nto be less than or equal to \n  <%s>", pActual, pExpected);
  }
}
