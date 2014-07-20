package org.assertj.db.api;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Descriptable;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.description.Description;
import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;
import org.assertj.db.type.Row;

/**
 * Assertion methods about {@link Column} or {@link Row}.
 * 
 * @author Régis Pouiller
 * 
 * @param <E> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <S> The class of the original assertion (an sub-class of {@link AbstractDbAssert}).
 * @param <T> The class of this assertion (an sub-class of {@link AbstractSubAssert}).
 * @param <V> The class of this assertion on the value (an sub-class of {@link AbstractValueAssert}).
 */
public abstract class AbstractSubAssert<E extends AbstractDbData<E>, S extends AbstractDbAssert<E, S>, T extends AbstractSubAssert<E, S, T, V>, V extends AbstractValueAssert<E, S, T, V>>
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
  /**
   * Class of the assert on the value (used to make instance).
   */
  private final Class<?> valueClass;

  /**
   * Index of the next value to get.
   */
  private int indexNextValue;
  /**
   * Map the values assert with their index in key (contains the values assert already generated).
   */
  private Map<Integer, V> valuesAssertMap = new HashMap<Integer, V>();

  // Like in AbstractAssert from assertj-core :
  // we prefer not to use Class<? extends S> selfType because it would force inherited
  // constructor to cast with a compiler warning
  // let's keep compiler warning internal (when we can) and not expose them to our end users.
  /**
   * Constructor.
   * 
   * @param originalDbAssert The original assert. That could be a {@link RequestAssert} or a {@link TableAssert}.
   * @param selfType Class of this assert (the sub assert) : a sub-class of {@code AbstractSubAssert}.
   * @param valueType Class of the assert on the value : a sub-class of {@code AbstractValueAssert}.
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  AbstractSubAssert(S originalDbAssert, Class<? extends AbstractSubAssert> selfType,
      Class<? extends AbstractValueAssert> valueType) {

    myself = (T) selfType.cast(this);
    valueClass = valueType;
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
   * Gets an instance of value assert corresponding to the index. If this instance is already instanced, the method
   * returns it from the cache.
   * 
   * @param index Index of the value on which is the instance of value assert.
   * @return The value assert implementation.
   */
  protected V getValueAssertInstance(int index) {
    if (valuesAssertMap.containsKey(index)) {
      return valuesAssertMap.get(index);
    }

    try {
      @SuppressWarnings("unchecked")
      Constructor<V> constructor = (Constructor<V>) valueClass.getDeclaredConstructor(myself.getClass(), Object.class);
      V instance = constructor.newInstance(this, getValue(index));
      valuesAssertMap.put(index, instance);
      return instance;
    } catch (Exception e) {
      throw new AssertJDBException("There is an exception " + (e.getClass())
          + " in the instanciation of the assertion on the value. "
          + "It is normally impossible. That means there is a big mistake in the development of AssertJDB. "
          + "Please write an issue for that if you meet this problem.");
    }
  }

  /**
   * Returns assertion methods on the next value in the list of value.
   * 
   * @return An object to make assertions on the next value.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public V value() {
    return getValueAssertInstance(indexNextValue);
  }

  /**
   * Returns assertion methods on the value at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the value.
   * @return An object to make assertions on the value.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public V value(int index) {
    return getValueAssertInstance(index);
  }

  /**
   * Returns the list of values.
   * 
   * @return The list of values.
   */
  protected abstract List<Object> getValuesList();

  /**
   * Returns the value at the {@code index} in parameter.
   * 
   * @param index The index corresponding to the value.
   * @return The value.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  protected Object getValue(int index) {
    int size = getValuesList().size();
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits [0, %s[", index, size);
    }
    Object object = getValuesList().get(index);
    indexNextValue = index + 1;
    return object;
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
   * pattern. So for a {@link Row}, the implementation in {@link AbstractRowAssert} sub-class tests the number of
   * columns and for a {@link Column}, the implementation in {@link AbstractColumnAssert} sub-class tests the number of
   * rows.
   * 
   * @param info Info on the object to assert.
   * @param expected The number to compare to the size.
   */
  protected abstract void assertHasSize(WritableAssertionInfo info, int expected);
}
