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
package org.assertj.db.api;

import org.assertj.db.api.assertions.AssertOnNumberOfColumns;
import org.assertj.db.api.assertions.AssertOnRowEquality;
import org.assertj.db.api.assertions.impl.AssertionsOnNumberOfColumns;
import org.assertj.db.api.assertions.impl.AssertionsOnRowEquality;
import org.assertj.db.api.navigation.RowAssert;
import org.assertj.db.api.navigation.ToValueFromRow;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.AbstractDbData;
import org.assertj.db.type.Row;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Base class for all {@link Row}s assertions.
 *
 * @author Régis Pouiller
 *
 * @param <D> The class of the actual value (an sub-class of {@link AbstractDbData}).
 * @param <A> The class of the original assertion (an sub-class of {@link AbstractDbAssert}).
 * @param <C> The class of the equivalent column assertion (an sub-class of {@link AbstractColumnAssert}).
 * @param <CV> The class of the equivalent column assertion on the value (an sub-class of {@link AbstractColumnValueAssert}
 *          ).
 * @param <R> The class of this assertion (an sub-class of {@link AbstractRowAssert}).
 * @param <RV> The class of this assertion on the value (an sub-class of {@link AbstractRowValueAssert}).
 */
public abstract class AbstractRowAssert<D extends AbstractDbData<D>, A extends AbstractDbAssert<D, A, C, CV, R, RV>, C extends AbstractColumnAssert<D, A, C, CV, R, RV>, CV extends AbstractColumnValueAssert<D, A, C, CV, R, RV>, R extends AbstractRowAssert<D, A, C, CV, R, RV>, RV extends AbstractRowValueAssert<D, A, C, CV, R, RV>>
    extends AbstractSubAssert<D, A, R, RV, C, CV, R, RV>
    implements RowAssert,
    ToValueFromRow<RV>,
    AssertOnRowEquality<R>,
    AssertOnNumberOfColumns<R> {

    /**
     * Row on which do the assertion.
     */
    private final Row row;

    /**
     * Constructor.
     *
     * @param originalDbAssert The original assert. That could be a {@link RequestAssert} or a {@link TableAssert}.
     * @param selfType Type of this assertion class : a sub-class of {@code AbstractRowAssert}.
     * @param valueType Class of the assert on the value : a sub-class of {@code AbstractRowValueAssert}.
     */
    AbstractRowAssert(A originalDbAssert, Class<R> selfType, Class<RV> valueType, Row row) {
        super(originalDbAssert, selfType, valueType);
        this.row = row;
    }

    /** {@inheritDoc} */
    @Override
    protected RV getValueAssertInstance(Class<RV> valueAssertType, int index, Object value) throws Exception {
        List<String> columnsNameList = row.getColumnsNameList();
        String columnName = columnsNameList.get(index);
        Constructor<RV> constructor = valueAssertType
            .getDeclaredConstructor(myself.getClass(), String.class, Object.class);
        RV instance = constructor.newInstance(this, columnName, value);
        return instance
            .as("Value at index " + index + " (column name : " + columnName + ") of " + info.descriptionText());
    }

    /** {@inheritDoc} */
    @Override
    protected List<Object> getValuesList() {
        return row.getValuesList();
    }

    /** {@inheritDoc} */
    @Override
    public RV value(String columnName) {
        if (columnName == null) {
            throw new NullPointerException("Column name must be not null");
        }
        List<String> columnsNameList = row.getColumnsNameList();
        int index = columnsNameList.indexOf(columnName.toUpperCase());
        if (index == -1) {
            throw new AssertJDBException("Column <%s> does not exist", columnName);
        }
        return getValueAssertInstance(index);
    }

    /** {@inheritDoc} */
    @Override
    public R hasNumberOfColumns(int expected) {
        return AssertionsOnNumberOfColumns.hasNumberOfColumns(myself, info, getValuesList().size(), expected);
    }

    /** {@inheritDoc} */
    @Override
    public R hasValues(Object... expected) {
        return AssertionsOnRowEquality.hasValues(myself, info, getValuesList(), expected);
    }
}
