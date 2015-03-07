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

import org.assertj.db.api.origin.OriginWithValuesFromColumn;

/**
 * Abstract class that represents a assert with an origin assert and which is the origin assert of another assert and have value from a column.
 *
 * @param <E> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @param <O> The class of the assert of origin
 * @author Régis Pouiller
 */
public abstract class AbstractAssertWithValuesFromColumn <E extends AbstractAssertWithValuesFromColumn<E, O>, O extends OriginWithValuesFromColumn>
        extends AbstractAssertWithValues<E, O> implements OriginWithValuesFromColumn {

  /**
   * Constructor.
   *
   * @param selfType     Class of this assert : a sub-class of {@code AbstractAssertWithValuesFromColumn}.
   * @param originAssert The assert of origin.
   * @param value The value on which are the assertions.
   */
  AbstractAssertWithValuesFromColumn(Class<E> selfType, O originAssert, Object value) {
    super(selfType, originAssert, value);
  }

  /**
   * Returns assertion methods on the value at the start point.
   *
   * @return An object to make assertions on the next value.
   */
  public ChangeColumnValueAssert valueAtStartPoint() {
    return origin.valueAtStartPoint();
  }

  /**
   * Returns assertion methods on the value at the end point.
   *
   * @return An object to make assertions on the value.
   */
  public ChangeColumnValueAssert valueAtEndPoint() {
    return origin.valueAtEndPoint();
  }
}
