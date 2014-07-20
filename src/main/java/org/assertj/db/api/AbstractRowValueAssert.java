package org.assertj.db.api;

import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Row;

/**
 * Assertion methods about a value in a {@link Row}.
 * 
 * @author Régis Pouiller
 * 
 * @param <S> The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <A> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <T> The class of which contains assertion methods about {@link Row} (an sub-class of
 *          {@link AbstractRowAssert}).
 * @param <R> The class of this assert (an sub-class of {@link AbstractRowValueAssert}).
 */
public class AbstractRowValueAssert<S extends AbstractDbAssert<S, A>, A extends AbstractDbData<A>, T extends AbstractRowAssert<S, A, T, R>, R extends AbstractRowValueAssert<S, A, T, R>>
    extends AbstractValueAssert<S, A, T, R> {

  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param selfType Class of this assert (the value assert) : a sub-class of {@code AbstractValueAssert}.
   * @param actualValue The value to assert.
   */
  AbstractRowValueAssert(T originalAssert, Class<R> selfType, Object actualValue) {
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
  public R value(String columnName) {
    return returnToSubAssert().value(columnName);
  }

}
