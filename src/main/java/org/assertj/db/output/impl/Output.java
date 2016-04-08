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
package org.assertj.db.output.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.type.*;

/**
 * Interface of the output of a output of assertj-db.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public interface Output {

  /**
   * Returns the output of a {@code Table}.
   *
   * @param info  Writable information about an assertion.
   * @param table Table.
   * @return The output of a {@code Table}.
   */
  String getTableOutput(WritableAssertionInfo info, Table table);

  /**
   * Returns the output of a {@code Request}.
   *
   * @param info    Writable information about an assertion.
   * @param request Request.
   * @return The output of a {@code Request}.
   */
  String getRequestOutput(WritableAssertionInfo info, Request request);

  /**
   * Returns the output of {@code Changes}.
   *
   * @param info    Writable information about an assertion.
   * @param changes Changes.
   * @return The output of {@code Changes}.
   */
  String getChangesOutput(WritableAssertionInfo info, Changes changes);

  /**
   * Returns the output of a {@code Change}.
   *
   * @param info   Writable information about an assertion.
   * @param change Change.
   * @return The output of a {@code Change}.
   */
  String getChangeOutput(WritableAssertionInfo info, Change change);

  /**
   * Returns the output of a {@code Row}.
   *
   * @param info Writable information about an assertion.
   * @param row  Row.
   * @return The output of a {@code Row}.
   */
  String getRowOutput(WritableAssertionInfo info, Row row);

  /**
   * Returns the output of a {@code Column}.
   *
   * @param info   Writable information about an assertion.
   * @param column Column.
   * @return The output of a {@code Column}.
   */
  String getColumnOutput(WritableAssertionInfo info, Column column);

  /**
   * Returns the output of a {@code Column} of a {@code Change}.
   *
   * @param info   Writable information about an assertion.
   * @param columnName Column name.
   * @param valueAtStartPoint Value at start point.
   * @param valueAtEndPoint Value at end point.
   * @return The output of a {@code Column} of a {@code Change}.
   */
  String getChangeColumnOutput(WritableAssertionInfo info, String columnName,
                               Value valueAtStartPoint, Value valueAtEndPoint);

  /**
   * Returns the output of a value.
   *
   * @param info       Writable information about an assertion.
   * @param value      Value.
   * @return The output of a value.
   */
  String getValueOutput(WritableAssertionInfo info, Value value);
}
