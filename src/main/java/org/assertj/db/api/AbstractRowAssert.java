package org.assertj.db.api;

import static org.assertj.db.error.ShouldHaveColumnsSize.shouldHaveColumnsSize;

import java.util.List;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Row;

/**
 * Assertion methods about the data in a <code>{@link Row}</code>.
 * 
 * @author Régis Pouiller
 * 
 * @param <S> The class of the original assert (an sub-class of {@link AbstractDbAssert}).
 * @param <A> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <R> The class of this assert (an sub-class of {@link AbstractRowAssert}).
 */
public abstract class AbstractRowAssert<S extends AbstractDbAssert<S, A>, A extends AbstractDbData<A>, R extends AbstractRowAssert<S, A, R>>
    extends AbstractSubAssert<S, A, R> {

  /**
   * Row on which do the assertion.
   */
  private Row row;

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
  AbstractRowAssert(S originalDbAssert, Class<?> selfType, Row row) {
    super(originalDbAssert, selfType);
    this.row = row;
  }

  /** {@inheritDoc} */
  @Override
  protected List<Object> getValuesList() {
      return row.getValuesList();
  }

  /**
   * Returns the value corresponding to the column name in parameter.
   * 
   * @param columnName The column name.
   * @return The value.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws AssertJDBException If there is no column with this name.
   */
  protected Object getValue(String columnName) {
    if (columnName == null) {
      throw new NullPointerException("Column name must be not null");
    }
    List<String> columnsNameList = row.getColumnsNameList();
    int index = columnsNameList.indexOf(columnName.toUpperCase());
    if (index == -1) {
      throw new AssertJDBException("Column <%s> does not exist", columnName);
    }
    return getValue(index);
  }

  /** {@inheritDoc} */
  @Override
  protected void assertHasSize(WritableAssertionInfo info, int expected) {
    List<String> columnsNameList = row.getColumnsNameList();
    int size = columnsNameList.size();
    if (size != expected) {
      throw failures.failure(info, shouldHaveColumnsSize(size, expected));
    }
  }
}
