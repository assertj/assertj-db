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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.navigation;

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.global.AbstractElement;
import org.assertj.db.type.DbElement;

import java.lang.reflect.Constructor;

/**
 * Position with point (start point and end point) during navigation.
 *
 * @param <E> The class of the actual position (an sub-class of {@link org.assertj.db.global.AbstractElement} and of {@link org.assertj.db.navigation.Navigation}).
 * @param <N> The class of the next position where the navigation go (an sub-class of {@link org.assertj.db.global.AbstractElement} and of {@link org.assertj.db.navigation.Navigation}).
 * @param <D> The class of the database element on which is the next position (an sub-class of {@link org.assertj.db.type.DbElement}).
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public abstract class PositionWithPoints<E extends AbstractElement & Navigation, N extends AbstractElement & Navigation, D extends DbElement> {

  /**
   * Actual value.
   */
  private final E myself;
  /**
   * Class of the element of navigation (used to make instance).
   */
  private final Class<N> elementClass;
  /**
   * Class of the database element at the next point (used to make instance).
   */
  private final Class<D> pointClass;

  /**
   * The element at start point.
   */
  private final D atStartPoint;

  /**
   * The element at end point.
   */
  private final D atEndPoint;

  /**
   * The instance at start point.
   */
  private N instanceAtStartPoint;
  /**
   * The instance at end point.
   */
  private N instanceAtEndPoint;

  /**
   * Constructor.
   *
   * @param myself Actual value.
   * @param elementClass Class of the element of navigation (used to make instance).
   * @param pointClass Class of the database element at the next point (used to make instance).
   * @param atStartPoint The element at start point
   * @param atEndPoint The element at end point
   */
  public PositionWithPoints(E myself, Class<N> elementClass, Class<D> pointClass, D atStartPoint, D atEndPoint) {
    this.myself = myself;
    this.elementClass = elementClass;
    this.pointClass = pointClass;
    this.atStartPoint = atStartPoint;
    this.atEndPoint = atEndPoint;
  }


  /**
   * Gets an instance of element of navigation at start point.
   * If this instance is already instanced, the method returns it from the cache.
   *
   * @return The instance of element of navigation.
   */
  public N getInstanceAtStartPoint() {
    if (instanceAtStartPoint == null) {
      instanceAtStartPoint = getInstance(atStartPoint);
      instanceAtStartPoint.as(getDescriptionAtStartPoint());
    }
    return instanceAtStartPoint;
  }

  /**
   * Gets an instance of element of navigation at end point.
   * If this instance is already instanced, the method returns it from the cache.
   *
   * @return The instance of element of navigation.
   */
  public N getInstanceAtEndPoint() {
    if (instanceAtEndPoint == null) {
      instanceAtEndPoint = getInstance(atEndPoint);
      instanceAtEndPoint.as(getDescriptionAtEndPoint());
    }
    return instanceAtEndPoint;
  }

  /**
   * Gets an instance of element of navigation corresponding to the element in parameter.
   *
   * @param element The element.
   * @return The instance of element of navigation.
   */
  protected N getInstance(D element) {
    try {
      Constructor<N> constructor = elementClass.getDeclaredConstructor(myself.getClass(), pointClass);
      N instance = constructor.newInstance(myself, element);
      return instance;
    } catch (Exception e) {
      throw new AssertJDBException(String.format("There is an exception '" + e.getMessage()
                                                 + "'%n\t in the instantiation of the element " + elementClass.getName() + "%n\t on "
                                                 + pointClass
                                                 + " with " + myself.getClass() + ".%n "
                                                 + "It is normally impossible.%n That means there is a big mistake in the development of AssertJDB.%n "
                                                 + "Please write an issue for that if you meet this problem."));
    }
  }

  /**
   * Returns the description at start point.
   * @return The description at start point
   */
  protected abstract String getDescriptionAtStartPoint();

  /**
   * Returns the description at end point.
   * @return The description at end point
   */
  protected abstract String getDescriptionAtEndPoint();
}
