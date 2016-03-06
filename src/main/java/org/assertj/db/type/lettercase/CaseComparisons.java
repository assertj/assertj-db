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
package org.assertj.db.type.lettercase;

/**
 * Comparisons on {@link java.lang.String} which consider the case.
 * @author Régis Pouiller
 * @since 1.1.0
 */
public enum CaseComparisons implements CaseComparison {

  /**
   * Comparison on {@link java.lang.String} which ignore the case.
   */
  IGNORE {
    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(String value1, String value2) {
      if (value1 == null) {
        if (value2 == null) {
          return 0;
        }
        else {
          return -1;
        }
      }
      else if (value2 == null) {
        return 1;
      }
      return value1.compareToIgnoreCase(value2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEqual(String value1, String value2) {
      if (value1 == null) {
        return value2 == null;
      }
      return value1.equalsIgnoreCase(value2);
    }
  },
  /**
   * Comparison on {@link java.lang.String} which strictly consider the case.
   */
  STRICT {
    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(String value1, String value2) {
      if (value1 == null) {
        if (value2 == null) {
          return 0;
        }
        else {
          return -1;
        }
      }
      else if (value2 == null) {
        return 1;
      }
      return value1.compareTo(value2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEqual(String value1, String value2) {
      if (value1 == null) {
        return value2 == null;
      }
      return value1.equals(value2);
    }
  };
}
