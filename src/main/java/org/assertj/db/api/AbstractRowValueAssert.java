package org.assertj.db.api;

import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Row;

/**
 * Assertion methods about a value in a {@link Row}.
 * 
 * @author Régis Pouiller
 * 
 * @param <E> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <D> The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <C> The class of the equivalent row assert (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractColumnValueAssert}).
 * @param <R> The class of this assert (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of this assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public class AbstractRowValueAssert<E extends AbstractDbData<E>, D extends AbstractDbAssert<E, D>, C extends AbstractColumnAssert<E, D, C, CV, R, RV>, CV extends AbstractColumnValueAssert<E, D, C, CV, R, RV>, R extends AbstractRowAssert<E, D, C, CV, R, RV>, RV extends AbstractRowValueAssert<E, D, C, CV, R, RV>>
    extends AbstractValueAssert<E, D, R, RV, C, CV, R, RV> {

  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param selfType Class of this assert (the value assert) : a sub-class of {@code AbstractValueAssert}.
   * @param actualValue The value to assert.
   */
  AbstractRowValueAssert(R originalAssert, Class<RV> selfType, Object actualValue) {
    super(originalAssert, selfType, actualValue);
  }

  /**
   * Returns assertion methods on the value corresponding to the column name in parameter in the list of value of the
   * original assertion.
   * 
   * @param columnName The column name.
   * @return An object to make assertions on the value.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws AssertJDBException If there is no column with this name.
   */
  public RV value(String columnName) {
    return returnToSubAssert().value(columnName);
  }

}
