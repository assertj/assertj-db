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

import org.assertj.db.display.impl.RepresentationType;
import org.assertj.db.global.AbstractElement;
import org.assertj.db.type.AbstractDbData;

import java.io.PrintStream;

/**
 * Base class for all display of assertj-db.
 *
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <E> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 * @since 1.1.0
 */
public abstract class AbstractDisplay<D extends AbstractDbData<D>, E extends AbstractDisplay<D, E>>
        extends AbstractElement<E> {

  /**
   * Type of display.
   */
  private RepresentationType displayType;

  /**
   * Constructor.
   *
   * @param selfType    Class of this assertion class : a sub-class of {@code AbstractDisplay}.
   * @param displayType Type of display.
   */
  AbstractDisplay(Class<E> selfType, RepresentationType displayType) {
    super(selfType);
    this.displayType = displayType;
  }

  /**
   * Changes the type of the display.
   *
   * @param displayType Type of display.
   * @return {@code this} display object.
   */
  public E withType(RepresentationType displayType) {
    this.displayType = displayType;
    return myself;
  }

  /**
   * Returns the representation for the display
   *
   * @param displayType Type of display.
   * @return The representation.
   */
  protected abstract String getRepresentation(RepresentationType displayType);

  /**
   * Display {@code this} in the {@code System.out}.
   *
   * @return {@code this} display object.
   */
  public E display() {
    return display(System.out);
  }

  /**
   * Display {@code this} in the {@code printStream}.
   *
   * @param printStream {@code PrintStream} to use for output
   * @return {@code this} display object.
   */
  public E display(PrintStream printStream) {
    String representation = getRepresentation(displayType);
    printStream.print(representation);
    return myself;
  }
}
