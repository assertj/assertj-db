package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is null.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldContainsOnlyNotNull extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldContainsOnlyNotNull}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldContainsOnlyNotNull(final Object pActual) {
    return new ShouldContainsOnlyNotNull(pActual);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   */
  public ShouldContainsOnlyNotNull(final Object pActual) {
    super("\nExpecting:\n  <%s>\nto not contain null", pActual);
  }
}
