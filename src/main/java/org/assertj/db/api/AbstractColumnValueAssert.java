package org.assertj.db.api;

import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;

/**
 * Assertion methods about a value in a {@link Column}.
 * 
 * @author Régis Pouiller
 * 
 * @param <S> The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <A> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <T> The class of which contains assertion methods about {@link Column} (an sub-class of
 *          {@link AbstractColumnAssert}).
 * @param <R> The class of this assert (an sub-class of {@link AbstractColumnValueAssert}).
 */
public class AbstractColumnValueAssert<S extends AbstractDbAssert<A, S>, A extends AbstractDbData<A>, T extends AbstractColumnAssert<S, A, T, R>, R extends AbstractColumnValueAssert<S, A, T, R>>
    extends AbstractValueAssert<S, A, T, R> {

  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param selfType Class of this assert (the value assert) : a sub-class of {@code AbstractValueAssert}.
   * @param actualValue The value to assert.
   */
  AbstractColumnValueAssert(T originalAssert, Class<R> selfType, Object actualValue) {
    super(originalAssert, selfType, actualValue);
  }

}
