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
package org.assertj.db.output;

import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;

import static org.assertj.db.util.Descriptions.getDescription;

/**
 * Entry point of all the outputs.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public class Outputs {

  /**
   * Private constructor of the entry point.
   */
  private Outputs() {
    // empty
  }

  /**
   * Creates a new instance of {@link TableOutputter}.
   *
   * @param table The table to output on.
   * @return The created output object.
   */
  public static TableOutputter output(Table table) {
    return new TableOutputter(table).as(getDescription(table));
  }

  /**
   * Creates a new instance of {@link RequestOutputter}.
   *
   * @param request The request to output on.
   * @return The created output object.
   */
  public static RequestOutputter output(Request request) {
    return new RequestOutputter(request).as(getDescription(request));
  }

  /**
   * Creates a new instance of {@link ChangesOutputter}.
   *
   * @param changes The changes to output on.
   * @return The created output object.
   */
  public static ChangesOutputter output(Changes changes) {
    return new ChangesOutputter(changes).as(getDescription(changes));
  }
}
