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

/**
 * Abstract class that represents a assert with an origin assert.
 * 
 * @author Régis Pouiller
 * 
 * @param <E> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *          for more details.
 * @param <O> The class of the assert of origin
 */
public abstract class AbstractAssertWithOriginAssert<E extends AbstractAssertWithOriginAssert<E, O>, O extends OriginAssert>
    extends AbstractAssert<E> {

  /**
   * The origin assert.
   */
  private final O originAssert;

  /**
   * Constructor.
   * 
   * @param selfType Class of this assert : a sub-class of {@code AbstractAssert}.
   * @param originAssert The assert of origin.
   */
  AbstractAssertWithOriginAssert(Class<E> selfType, O originAssert) {
    super(selfType);
    this.originAssert = originAssert;
  }

  /**
   * Returns the assert of origin (an instance of a sub-class of {@link AbstractAssert}.
   * 
   * @return The assert of origin.
   */
  protected O returnToOriginAssert() {
    return originAssert;
  }
}
