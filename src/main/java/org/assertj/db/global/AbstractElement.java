/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.global;

import org.assertj.core.api.Descriptable;
import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.description.Description;

/**
 * Base class for all elements of assertj-db.
 *
 * @param <E> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public abstract class AbstractElement<E extends AbstractElement<E>>
  implements Descriptable<E> {

  /**
   * Writable information about an element.
   */
  protected final WritableAssertionInfo info;

  /**
   * Class of the assertion.
   */
  protected final E myself;

  /**
   * Constructor.
   *
   * @param selfType Class of this assertion class : a sub-class of {@code AbstractElement}.
   */
  protected AbstractElement(Class<E> selfType) {
    myself = selfType.cast(this);
    info = new WritableAssertionInfo();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E as(String description, Object... args) {
    return describedAs(description, args);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E as(Description description) {
    return describedAs(description);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E describedAs(String description, Object... args) {
    info.description(description, args);
    return myself;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E describedAs(Description description) {
    info.description(description);
    return myself;
  }

  /**
   * Returns the information about an element.
   *
   * @return The information about an element.
   */
  public final WritableAssertionInfo getInfo() {
    return info;
  }
}
