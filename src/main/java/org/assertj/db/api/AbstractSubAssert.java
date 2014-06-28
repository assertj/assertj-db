package org.assertj.db.api;

import org.assertj.core.api.Descriptable;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.description.Description;
import org.assertj.db.type.AbstractDbDatas;

/**
 * Assertion methods about {@link Column} or {@link Row}.
 * 
 * @author Régis Pouiller
 * 
 * @param <S> The class of the original assertion (an sub-class of {@link AbstractDbAssert}).
 * @param <A> The class of the actual value (an sub-class of {@link AbstractDbDatas}).
 * @param <T> The class of this assertion (an sub-class of {@link AbstractSubAssert}).
 */
public abstract class AbstractSubAssert<S extends AbstractDbAssert<S, A>, A extends AbstractDbDatas<A>, T extends AbstractSubAssert<S, A, T>>
    implements Descriptable<T> {

  /**
   * Info on the object to assert.
   */
  private final WritableAssertionInfo info;
  /**
   * The original assert. That could be a {@link RequestAssert} or a {@link TableAssert}.
   */
  private final S original;
  /**
   * This assertion.
   */
  private final T myself;

  // Like in AbstractAssert from assertj-core :
  // we prefer not to use Class<? extends S> selfType because it would force inherited
  // constructor to cast with a compiler warning
  // let's keep compiler warning internal (when we can) and not expose them to our end users.
  /**
   * Constructor.
   * 
   * @param originalDbAssert The original assert. That could be a {@link RequestAssert} or a {@link TableAssert}.
   * @param selfType Class of this assert (the sub assert) : a sub-class of {@code AbstractSubAssert}.
   */
  @SuppressWarnings("unchecked")
  AbstractSubAssert(S originalDbAssert, Class<?> selfType) {
    myself = (T) selfType.cast(this);
    original = originalDbAssert;
    info = new WritableAssertionInfo();
  }

  /** {@inheritDoc} */
  @Override
  public T as(String description, Object... args) {
    return describedAs(description, args);
  }

  /** {@inheritDoc} */
  @Override
  public T as(Description description) {
    return describedAs(description);
  }

  /** {@inheritDoc} */
  @Override
  public T describedAs(String description, Object... args) {
    info.description(description, args);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public T describedAs(Description description) {
    info.description(description);
    return myself;
  }

  /**
   * Returns the original assertion (an instance of a sub-class of {@link AbstractDbAssert}.
   * 
   * @return The original assertion.
   */
  protected S returnToDbAssert() {
    return original;
  }

  /**
   * Verifies that the size of a {@link Row} or of a {@link Column} is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the first row of the table have 8 columns :
   * </p>
   * 
   * <pre>
   * assertThat(table).row().hasSize(8);
   * </pre>
   * <p>
   * Example where the assertion verifies that the column with index 1 of the table have 5 rows :
   * </p>
   * 
   * <pre>
   * assertThat(table).column(1).hasSize(5);
   * </pre>
   * 
   * @param expected The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the size is different to the number in parameter.
   */
  public T hasSize(int expected) {
    assertHasSize(info, expected);
    return myself;
  }

  /**
   * Called by {@link AbstractSubAssert#hasSize(int)}, the sub-classes implement this method. This is a skeleton
   * pattern. So for a {@link Row}, the implementation in {@link RowAssert} sub-class tests the number of columns and
   * for a {@link Column}, the implementation in {@link ColumnAssert} sub-class tests the number of rows.
   * 
   * @param info Info on the object to assert.
   * @param expected The number to compare to the size.
   */
  protected abstract void assertHasSize(WritableAssertionInfo info, int expected);
}
