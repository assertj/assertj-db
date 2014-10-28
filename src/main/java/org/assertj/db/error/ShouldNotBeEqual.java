package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is not equal to another
 * value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldNotBeEqual extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE = "\nExpecting:\n  <%s>\nnot to be equal to: \n  <%s>";
  private static final String EXPECTED_MESSAGE_BUT_NOT = "\nExpecting to be not equal to value but was equal";

  /**
   * Creates a new <code>{@link ShouldNotBeEqual.java}</code>.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldNotBeEqual(Object actual, Object expected) {
    return new ShouldNotBeEqual(actual, expected);
  }

  /**
   * Creates a new <code>{@link ShouldNotBeEqual.java}</code>.
   * 
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldNotBeEqual() {
    return new ShouldNotBeEqual();
  }

  /**
   * Constructor.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  public ShouldNotBeEqual(Object actual, Object expected) {
    super(EXPECTED_MESSAGE, actual, expected);
  }

  /**
   * Constructor.
   */
  public ShouldNotBeEqual() {
    super(EXPECTED_MESSAGE_BUT_NOT);
  }
}
