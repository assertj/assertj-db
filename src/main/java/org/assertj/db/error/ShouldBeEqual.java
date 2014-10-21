package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value is equal to another value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeEqual extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE = "\nExpecting:\n  <%s>\nto be equal to: \n  <%s>";
  private static final String EXPECTED_MESSAGE_BUT_NOT = "\nExpecting to be equal to the expected value but was not equal";
  private static final String EXPECTED_MESSAGE_WITH_INDEX = "\nExpecting that the value at index %s:\n  <%s>\nto be equal to: \n  <%s>";
  private static final String EXPECTED_MESSAGE_BUT_NOT_WITH_INDEX = "\nExpecting that the value at index %s to be equal to the expected value but was not equal";

  /**
   * Creates a new <code>{@link ShouldBeEqual.java}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual(final Object pActual, final Object pExpected) {
    return new ShouldBeEqual(pActual, pExpected);
  }

  /**
   * Creates a new <code>{@link ShouldBeEqual.java}</code>.
   * 
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual() {
    return new ShouldBeEqual();
  }

  /**
   * Creates a new <code>{@link ShouldBeEqual.java}</code>.
   * 
   * @param index The index of the value.
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual(final int index, final Object pActual, final Object pExpected) {
    return new ShouldBeEqual(index, pActual, pExpected);
  }

  /**
   * Creates a new <code>{@link ShouldBeEqual.java}</code>.
   * 
   * @param index The index of the value.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual(final int index) {
    return new ShouldBeEqual(index);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeEqual(final Object pActual, final Object pExpected) {
    super(EXPECTED_MESSAGE, pActual, pExpected);
  }

  /**
   * Constructor.
   */
  public ShouldBeEqual() {
    super(EXPECTED_MESSAGE_BUT_NOT);
  }

  /**
   * Constructor.
   * 
   * @param index The index of the value.
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeEqual(final int index, final Object pActual, final Object pExpected) {
    super(EXPECTED_MESSAGE_WITH_INDEX, index, pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param index The index of the value.
   */
  public ShouldBeEqual(final int index) {
    super(EXPECTED_MESSAGE_BUT_NOT_WITH_INDEX, index);
  }
}
