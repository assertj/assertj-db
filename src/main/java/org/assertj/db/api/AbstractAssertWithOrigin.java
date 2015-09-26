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
package org.assertj.db.api;

import org.assertj.db.navigation.origin.Origin;

/**
 * Base class for all assertions with an {@link org.assertj.db.navigation.origin.Origin}.
 * 
 * @author Régis Pouiller
 * 
 * @param <E> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *          for more details.
 * @param <O> The type of the assertion class of {@link org.assertj.db.navigation.origin.Origin}.
 */
public abstract class AbstractAssertWithOrigin<E extends AbstractAssertWithOrigin<E, O>, O extends Origin>
        extends AbstractAssert<E> {

  /**
   * The assertion of origin.
   */
  protected final O origin;

  /**
   * Constructor.
   * 
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractAssertWithOrigin}.
   * @param origin The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   */
  AbstractAssertWithOrigin(Class<E> selfType, O origin) {
    super(selfType);
    this.origin = origin;
  }

  /**
   * Returns the assertion of origin (an instance of a sub-class of {@link org.assertj.db.navigation.origin.Origin}.
   * 
   * @return The assertion of origin.
   */
  protected O returnToOrigin() {
    return origin;
  }
}
