/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.db.api.navigation.ToValue;
import org.assertj.db.api.origin.OriginWithColumnsAndRows;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Column;
import org.assertj.db.type.Row;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Assertion methods about {@link Column} or {@link Row}.
 * 
 * @author Régis Pouiller
 * 
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assertion (an sub-class of {@link AbstractDbAssert}).
 * @param <S> The class of this assertion (an sub-class of {@link AbstractSubAssert}).
 * @param <V> The class of this assertion on the value (an sub-class of {@link AbstractValueAssert}).
 * @param <C> The class of this assertion (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of this assertion on the value (an sub-class of {@link AbstractColumnValueAssert}).
 * @param <R> The class of the equivalent row assertion (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of the equivalent row assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public abstract class AbstractSubAssert<D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, S extends AbstractSubAssert<D, A, S, V, C, CV, R, RV>, V extends AbstractValueAssert<D, A, S, V, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>>
        extends AbstractAssertWithOriginWithColumnsAndRows<S, A, D, A, C, CV, R, RV>
        implements OriginWithColumnsAndRows<C, R>,
                   ToValue<V> {

  /**
   * Class of the assertion on the value (used to make instance).
   */
  private final Class<V> valueClass;

  /**
   * Index of the next value to get.
   */
  private int indexNextValue;
  /**
   * Map the values assertion with their index in key (contains the values assertion already generated).
   */
  private Map<Integer, V> valuesAssertMap = new HashMap<Integer, V>();

  /**
   * Constructor.
   * 
   * @param originalDbAssert The original assert. That could be a {@link RequestAssert} or a {@link TableAssert}.
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractSubAssert}.
   * @param valueType Class of the assertion on the value : a sub-class of {@code AbstractValueAssert}.
   */
  AbstractSubAssert(A originalDbAssert, Class<S> selfType, Class<V> valueType) {
    super(selfType, originalDbAssert);
    valueClass = valueType;
  }

  /**
   * To initialize the object when getting from cache.
   */
  S initialize() {
    indexNextValue = 0;
    return myself;
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
      V valueAssert = valuesAssertMap.get(index);
      indexNextValue = index + 1;
      return valueAssert;
    }

    try {
      Constructor<V> constructor = (Constructor<V>) valueClass.getDeclaredConstructor(myself.getClass(), Object.class);
      V instance = constructor.newInstance(this, getValue(index));
      valuesAssertMap.put(index, instance);
      return instance.as("Value at index " + index + " of " + info.descriptionText());
    } catch (Exception e) {
      throw new AssertJDBException("There is an exception '" + e.getMessage()
          + "'\n\t in the instanciation of the assertion " + valueClass.getName() + "\n\t on the value with "
          + myself.getClass() + ".\n "
          + "It is normally impossible.\n That means there is a big mistake in the development of AssertJDB.\n "
          + "Please write an issue for that if you meet this problem.");
    }
  }

  /** {@inheritDoc} */
  @Override
  public V value() {
    return getValueAssertInstance(indexNextValue);
  }

  /** {@inheritDoc} */
  @Override
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
}
