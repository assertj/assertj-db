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
   * @param pIndex The index of value in the failed assertion.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldContainsOnlyNotNull(int pIndex) {
    return new ShouldContainsOnlyNotNull(pIndex);
  }

  /**
   * Constructor.
   * 
   * @param pIndex The index of value in the failed assertion.
   */
  public ShouldContainsOnlyNotNull(int pIndex) {
    super("\nExpecting to contain only not null:\nbut contains null at index: %s", pIndex);
  }
}
