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
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.output;

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.global.AbstractElement;
import org.assertj.db.output.impl.Output;

import java.io.*;

/**
 * Base class for all output of assertj-db.
 *
 * @param <E> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 * @since 1.1.0
 */
public abstract class AbstractOutputter<E extends AbstractOutputter<E>>
        extends AbstractElement<E> {

  /**
   * Type of output.
   */
  protected Output outputType;

  /**
   * Constructor.
   *
   * @param selfType    Class of this assertion class : a sub-class of {@code AbstractOutputter}.
   */
  AbstractOutputter(Class<E> selfType) {
    super(selfType);
  }

  /**
   * Changes the type of the output.
   *
   * @param outputType Type of output.
   * @return {@code this} output object.
   */
  public E withType(Output outputType) {
    this.outputType = outputType;
    return myself;
  }

  /**
   * Returns the output for the output
   *
   * @param outputType Type of output.
   * @return The output.
   */
  protected abstract String getOutput(Output outputType);

  /**
   * Output {@code this} to the {@code System.out}.
   *
   * @return {@code this} output object.
   */
  @SuppressWarnings("squid:S106")
  public E toConsole() {
    return toStream(System.out);
  }

  /**
   * Output {@code this} to the {@code OutputStream}.
   *
   * @param outputStream {@code OutputStream} to use for output.
   * @return {@code this} output object.
   */
  public E toStream(OutputStream outputStream) {
    String output = getOutput(outputType);
    PrintStream printStream = new PrintStream(outputStream);
    printStream.print(output);
    return myself;
  }

  /**
   * Output {@code this} to a file.
   *
   * @param fileName The file name.
   * @return {@code this} output object.
   * @throws AssertJDBException If exception in IO.
   */
  public E toFile(String fileName) {
    String output = getOutput(outputType);
    try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
      PrintStream printStream = new PrintStream(fileOutputStream);
      printStream.print(output);
    } catch (IOException e) {
      throw new AssertJDBException(e);
    }
    return myself;
  }
}
