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

  /**
   * Creates a new <code>{@link ShouldBeType}</code>.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected type.
   * @param tested The tested type.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeType(final Object actual, final ValueType expected,
      final ValueType tested) {

    return new ShouldBeType(actual, expected, tested);
  }

  /**
   * Constructor.
   * 
   * @param actual The actual value in the failed assertion.
   * @param expected The expected type.
   * @param tested The tested type.
   */
  public ShouldBeType(final Object actual, final ValueType expected, final ValueType tested) {

    super("\nExpecting:\n  <%s>\nto be of type\n" + "  <%s>\nbut was of type\n  <%s>", actual, expected, tested);
  }
}
