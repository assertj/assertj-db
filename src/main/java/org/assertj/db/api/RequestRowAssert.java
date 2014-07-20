package org.assertj.db.api;

import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Request;
import org.assertj.db.type.Row;
import org.assertj.db.type.Table;

/**
 * Assertion methods about the data in a {@link Row} of a {@link Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestRowAssert extends
    AbstractRowAssert<RequestAssert, Request, RequestRowAssert, RequestRowValueAssert> {

  /**
   * Constructor.
   * 
   * @param originalRequestAssert The original assertion ({@link RequestAssert}).
   * @param row The row on which do assertion.
   */
  RequestRowAssert(RequestAssert originalRequestAssert, Row row) {
    super(originalRequestAssert, RequestRowAssert.class, RequestRowValueAssert.class, row);
  }

  /**
   * Returns to level of assertion methods on a {@link Request}.
   * 
   * @return a object of assertion methods on a {@link Request}.
   */
  public RequestAssert returnToRequest() {
    return returnToDbAssert();
  }

  /**
   * Returns assertion methods on the value corresponding to the column name in parameter.
   * 
   * @param columnName The column name.
   * @return An object to make assertions on the value.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws AssertJDBException If there is no column with this name.
   */
  public RequestRowValueAssert value(String columnName) {
    return new RequestRowValueAssert(this, getValue(columnName));
  }

}
