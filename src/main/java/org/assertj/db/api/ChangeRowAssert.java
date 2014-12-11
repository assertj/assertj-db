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

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Row;

import java.util.HashMap;
import java.util.Map;

/**
 * Assertion methods about a {@code Row} of a {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeRowAssert extends AbstractAssertWithOriginAssert<ChangeRowAssert, ChangeAssert> implements OriginAssert {

  /**
   * The actual row on which the assertion is.
   */
  private final Row row;

  /**
   * Index of the next value to get.
   */
  private int indexNextValue;
  /**
   * Map the value assert with their index in key (contains the value assert already generated).
   */
  private Map<Integer, ChangeValueAssert> changeValueAssertMap = new HashMap<Integer, ChangeValueAssert>();

  /**
   * Constructor.
   *
   * @param originalAssert The original assert.
   * @param row The {@link Row} on which are the assertions.
   */
  ChangeRowAssert(ChangeAssert originalAssert, Row row) {
    super(ChangeRowAssert.class, originalAssert);
    this.row = row;
  }

  /**
   * Returns the value at the {@code index} in parameter.
   *
   * @param index The index corresponding to the value.
   * @return The value.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  private Object getValue(int index) {
    int size = row.getValuesList().size();
    if (index < 0 || index >= size) {
      throw new AssertJDBException("Index %s out of the limits [0, %s[", index, size);
    }
    Object object = row.getValuesList().get(index);
    indexNextValue = index + 1;
    return object;
  }

  /**
   * Gets an instance of change assert corresponding to the index. If this instance is already instanced, the method
   * returns it from the cache.
   *
   * @param index Index of the change on which is the instance of change assert.
   * @return The change assert implementation.
   */
  private ChangeValueAssert getChangeValueAssertInstance(int index) {
    if (changeValueAssertMap.containsKey(index)) {
      ChangeValueAssert changeValueAssert = changeValueAssertMap.get(index);
      indexNextValue = index + 1;
      return changeValueAssert;
    }

    ChangeValueAssert instance = new ChangeValueAssert(this, getValue(index));
    changeValueAssertMap.put(index, instance);
    return instance.as("Value at index " + index + " of " + info.descriptionText());
  }

  /**
   * Returns assertion methods on the next value in the list of values.
   *
   * @return An object to make assertions on the next value.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeValueAssert value() {
    return getChangeValueAssertInstance(indexNextValue);
  }

  /**
   * Returns assertion methods on the value at the {@code index} in parameter.
   *
   * @param index The index corresponding to the value.
   * @return An object to make assertions on the value.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeValueAssert value(int index) {
    return getChangeValueAssertInstance(index);
  }
}
