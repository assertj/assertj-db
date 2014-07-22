package org.assertj.db.api;

import static org.assertj.db.error.ShouldHaveRowsSize.shouldHaveRowsSize;

import java.util.List;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;

/**
 * Assertion methods about the data in a <code>{@link Column}</code>.
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
public abstract class AbstractColumnAssert<E extends AbstractDbData<E>, D extends AbstractDbAssert<E, D>, C extends AbstractColumnAssert<E, D, C, CV, R, RV>, CV extends AbstractColumnValueAssert<E, D, C, CV, R, RV>, R extends AbstractRowAssert<E, D, C, CV, R, RV>, RV extends AbstractRowValueAssert<E, D, C, CV, R, RV>>
    extends AbstractSubAssert<E, D, C, CV> {

  /**
   * Column on which do the assertion.
   */
  private Column column;

  /**
   * To notice failures in the assertion.
   */
  private static Failures failures = Failures.instance();

  /**
   * Constructor.
   * 
   * @param originalDbAssert The original assert. That could be a {@link RequestAssert} or a {@link TableAssert}.
   * @param selfType Class of this assert (the sub assert) : a sub-class of {@code AbstractSubAssert}.
   * @param valueType Class of the assert on the value : a sub-class of {@code AbstractValueAssert}.
   */
  AbstractColumnAssert(D originalDbAssert, Class<C> selfType, Class<CV> valueType, Column column) {
    super(originalDbAssert, selfType, valueType);
    this.column = column;
  }

  /** {@inheritDoc} */
  @Override
  protected List<Object> getValuesList() {
      return column.getValuesList();
  }

  /** {@inheritDoc} */
  @Override
  protected void assertHasSize(WritableAssertionInfo info, int expected) {
    List<Object> valuesList = column.getValuesList();
    int size = valuesList.size();
    if (size != expected) {
      throw failures.failure(info, shouldHaveRowsSize(size, expected));
    }
  }

}
