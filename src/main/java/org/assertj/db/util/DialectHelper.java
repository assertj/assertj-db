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
package org.assertj.db.util;

import org.assertj.db.exception.AssertJDBException;

public class DialectHelper {

    public static final String ASSERTJ_DB_DIALECT_PROPERTY_NAME = "ASSERTJ_DB_DIALECT";
    private static final String ASSERTJ_DB_DIALECT = System.getProperty(ASSERTJ_DB_DIALECT_PROPERTY_NAME);

    public static String getColumnName(String name) {
        if (name == null)
            throw new AssertJDBException("ColumnName is mandatory");
        return ASSERTJ_DB_DIALECT == null || "H2".equals(ASSERTJ_DB_DIALECT) ? name.toUpperCase() : name;
    }
}
