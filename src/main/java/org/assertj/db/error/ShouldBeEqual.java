package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;

/**
 * Creates an error message indicating that an assertion that verifies that a value is equal to another
 * value.
 * 
 * @author Régis Pouiller
 * 
 */
public class ShouldBeEqual extends BasicErrorMessageFactory {

  private static final String EXPECTED_MESSAGE = "\nExpecting:\n  <%s>\nto be equal to: \n  <%s>";
  private static final String EXPECTED_MESSAGE_BUT_NOT = "\nExpecting to be equal to value but was not equal";

  /**
   * Creates a new <code>{@link ShouldBeEqual.java}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual(final String pActual, final String pExpected) {
    return new ShouldBeEqual(pActual, pExpected);
  }

  /**
   * Creates a new <code>{@link ShouldBeEqual.java}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual(final Boolean pActual, final Boolean pExpected) {
    return new ShouldBeEqual(pActual, pExpected);
  }

  /**
   * Creates a new <code>{@link ShouldBeEqual.java}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual(final Number pActual, final Object pExpected) {
    return new ShouldBeEqual(pActual, pExpected);
  }

  /**
   * Creates a new <code>{@link ShouldBeEqual.java}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual(final DateValue pActual, final DateValue pExpected) {
    return new ShouldBeEqual(pActual, pExpected);
  }

  /**
   * Creates a new <code>{@link ShouldBeEqual.java}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual(final TimeValue pActual, final TimeValue pExpected) {
    return new ShouldBeEqual(pActual, pExpected);
  }

  /**
   * Creates a new <code>{@link ShouldBeEqual.java}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeEqual(final DateTimeValue pActual, final DateTimeValue pExpected) {
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
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeEqual(final String pActual, final String pExpected) {
    super(EXPECTED_MESSAGE, pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeEqual(final Boolean pActual, final Boolean pExpected) {
    super(EXPECTED_MESSAGE, pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeEqual(final Number pActual, final Object pExpected) {
    super(EXPECTED_MESSAGE, pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeEqual(final DateValue pActual, final DateValue pExpected) {
    super(EXPECTED_MESSAGE, pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeEqual(final TimeValue pActual, final TimeValue pExpected) {
    super(EXPECTED_MESSAGE, pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldBeEqual(final DateTimeValue pActual, final DateTimeValue pExpected) {
    super(EXPECTED_MESSAGE, pActual, pExpected);
  }

  /**
   * Constructor.
   */
  public ShouldBeEqual() {
    super(EXPECTED_MESSAGE_BUT_NOT);
  }
}
