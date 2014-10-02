package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is not null.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldContainsOnlyNull extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldContainsOnlyNull}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldContainsOnlyNull(final Object pActual) {
    return new ShouldContainsOnlyNull(pActual);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   */
  public ShouldContainsOnlyNull(final Object pActual) {
    super("\nExpecting:\n  <%s>\nto not contain only null", pActual);
  }
}
