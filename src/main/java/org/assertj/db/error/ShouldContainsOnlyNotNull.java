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
   * @param index The index of value in the failed assertion.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldContainsOnlyNotNull(int index) {
    return new ShouldContainsOnlyNotNull(index);
  }

  /**
   * Constructor.
   * 
   * @param index The index of value in the failed assertion.
   */
  public ShouldContainsOnlyNotNull(int index) {
    super("\nExpecting to contain only not null:\nbut contains null at index: %s", index);
  }
}
