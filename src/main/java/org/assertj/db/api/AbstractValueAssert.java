package org.assertj.db.api;

import org.assertj.core.api.Descriptable;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.description.Description;
import org.assertj.db.type.AbstractDbData;

public abstract class AbstractValueAssert<S extends AbstractDbAssert<S, A>, A extends AbstractDbData<A>, T extends AbstractSubAssert<S, A, T>, V extends AbstractValueAssert<S, A, T, V>>
    implements Descriptable<V> {

  /**
   * The original assert.
   */
  private final T originalAssert;
  /**
   * The actual value on which this assertion is.
   */
  private final Object value;
  /**
   * This assertion.
   */
  private final V myself;
  /**
   * The information about the assertion.
   */
  private final WritableAssertionInfo info;

  // Like in AbstractAssert from assertj-core :
  // we prefer not to use Class<? extends S> selfType because it would force inherited
  // constructor to cast with a compiler warning
  // let's keep compiler warning internal (when we can) and not expose them to our end users.
  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param selfType Class of this assert (the value assert) : a sub-class of {@code AbstractValueAssert}.
   * @param actualValue The value to assert.
   */
  @SuppressWarnings("unchecked")
  AbstractValueAssert(T originalAssert, Class<?> selfType, Object actualValue) {
    myself = (V) selfType.cast(this);
    this.originalAssert = originalAssert;
    this.value = actualValue;
    info = new WritableAssertionInfo();
  }

  /** {@inheritDoc} */
  public V as(String description, Object... args) {
    return describedAs(description, args);
  }

  /** {@inheritDoc} */
  @Override
  public V as(Description description) {
    return describedAs(description);
  }

  /** {@inheritDoc} */
  @Override
  public V describedAs(String description, Object... args) {
    info.description(description, args);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public V describedAs(Description description) {
    info.description(description);
    return myself;
  }

  /**
   * Returns the original assertion (an instance of a sub-class of {@link AbstractSubAssert}.
   * 
   * @return The original assertion.
   */
  protected T returnToSubAssert() {
    return originalAssert;
  }

}
