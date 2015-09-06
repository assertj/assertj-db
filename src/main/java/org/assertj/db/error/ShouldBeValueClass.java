package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is of a type.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public class ShouldBeValueClass extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE =
          "%nExpecting:%n  <%s>%nto be of class%n  <%s>%nbut was of class%n  <%s>";

  /**
   * Creates a new <code>{@link ShouldBeValueClass}</code>.
   *
   * @param actual The actual value in the failed assertion.
   * @param tested The tested class.
   * @param expected The expected class.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeValueClass(Object actual, Class tested, Class expected) {
    return new ShouldBeValueClass(actual, tested, expected);
  }

  /**
   * Constructor.
   *
   * @param actual The actual value in the failed assertion.
   * @param tested The tested class.
   * @param expected The expected class.
   */
  private ShouldBeValueClass(Object actual, Class tested, Class expected) {
    super(EXPECTED_MESSAGE, actual, expected, tested);
  }
}
