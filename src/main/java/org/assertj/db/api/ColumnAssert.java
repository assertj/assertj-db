package org.assertj.db.api;

import static org.assertj.db.error.ShouldHaveRowsSize.shouldHaveRowsSize;

import java.util.List;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.type.AbstractDbDatas;
import org.assertj.db.type.Column;

/**
 * Assertion methods about the data in a <code>{@link Column}</code>.
 * 
 * @author Régis Pouiller
 * 
 * @param <S> The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <A> The class of the actual value (an sub-class of {@link AbstractDbDatas}).
 * @param <R> The class of this assert (an sub-class of {@link RowAssert}).
 */
public class ColumnAssert<S extends AbstractDbAssert<S, A>, A extends AbstractDbDatas<A>, R extends ColumnAssert<S, A, R>>
    extends AbstractSubAssert<S, A, R> {

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
   */
  ColumnAssert(S originalDbAssert, Class<?> selfType, Column column) {
    super(originalDbAssert, selfType);
    this.column = column;
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
