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
package org.assertj.db.api.assertions;

import org.assertj.db.type.DataType;

/**
 * Defines the assertion methods on the type of data (from a table or from a request).
 * <p>The different type of data are enumerated in {@link org.assertj.db.type.DataType}.</p>
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnDataType<T extends AssertOnModifiedColumns<T>> {

  /**
   * Verifies that the data type on which is the change is equal to the type in parameter.
   * <p>
   * Example where the assertion verifies that the change is on data type {@code TABLE} :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isOnDataType(DataType.TABLE);
   * </code>
   * </pre>
   *
   * @param expected The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   * @see #isOnTable()
   * @see #isOnRequest()
   * @see org.assertj.db.api.ChangeAssert#isOnDataType(org.assertj.db.type.DataType)
   */
  public T isOnDataType(DataType expected);

  /**
   * Verifies that the data type on which is the change is a table.
   * <p>
   * Example where the assertion verifies that the change is on data type {@code TABLE} :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isOnTable();
   * </code>
   * </pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOnDataType(DataType.TABLE);
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of data is not table.
   * @see org.assertj.db.type.DataType#TABLE
   * @see org.assertj.db.api.ChangeAssert#isOnTable()
   */
  public T isOnTable();

  /**
   * Verifies that the data type on which is the change is a request.
   * <p>
   * Example where the assertion verifies that the change is on data type {@code REQUEST} :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isOnRequest();
   * </code>
   * </pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOnDataType(DataType.REQUEST);
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of data is not request.
   * @see org.assertj.db.type.DataType#REQUEST
   * @see org.assertj.db.api.ChangeAssert#isOnRequest()
   */
  public T isOnRequest();

  /**
   * Verifies that the change is on a table with the name in parameter.
   * <p>
   * Example where the assertion verifies that the change is on {@code TABLE} called movie :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isOnTable("movie");
   * </code>
   * </pre>
   *
   * @param name The name of the table on which is the change.
   * @return {@code this} assertion object.
   * @throws AssertionError                 If the type of data is not table or if the table have a different name.
   * @throws java.lang.NullPointerException If the name in parameter is {@code null}.
   * @see org.assertj.db.api.ChangeAssert#isOnTable(String)
   */
  public T isOnTable(String name);
}
