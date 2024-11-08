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
package org.assertj.db.api;

import org.assertj.db.global.AbstractElement;

/**
 * Base class for all assertions of assertj-db.
 *
 * @param <E> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public abstract class AbstractAssert<E extends AbstractAssert<E>>
  extends AbstractElement<E> {

  /**
   * Constructor.
   *
   * @param selfType Class of this assertion class : a sub-class of {@code AbstractElement}.
   */
  AbstractAssert(Class<E> selfType) {
    super(selfType);
  }
}
