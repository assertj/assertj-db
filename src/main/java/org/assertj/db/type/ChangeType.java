/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.type;

/**
 * Enumeration of the different types of change that are possible in the database (creation, modification or deletion of a row).
 *
 * @author Régis Pouiller
 */
public enum ChangeType {

  /**
   * The change is a creation of a row.
   * <p>At the start point the row do not exist and at the end point it is created.</p>
   */
  CREATION,
  /**
   * The change is a modification of a row.
   * <p>The row is modified between the start point and at the end point.</p>
   */
  MODIFICATION,
  /**
   * The change is a deletion of a row.
   * <p>At the start point the row exists but anymore at the end point.</p>
   */
  DELETION,
}
