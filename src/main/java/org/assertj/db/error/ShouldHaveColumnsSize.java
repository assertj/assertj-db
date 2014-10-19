package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies the columns size a value failed.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldHaveColumnsSize extends BasicErrorMessageFactory {

  /**
   * Creates a new </code>{@link ShouldHaveColumnsSize}</code>.
   * 
   * @param actualSize the size of {@code actual}.
   * @param expectedSize the expected size.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldHaveColumnsSize(int actualSize, int expectedSize) {
    return new ShouldHaveColumnsSize(actualSize, expectedSize);
  }

  private ShouldHaveColumnsSize(int actualSize, int expectedSize) {
    super("\nExpecting size (number of columns) to be equal to :\n   <%s>\nbut was:\n   <%s>", expectedSize, actualSize);
  }
}
