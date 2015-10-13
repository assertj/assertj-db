package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;
import org.assertj.db.type.Value;

/**
 * Creates an error message indicating that an assertion that verifies that a value is compatible with a value.
 *
 * @author Régis Pouiller
 *
 */
public class ShouldBeCompatible extends BasicErrorMessageFactory {

  /**
   * Creates a new <code>{@link ShouldBeCompatible}</code>.
   *
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeCompatible(Value actual, Object expected) {
    if (expected == null) {
      return new ShouldBeCompatible(actual, expected);
    }
    return new ShouldBeCompatible(actual, expected.getClass(), expected);
  }

  /**
   * Constructor.
   *
   * @param actual The actual value in the failed assertion.
   * @param clazz The class of the expected value.
   * @param expected The expected value to compare to.
   */
  private ShouldBeCompatible(Value actual, Class<?> clazz, Object expected) {
    super("%nExpecting:%n  %s : <%s>%nto be compatible with %n  %s : <%s>", actual.getValueTypeRepresentation(),
          actual.getValue(), clazz, expected);
  }

  /**
   * Constructor.
   *
   * @param actual The actual value in the failed assertion.
   * @param expected The expected value to compare to.
   */
  private ShouldBeCompatible(Value actual, Object expected) {
    super("%nExpecting:%n  %s : <%s>%nto be compatible with %n  <%s>", actual.getValueTypeRepresentation(),
          actual.getValue(), expected);
  }
}
