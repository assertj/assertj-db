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
   * @param pIndex The index of value in the failed assertion.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldContainsOnlyNull(int pIndex) {
    return new ShouldContainsOnlyNull(pIndex);
  }

  /**
   * Constructor.
   * 
   * @param pIndex The index of value in the failed assertion.
   */
  public ShouldContainsOnlyNull(int pIndex) {
    super("\nExpecting to contain only null:\nbut contains not null at index: %s", pIndex);
  }
}
