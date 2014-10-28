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
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeLess(Object pActual, Object pExpected) {
    return new ShouldBeLess(pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeLess(Object pActual, Object pExpected) {
    super("\nExpecting:\n  <%s>\nto be less than \n  <%s>", pActual, pExpected);
  }
}
