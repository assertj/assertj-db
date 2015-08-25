/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p>
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.db.type.Row;

import java.util.Comparator;
import java.util.List;

/**
 * Comparator for the {@code Row}.
 *
 * @author Régis Pouiller
 *
 */
public enum RowComparator implements Comparator<Row> {

    /**
     * The singleton
     */
    INSTANCE;

    /**
     * Compare one array of values to another.
     * @param values1 The first array of values.
     * @param values2 The second array of values.
     * @return The result.
     * @see Comparator#compare(Object, Object)
     */
    private static int compare(Object[] values1, Object[] values2) {
        if (values1.length == values2.length) {
            for (int index = 0; index < values1.length; index++) {
                Object value1 = values1[index];
                Object value2 = values2[index];
                if (value1 == null && value2 != null) {
                    return 1;
                }
                if (value1 != null && value2 == null) {
                    return -1;
                }
                if (value1 instanceof Comparable && value2 instanceof Comparable) {
                    @SuppressWarnings("unchecked")
                    Comparable<Object> comparable1 = Comparable.class.cast(value1);
                    int compare = comparable1.compareTo(value2);
                    if (compare != 0) {
                        return compare;
                    }
                }
            }
        }
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public int compare(Row row1, Row row2) {
        Object[] pksValues1 = row1.getPksValues();
        Object[] pksValues2 = row2.getPksValues();
        int compare = compare(pksValues1, pksValues2);
        if (compare != 0) {
            return compare;
        }
        List<Object> valuesList1 = row1.getValuesList();
        List<Object> valuesList2 = row2.getValuesList();
        Object[] values1 = valuesList1.toArray(new Object[valuesList1.size()]);
        Object[] values2 = valuesList2.toArray(new Object[valuesList2.size()]);
        return compare(values1, values2);
    }
}
