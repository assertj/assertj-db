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
package org.assertj.db.api.navigation;

/**
 * Interface that represents a assert with values from a row.
 *
 * @param <V> The class of a assert on value (an sub-class of {@link org.assertj.db.api.navigation.ValueAssert}).
 * @author Régis Pouiller
 */
public interface WithValuesFromRow<V extends ValueAssert> {

  /**
   * Returns assertion methods on the value corresponding to the column name in parameter.
   *
   * @param columnName The column name.
   * @return An object to make assertions on the value.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws org.assertj.db.exception.AssertJDBException If there is no column with this name.
   */
  public V value(String columnName);
}
