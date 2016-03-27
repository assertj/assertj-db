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

import org.assertj.db.display.impl.Representation;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.global.AbstractElement;

import java.io.*;

/**
 * Base class for all display of assertj-db.
 *
 * @param <E> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 * @since 1.1.0
 */
public abstract class AbstractDisplay<E extends AbstractDisplay<E>>
        extends AbstractElement<E> {

  /**
   * Type of representation.
   */
  protected Representation representationType;

  /**
   * Constructor.
   *
   * @param selfType    Class of this assertion class : a sub-class of {@code AbstractDisplay}.
   */
  AbstractDisplay(Class<E> selfType) {
    super(selfType);
  }

  /**
   * Changes the type of the representation.
   *
   * @param representationType Type of representation.
   * @return {@code this} display object.
   */
  public E withType(Representation representationType) {
    this.representationType = representationType;
    return myself;
  }

  /**
   * Returns the representation for the display
   *
   * @param representationType Type of representation.
   * @return The representation.
   */
  protected abstract String getRepresentation(Representation representationType);

  /**
   * Display {@code this} in the {@code System.out}.
   *
   * @return {@code this} display object.
   */
  public E inConsole() {
    return inStream(System.out);
  }

  /**
   * Display {@code this} in the {@code OutputStream}.
   *
   * @param outputStream {@code OutputStream} to use for output.
   * @return {@code this} display object.
   */
  public E inStream(OutputStream outputStream) {
    String representation = getRepresentation(representationType);
    PrintStream printStream = new PrintStream(outputStream);
    printStream.print(representation);
    return myself;
  }

  /**
   * Display {@code this} in a file.
   *
   * @param fileName The file name.
   * @return {@code this} display object.
   * @throws AssertJDBException If exception in IO.
   */
  public E inFile(String fileName) {
    String representation = getRepresentation(representationType);
    try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
      PrintStream printStream = new PrintStream(fileOutputStream);
      printStream.print(representation);
    } catch (FileNotFoundException e) {
      throw new AssertJDBException(e);
    } catch (IOException e) {
      throw new AssertJDBException(e);
    }
    return myself;
  }
}
