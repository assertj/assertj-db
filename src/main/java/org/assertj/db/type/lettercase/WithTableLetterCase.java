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
package org.assertj.db.type.lettercase;

/**
 * Defines the method to get the letter case of the tables.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public interface WithTableLetterCase {

  /**
   * Returns the letter case of the tables.
   *
   * @return The letter case of the tables.
   */
  LetterCase getTableLetterCase();
}
