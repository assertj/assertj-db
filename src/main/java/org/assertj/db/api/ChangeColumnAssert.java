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

import org.assertj.db.util.AssertionsOnChangeColumn;
import org.assertj.db.util.AssertionsOnColumn;

/**
 * Assertion methods about a {@code Column} of a {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeColumnAssert extends AbstractAssertWithColumnsAndRowsFromChange<ChangeColumnAssert, ChangeAssert>
        implements OriginAssertWithValuesFromColumn, AssertOnColumnOfChange<ChangeColumnAssert> {

  /**
   * The name of the column.
   */
  private final String columnName;
  /**
   * The actual value at start point.
   */
  private final Object valueAtStartPoint;

  /**
   * The actual value at end point.
   */
  private final Object valueAtEndPoint;

  /**
   * The assertion on the value at start point.
   */
  private ChangeColumnValueAssert changeColumnValueAssertAtStartPoint;
  /**
   * The assertion on the value at end point.
   */
  private ChangeColumnValueAssert changeColumnValueAssertAtEndPoint;

  /**
   * Constructor.
   *
   * @param columnName        The column name.
   * @param originalAssert    The original assert.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   */
  ChangeColumnAssert(ChangeAssert originalAssert, String columnName, Object valueAtStartPoint, Object valueAtEndPoint) {
    super(ChangeColumnAssert.class, originalAssert);
    this.columnName = columnName;
    this.valueAtStartPoint = valueAtStartPoint;
    this.valueAtEndPoint = valueAtEndPoint;
  }

  /**
   * Returns assertion methods on the value at the start point.
   *
   * @return An object to make assertions on the next value.
   */
  public ChangeColumnValueAssert valueAtStartPoint() {
    if (changeColumnValueAssertAtStartPoint == null) {
      StringBuilder stringBuilder = new StringBuilder("Value at start point of ");
      stringBuilder.append(info.descriptionText());
      changeColumnValueAssertAtStartPoint = new ChangeColumnValueAssert(this, valueAtStartPoint)
              .as(stringBuilder.toString());
    }
    return changeColumnValueAssertAtStartPoint;
  }

  /**
   * Returns assertion methods on the value at the end point.
   *
   * @return An object to make assertions on the value.
   */
  public ChangeColumnValueAssert valueAtEndPoint() {
    if (changeColumnValueAssertAtEndPoint == null) {
      StringBuilder stringBuilder = new StringBuilder("Value at end point of ");
      stringBuilder.append(info.descriptionText());
      changeColumnValueAssertAtEndPoint = new ChangeColumnValueAssert(this, valueAtEndPoint)
              .as(stringBuilder.toString());
    }
    return changeColumnValueAssertAtEndPoint;
  }

  /**
   * Verifies that the column is modified between the start point and the end point.
   * <p>
   * Example where the assertion verifies that :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).column().isModified();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeColumnAssert isModified() {
    return AssertionsOnChangeColumn.isModified(myself, info, valueAtStartPoint, valueAtEndPoint);
  }

  /**
   * Verifies that the column is not modified between the start point and the end point.
   * <p>
   * Example where the assertion verifies that :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).column().isNotModified();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeColumnAssert isNotModified() {
    return AssertionsOnChangeColumn.isNotModified(myself, info, valueAtStartPoint, valueAtEndPoint);
  }

  /**
   * Verifies that the values at the start point and the end point are equal to the parameter.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * "Ellen Louise Ripley" :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValuesEqualTo("Ellen Louise Ripley");
   * </code></pre>
   *
   * @param expected The expected value.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values are not equal to the parameter.
   */
  public ChangeColumnAssert hasValuesEqualTo(Object expected) {
    return AssertionsOnChangeColumn.hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expected);
  }

  /**
   * Verifies that the values at the start point and the end point are equal to a parameter for start point and a parameter for end point.
   * <p>
   * Example where the assertion verifies that the values of the first {@code Column} of the {@code Table} are equal to
   * "Sigourney" at start point and "Susan Alexandra" at end point :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasValuesEqualTo("Sigourney", "Susan Alexandra");
   * </code></pre>
   *
   * @param expectedAtStartPoint The expected value at start point.
   * @param expectedAtEndPoint   The expected value at end point.
   * @return {@code this} assertion object.
   * @throws AssertionError If the values are not equal to the parameters.
   */
  public ChangeColumnAssert hasValuesEqualTo(Object expectedAtStartPoint, Object expectedAtEndPoint) {
    return AssertionsOnChangeColumn
            .hasValuesEqualTo(myself, info, valueAtStartPoint, valueAtEndPoint, expectedAtStartPoint,
                              expectedAtEndPoint);
  }

  /**
   * Verifies that the name of a column is equal to parameter.
   * <p>
   * Example where the assertion verifies that the column name of the first {@code Column} of the {@code Table} is equal to
   * "title" :
   * </p>
   * <pre><code class='java'>
   * assertThat(changes).change(1).column().hasColumnName("title");
   * </code></pre>
   *
   * @param columnName The expected column name.
   * @return {@code this} assertion object.
   * @throws AssertionError If the column name is not equal to the parameter.
   */
  public ChangeColumnAssert hasColumnName(String columnName) {
    return AssertionsOnColumn.hasColumnName(myself, info, this.columnName, columnName);
  }
}
