package org.assertj.db.api;

import static org.assertj.db.error.ShouldHaveColumnsSize.shouldHaveColumnsSize;
import static org.assertj.db.error.ShouldHaveRowsSize.shouldHaveRowsSize;

import java.util.List;

import org.assertj.core.api.Descriptable;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.description.Description;
import org.assertj.core.internal.Failures;
import org.assertj.db.type.AbstractDbDatas;
import org.assertj.db.type.Row;

public abstract class AbstractDbAssert<S extends AbstractDbAssert<S, A>, A extends AbstractDbDatas<A>> implements
    Descriptable<S> {

  /**
   * Info on the object to assert.
   */
  private final WritableAssertionInfo info;
  /**
   * The actual value on which the assertion is.
   */
  private final A actual;
  /**
   * Class of the assertion.
   */
  private final S myself;

  /**
   * To notice failures in the assertion.
   */
  private static Failures failures = Failures.instance();

  // Like in AbstractAssert from assertj-core :
  // we prefer not to use Class<? extends S> selfType because it would force inherited
  // constructor to cast with a compiler warning
  // let's keep compiler warning internal (when we can) and not expose them to our end users.
  /**
   * Constructor of the database assertions.
   * 
   * @param actualValue The actual value on which the assertion is.
   * @param selfType Class of the assertion
   */
  @SuppressWarnings("unchecked")
  protected AbstractDbAssert(final A actualValue, final Class<?> selfType) {
    myself = (S) selfType.cast(this);
    actual = actualValue;
    info = new WritableAssertionInfo();
  }

  /** {@inheritDoc} */
  public S as(String description, Object... args) {
    return describedAs(description, args);
  }

  /** {@inheritDoc} */
  @Override
  public S as(Description description) {
    return describedAs(description);
  }

  /** {@inheritDoc} */
  @Override
  public S describedAs(String description, Object... args) {
    info.description(description, args);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public S describedAs(Description description) {
    info.description(description);
    return myself;
  }

  /**
   * Verifies that the number of rows is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the table have 2 rows :
   * </p>
   * 
   * <pre>
   * assertThat(table).hasRowsSize(2);
   * </pre>
   * 
   * @param expected The number to compare to the number of rows.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of rows is different to the number in parameter.
   */
  public S hasRowsSize(int expected) {
    List<Row> rowsList = actual.getRowsList();
    int size = rowsList.size();
    if (size != expected) {
      throw failures.failure(info, shouldHaveRowsSize(size, expected));
    }
    return myself;
  }

  /**
   * Verifies that the number of columns is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that the table have 8 columns :
   * </p>
   * 
   * <pre>
   * assertThat(table).hasColumnsSize(8);
   * </pre>
   * 
   * @param expected The number to compare to the number of columns.
   * @return {@code this} assertion object.
   * @throws AssertionError If the number of columns is different to the number in parameter.
   */
  public S hasColumnsSize(int expected) {
    List<String> columnsNameList = actual.getColumnsNameList();
    int size = columnsNameList.size();
    if (size != expected) {
      throw failures.failure(info, shouldHaveColumnsSize(size, expected));
    }
    return myself;
  }
}
