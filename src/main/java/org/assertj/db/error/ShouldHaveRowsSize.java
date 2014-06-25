package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies the rows size a value failed.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldHaveRowsSize extends BasicErrorMessageFactory {

  /**
   * Creates a new </code>{@link ShouldHaveRowsSize}</code>.
   * 
   * @param actualSize the size of {@code actual}.
   * @param expectedSize the expected size.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldHaveRowsSize(int actualSize, int expectedSize) {
    return new ShouldHaveRowsSize(actualSize, expectedSize);
  }

  private ShouldHaveRowsSize(int actualSize, int expectedSize) {
    super("\nExpecting size of the rows to be equal to :\n   <%s>\nbut was:\n   <%s>", expectedSize, actualSize);
  }
}
