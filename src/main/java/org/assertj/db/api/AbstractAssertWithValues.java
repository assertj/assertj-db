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
 * Abstract class that represents a assert with an origin assert and which is the origin assert of another assert and have changes.
 *
 * @param <E> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @param <O> The class of the assert of origin
 * @author Régis Pouiller
 */
public abstract class AbstractAssertWithValues<E extends AbstractAssertWithValues<E, O>, O extends OriginAssertWithValues>
        extends AbstractAssertWithRows<E, O> implements OriginAssertWithValues {
  /**
   * Constructor.
   *
   * @param selfType     Class of this assert : a sub-class of {@code AbstractAssertWithValues}.
   * @param originAssert The assert of origin.
   */
  AbstractAssertWithValues(Class<E> selfType, O originAssert) {
    super(selfType, originAssert);
  }

  /**
   * Returns assertion methods on the next value in the list of values.
   *
   * @return An object to make assertions on the next value.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeValueAssert value() {
    return originAssert.value();
  }

  /**
   * Returns assertion methods on the value at the {@code index} in parameter.
   *
   * @param index The index corresponding to the value.
   * @return An object to make assertions on the value.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeValueAssert value(int index) {
    return originAssert.value(index);
  }

  /**
   * Returns assertion methods on the value corresponding to the column name in parameter.
   *
   * @param columnName The column name.
   * @return An object to make assertions on the value.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws org.assertj.db.exception.AssertJDBException If there is no column with this name.
   */
  public ChangeValueAssert value(String columnName) {
    return originAssert.value(columnName);
  }
}
