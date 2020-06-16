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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.type.lettercase;

/**
 * Conversions of the case of a {@link java.lang.String}.
 * @author Régis Pouiller
 * @since 1.1.0
 */
public enum CaseConversions implements CaseConversion {

  /**
   * Lower conversion of the case of a {@link java.lang.String}.
   */
  LOWER {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConversionName() {
      return "LOWER - Lower case conversion";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convert(String value) {
      if (value == null) {
        return null;
      }
      return value.toLowerCase();
    }
  },
  /**
   * Upper conversion of the case of a {@link java.lang.String}.
   */
  UPPER {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConversionName() {
      return "UPPER - Upper case conversion";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convert(String value) {
      if (value == null) {
        return null;
      }
      return value.toUpperCase();
    }
  },
  /**
   * No conversion of the case of a {@link java.lang.String}.
   */
  NO {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getConversionName() {
      return "NO - No case conversion";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convert(String value) {
      return value;
    }
  }
}
