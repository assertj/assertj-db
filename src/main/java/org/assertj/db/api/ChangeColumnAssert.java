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
 * Assertion methods about a {@code Column} of a {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeColumnAssert extends AbstractAssertWithColumnsAndRowsFromChange<ChangeColumnAssert, ChangeAssert>
        implements OriginAssertWithValuesFromColumn {

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
   * @param originalAssert    The original assert.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint   The value at end point.
   */
  ChangeColumnAssert(ChangeAssert originalAssert, Object valueAtStartPoint, Object valueAtEndPoint) {
    super(ChangeColumnAssert.class, originalAssert);
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
}
