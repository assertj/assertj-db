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
 * @param <S> The class of which contains assertion methods about {@link Column} (an sub-class of
 *          {@link AbstractColumnAssert}).
 * @param <V> The class of this assert (an sub-class of {@link AbstractColumnValueAssert}).
 */
public class AbstractColumnValueAssert<E extends AbstractDbData<E>, D extends AbstractDbAssert<E, D>, S extends AbstractColumnAssert<E, D, S, V>, V extends AbstractColumnValueAssert<E, D, S, V>>
    extends AbstractValueAssert<E, D, S, V> {

  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param selfType Class of this assert (the value assert) : a sub-class of {@code AbstractValueAssert}.
   * @param actualValue The value to assert.
   */
  AbstractColumnValueAssert(S originalAssert, Class<V> selfType, Object actualValue) {
    super(originalAssert, selfType, actualValue);
  }

}
