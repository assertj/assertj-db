package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;

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
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldNotBeEqual(final String pActual, final String pExpected) {
    return new ShouldNotBeEqual(pActual, pExpected);
  }

  /**
   * Creates a new <code>{@link ShouldNotBeEqual.java}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldNotBeEqual(final Boolean pActual, final Boolean pExpected) {
    return new ShouldNotBeEqual(pActual, pExpected);
  }

  /**
   * Creates a new <code>{@link ShouldNotBeEqual.java}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldNotBeEqual(final Number pActual, final Object pExpected) {
    return new ShouldNotBeEqual(pActual, pExpected);
  }

  /**
   * Creates a new <code>{@link ShouldNotBeEqual.java}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldNotBeEqual(final DateValue pActual, final DateValue pExpected) {
    return new ShouldNotBeEqual(pActual, pExpected);
  }

  /**
   * Creates a new <code>{@link ShouldNotBeEqual.java}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldNotBeEqual(final TimeValue pActual, final TimeValue pExpected) {
    return new ShouldNotBeEqual(pActual, pExpected);
  }

  /**
   * Creates a new <code>{@link ShouldNotBeEqual.java}</code>.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldNotBeEqual(final DateTimeValue pActual, final DateTimeValue pExpected) {
    return new ShouldNotBeEqual(pActual, pExpected);
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
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldNotBeEqual(final String pActual, final String pExpected) {
    super(EXPECTED_MESSAGE, pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldNotBeEqual(final Boolean pActual, final Boolean pExpected) {
    super(EXPECTED_MESSAGE, pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldNotBeEqual(final Number pActual, final Object pExpected) {
    super(EXPECTED_MESSAGE, pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldNotBeEqual(final DateValue pActual, final DateValue pExpected) {
    super(EXPECTED_MESSAGE, pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldNotBeEqual(final TimeValue pActual, final TimeValue pExpected) {
    super(EXPECTED_MESSAGE, pActual, pExpected);
  }

  /**
   * Constructor.
   * 
   * @param pActual The actual value in the failed assertion.
   * @param pExpected The expected value to compare to.
   */
  public ShouldNotBeEqual(final DateTimeValue pActual, final DateTimeValue pExpected) {
    super(EXPECTED_MESSAGE, pActual, pExpected);
  }

  /**
   * Constructor.
   */
  public ShouldNotBeEqual() {
    super(EXPECTED_MESSAGE_BUT_NOT);
  }
}
