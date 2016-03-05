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
package org.assertj.db.display;

import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;

import static org.assertj.db.util.Descriptions.getDescription;

/**
 * Entry point of all the displays.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public class Displaying {

  /**
   * Private constructor of the entry point.
   */
  private Displaying() {
    // empty
  }

  /**
   * Creates a new instance of {@link TableDisplay}.
   *
   * @param table The table to display on.
   * @return The created display object.
   */
  public static TableDisplay display(Table table) {
    return new TableDisplay(table).as(getDescription(table));
  }

  /**
   * Creates a new instance of {@link RequestDisplay}.
   *
   * @param request The request to display on.
   * @return The created display object.
   */
  public static RequestDisplay display(Request request) {
    return new RequestDisplay(request).as(getDescription(request));
  }

  /**
   * Creates a new instance of {@link ChangesDisplay}.
   *
   * @param changes The changes to display on.
   * @return The created display object.
   */
  public static ChangesDisplay display(Changes changes) {
    return new ChangesDisplay(changes).as(getDescription(changes));
  }
}
