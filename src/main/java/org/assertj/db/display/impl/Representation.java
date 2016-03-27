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
package org.assertj.db.display.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.type.*;

/**
 * Interface of the representation of a display of assertj-db.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public interface Representation {

  /**
   * Returns the representation of a {@code Table}.
   *
   * @param info  Writable information about an assertion.
   * @param table Table.
   * @return The representation of a {@code Table}.
   */
  String getTableRepresentation(WritableAssertionInfo info, Table table);

  /**
   * Returns the representation of a {@code Request}.
   *
   * @param info    Writable information about an assertion.
   * @param request Request.
   * @return The representation of a {@code Request}.
   */
  String getRequestRepresentation(WritableAssertionInfo info, Request request);

  /**
   * Returns the representation of {@code Changes}.
   *
   * @param info    Writable information about an assertion.
   * @param changes Changes.
   * @return The representation of {@code Changes}.
   */
  String getChangesRepresentation(WritableAssertionInfo info, Changes changes);

  /**
   * Returns the representation of a {@code Change}.
   *
   * @param info   Writable information about an assertion.
   * @param change Change.
   * @return The representation of a {@code Change}.
   */
  String getChangeRepresentation(WritableAssertionInfo info, Change change);

  /**
   * Returns the representation of a {@code Row}.
   *
   * @param info Writable information about an assertion.
   * @param row  Row.
   * @return The representation of a {@code Row}.
   */
  String getRowRepresentation(WritableAssertionInfo info, Row row);

  /**
   * Returns the representation of a {@code Column}.
   *
   * @param info   Writable information about an assertion.
   * @param column Column.
   * @return The representation of a {@code Column}.
   */
  String getColumnRepresentation(WritableAssertionInfo info, Column column);

  /**
   * Returns the representation of a {@code Column} of a {@code Change}.
   *
   * @param info   Writable information about an assertion.
   * @param columnName Column name.
   * @param valueAtStartPoint Value at start point.
   * @param valueAtEndPoint Value at end point.
   * @return The representation of a {@code Column} of a {@code Change}.
   */
  String getChangeColumnRepresentation(WritableAssertionInfo info, String columnName,
                                       Value valueAtStartPoint, Value valueAtEndPoint);

  /**
   * Returns the representation of a value.
   *
   * @param info       Writable information about an assertion.
   * @param value      Value.
   * @return The representation of a value.
   */
  String getValueRepresentation(WritableAssertionInfo info, Value value);
}
