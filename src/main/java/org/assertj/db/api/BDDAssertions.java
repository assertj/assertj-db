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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Source;
import org.assertj.db.type.Table;

import java.io.*;

import static org.assertj.db.util.Descriptions.getDescription;

/**
 * Behavior Driven Development style entry point for all assertion methods.
 * <p>
 * The difference with the {@link Assertions} class is that the entry point methods are named <code>then</code>
 * instead of <code>assertThat</code>.
 * <p>
 *   The navigation methods are defined in <a href="navigation/package-summary.html">navigation package</a>.<br>
 *   The assertion methods are defined in <a href="assertions/package-summary.html">assertions package</a>.
 * </p>
 * <p>
 * Example with a {@link Source} and a {@link Table} with test on the content on the first row of the {@code movie}
 * table that the {@code title} column contains "Alien" as text and the next column contains 1979 as a number :
 * </p>
 * 
 * <pre>
 * <code class='java'>
 * Source source = new Source(&quot;jdbc:h2:mem:test&quot;, &quot;sa&quot;, &quot;&quot;);
 * Table table = new Table(source, &quot;movie&quot;);
 * then(table)
 *     .row()
 *        .value("title")
 *            .isEqualTo("Alien")
 *        .returnToRow()
 *        .value()
 *            .isEqualTo(1979);
 * </code>
 * </pre>
 * 
 * <p>
 * It is possible to chain assertion on a value :
 * </p>
 * 
 * <pre>
 * <code class='java'>
 * then(table)
 *     .row()
 *         .value("title")
 *             .isText()
 *             .isEqualTo("Alien");
 * </code>
 * </pre>
 * 
 * <p>
 * It is not necessary to use the <code>returnToXxxx</code> methods. The next example is equivalent to the first :
 * </p>
 * 
 * <pre>
 * <code class='java'>
 * Source source = new Source(&quot;jdbc:h2:mem:test&quot;, &quot;sa&quot;, &quot;&quot;);
 * Table table = new Table(source, &quot;movie&quot;);
 * then(table)
 *     .row()
 *        .value("title")
 *            .isEqualTo("Alien")
 *        .value()
 *            .isEqualTo(1979);
 * </code>
 * </pre>
 * 
 * <p>
 * It is possible to do the same thing with column and the row :
 * </p>
 * 
 * <pre>
 * <code class='java'>
 * then(table)
 *     .row()
 *        .value("title")
 *            .isEqualTo("Alien")
 *     .row()
 *        .value()
 *            .isEqualTo("The Village")
 *     .column("year")
 *         .value(1)
 *            .equalTo(2004);
 * </code>
 * </pre>
 */
public final class BDDAssertions {

  /**
   * Private constructor of the entry point.
   */
  private BDDAssertions() {
    // empty
  }

  /**
   * Creates a new instance of {@link TableAssert}.
   * 
   * @param table The table to assert on.
   * @return The created assertion object.
   */
  public static TableAssert then(Table table) {
    return new TableAssert(table).as(getDescription(table));
  }

  /**
   * Creates a new instance of {@link RequestAssert}.
   * 
   * @param request The request to assert on.
   * @return The created assertion object.
   */
  public static RequestAssert then(Request request) {
    return new RequestAssert(request).as(getDescription(request));
  }

  /**
   * Creates a new instance of {@link ChangesAssert}.
   * 
   * @param changes The changes to assert on.
   * @return The created assertion object.
   */
  public static ChangesAssert then(Changes changes) {
    return new ChangesAssert(changes).as(getDescription(changes));
  }

  /**
   * Reads the bytes from a file and returns them.
   * 
   * @param file The {@link File}
   * @return The bytes of the file.
   * @throws NullPointerException If the {@code file} field is {@code null}.
   * @throws AssertJDBException If triggered, this exception wrap a possible {@link IOException} during the loading.
   */
  public static byte[] bytesContentOf(File file) {
    return Assertions.bytesContentOf(file);
  }

  /**
   * Reads the bytes from a file in the classpath and returns them.
   * 
   * @param resource The name of the file in the classpath.
   * @return The bytes of the file.
   * @throws NullPointerException If the {@code resource} field is {@code null}.
   */
  public static byte[] bytesContentFromClassPathOf(String resource) {
    return Assertions.bytesContentFromClassPathOf(resource);
  }
}
