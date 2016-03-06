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
package org.assertj.db.display;

import org.assertj.db.navigation.element.ValueElement;
import org.assertj.db.navigation.origin.OriginWithColumnsAndRowsFromChange;
import org.assertj.db.type.Value;

/**
 * Base class for all values from a {@link org.assertj.db.type.Change} displays.
 *
 * @param <E> The "self" type of this display class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @param <O> The type of the assertion class of {@link org.assertj.db.navigation.origin.Origin}.
 * @author Régis Pouiller
 */
public abstract class AbstractDisplayWithValues <E extends AbstractDisplayWithValues<E, O>, O extends OriginWithColumnsAndRowsFromChange<ChangesDisplay, ChangeDisplay, ChangeColumnDisplay, ChangeRowDisplay>>
        extends AbstractDisplayWithOriginWithColumnsAndRowsFromChange<E, O>
        implements ValueElement {
  /**
   * The actual value on which the assertion is.
   */
  protected final Value value;

  /**
   * Constructor.
   *
   * @param selfType Type of this assertion class : a sub-class of {@code AbstractDisplayWithValues}.
   * @param origin The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param value The value on which are the assertion methods.
   */
  AbstractDisplayWithValues(Class<E> selfType, O origin, Value value) {
    super(selfType, origin);
    this.value = value;
  }
}
