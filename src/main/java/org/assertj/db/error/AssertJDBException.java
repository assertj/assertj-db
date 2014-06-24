package org.assertj.db.error;

/**
 * Exception during the assertion (for example : when getting the datas in the database, or accessing to file system).
 * 
 * @author Régis Pouiller
 * 
 */
public class AssertJDBException extends RuntimeException {

  /**
   * Serial version UID of the class.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Constructor of the Exception.
   * 
   * @param exception Exception inside this one.
   */
  public AssertJDBException(Exception exception) {
    super(exception);
  }

  /**
   * Constructor of the Exception.
   * 
   * @param message Message of the exception
   * @param objects Parameters of the message
   */
  public AssertJDBException(String message, Object... objects) {
    super(String.format(message, objects));
  }
}
