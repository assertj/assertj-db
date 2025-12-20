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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.util;

import java.util.List;

import org.assertj.db.type.lettercase.CaseComparison;

/**
 * Comparator for the names.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public enum NameComparator {

  /**
   * The singleton
   */
  INSTANCE;

  /**
   * Returns if {@code namesList} contains {@code name} by comparing using {@code comparison}.
   *
   * @param namesList  List of names which can contain the {@code name}.
   * @param name       The name to search.
   * @param comparison Case comparison to compare the name.
   * @return The result.
   */
  public boolean contains(List<String> namesList, String name, CaseComparison comparison) {
    for (String nameInTheList : namesList) {
      if (comparison.isEqual(nameInTheList, name)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the index of {@code name}in {@code namesList} by comparing using {@code comparison}.
   *
   * @param namesList  List of names which can contain the {@code name}.
   * @param name       The name to search.
   * @param comparison Case comparison to compare the name.
   * @return The result.
   */
  public int indexOf(List<String> namesList, String name, CaseComparison comparison) {
    int index = 0;
    for (String nameInTheList : namesList) {
      if (comparison.isEqual(nameInTheList, name)) {
        return index;
      }
      index++;
    }
    return -1;
  }
}
