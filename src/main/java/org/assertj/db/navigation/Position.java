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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.navigation;

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.global.AbstractElement;
import org.assertj.db.type.DbElement;
import org.assertj.db.type.Row;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Position during navigation.
 *
 * @param <E> The class of the actual position (an sub-class of {@link org.assertj.db.global.AbstractElement} and of {@link org.assertj.db.navigation.Navigation}).
 * @param <N> The class of the next position where the navigation go (an sub-class of {@link org.assertj.db.global.AbstractElement} and of {@link org.assertj.db.navigation.Navigation}).
 * @param <D> The class of the database element on which is the next position (an sub-class of {@link org.assertj.db.type.DbElement}).
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public abstract class Position<E extends AbstractElement<?> & Navigation, N extends AbstractElement<?> & Navigation, D extends DbElement> {

  /**
   * Actual value.
   */
  private final E myself;
  /**
   * Index of the next to get.
   */
  private int nextIndex;
  /**
   * Class of the element of navigation (used to make instance).
   */
  private final Class<N> elementClass;
  /**
   * Map the elements of navigation with their index in key (contains the elements of navigation already generated).
   */
  private final Map<Integer, N> elementsMap = new HashMap<>();

  /**
   * Constructor.
   *
   * @param myself Actual value.
   * @param elementClass Class of the element of navigation (used to make instance).
   */
  public Position(E myself, Class<N> elementClass) {
    this.myself = myself;
    this.elementClass = elementClass;
  }

  /**
   * Returns the {@link Row} at the {@code index} in parameter.
   *
   * @param dbElementsList List of db elements.
   * @param index          The index corresponding to the {@link Row}.
   * @return The {@link Row}.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  protected D getDbElement(List<D> dbElementsList, int index) {
    int size = dbElementsList.size();
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits [0, %s[", index, size);
    }
    D element = dbElementsList.get(index);
    nextIndex = index + 1;
    return element;
  }

  /**
   * Gets an instance of element of navigation corresponding to the next index.
   * If this instance is already instanced, the method returns it from the cache.
   *
   * @param elementsList List of elements.
   * @return The instance of element of navigation.
   */
  public N getInstance(List<D> elementsList) {
    return getInstance(elementsList, nextIndex);
  }

  /**
   * Gets an instance of element of navigation corresponding to the index.
   * If this instance is already instanced, the method returns it from the cache.
   *
   * @param elementsList List of elements.
   * @param index        Index of the element on which is the instance of element of navigation.
   * @return The instance of element of navigation.
   */
  public N getInstance(List<D> elementsList, int index) {
    if (elementsMap.containsKey(index)) {
      N rowAssert = elementsMap.get(index);
      nextIndex = index + 1;
      return rowAssert;
    }

    D element = getDbElement(elementsList, index);
    try {
      Constructor<N> constructor = elementClass.getDeclaredConstructor(myself.getClass(), element.getClass());
      N instance = constructor.newInstance(myself, element);
      elementsMap.put(index, instance);
      instance.as(getDescription(index));
      return instance;
    } catch (Exception e) {
      throw new AssertJDBException(String.format("There is an exception '" + e.getMessage()
                                   + "'%n\t in the instantiation of the element " + elementClass.getName() + "%n\t on "
                                   + element.getClass()
                                   + " with " + myself.getClass() + ".%n "
                                   + "It is normally impossible.%n That means there is a big mistake in the development of AssertJDB.%n "
                                   + "Please write an issue for that if you meet this problem."));
    }
  }

  /**
   * Returns the description.
   *
   * @param index Index of the value.
   * @return The description
   */
  protected abstract String getDescription(int index);
}
