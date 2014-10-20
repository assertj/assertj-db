package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;
import org.assertj.db.api.ValueType;

/**
 * Creates an error message indicating that an assertion that verifies that a value is of a type.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeType extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE = "\nExpecting:\n  <%s>\nto be of type\n"
      + "  <%s>\nbut was of type\n  <%s>";
  private static final String EXPECTED_MESSAGE_WITH_INDEX = "\nExpecting that the value at index %s:\n  <%s>\nto be of type\n"
      + "  <%s>\nbut was of type\n  <%s>";

  /**
   * Creates a new <code>{@link ShouldBeType}</code>.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected type.
   * @param tested The tested type.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeType(Object actual, ValueType expected, ValueType tested) {
    return new ShouldBeType(actual, expected, tested);
  }

  /**
   * Creates a new <code>{@link ShouldBeType}</code>.
   * 
   * @param index The index of the value.
   * @param actual The actual value in the failed assertion.
   * @param expected The expected type.
   * @param tested The tested type.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeType(int index, Object actual, ValueType expected, ValueType tested) {
    return new ShouldBeType(index, actual, expected, tested);
  }

  /**
   * Constructor.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected type.
   * @param tested The tested type.
   */
  public ShouldBeType(Object actual, ValueType expected, ValueType tested) {
    super(EXPECTED_MESSAGE, actual, expected, tested);
  }

  /**
   * Constructor.
   * 
   * @param index The index of the value.
   * @param actual The actual value in the failed assertion.
   * @param expected The expected type.
   * @param tested The tested type.
   */
  public ShouldBeType(int index, Object actual, ValueType expected, ValueType tested) {
    super(EXPECTED_MESSAGE_WITH_INDEX, index, actual, expected, tested);
  }
}
