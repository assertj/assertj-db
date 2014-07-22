package org.assertj.db.api;

import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;

/**
 * Assertion methods about a value in a {@link Column}.
 * 
 * @author Régis Pouiller
 * 
 * @param <E> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <D> The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <C> The class of this assert (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueAssert}).
 * @param <R> The class of the equivalent row assert (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public class AbstractColumnValueAssert<E extends AbstractDbData<E>, D extends AbstractDbAssert<E, D>, C extends AbstractColumnAssert<E, D, C, CV, R, RV>, CV extends AbstractColumnValueAssert<E, D, C, CV, R, RV>, R extends AbstractRowAssert<E, D, C, CV, R, RV>, RV extends AbstractRowValueAssert<E, D, C, CV, R, RV>>
    extends AbstractValueAssert<E, D, C, CV> {

  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param selfType Class of this assert (the value assert) : a sub-class of {@code AbstractValueAssert}.
   * @param actualValue The value to assert.
   */
  AbstractColumnValueAssert(C originalAssert, Class<CV> selfType, Object actualValue) {
    super(originalAssert, selfType, actualValue);
  }

}
