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
package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.internal.Failures;
import org.assertj.db.api.AbstractAssert;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.assertj.db.type.ValueType;
import org.assertj.db.util.Values;

import java.util.List;
import java.util.UUID;

import static org.assertj.db.error.ShouldBeEqual.shouldBeEqual;
import static org.assertj.db.util.Values.areEqual;

/**
 * Implements the assertion methods on the equality of a column.
 *
 * @author Régis Pouiller
 * @see org.assertj.db.api.assertions.AssertOnColumnEquality
 */
public class AssertionsOnColumnEquality {

    /**
     * To notice failures in the assertion.
     */
    private final static Failures failures = Failures.instance();

    /**
     * Private constructor.
     */
    private AssertionsOnColumnEquality() {
        // Empty
    }

    /**
     * Verifies that the values of a column are equal to booleans.
     *
     * @param <A>        The type of the assertion which call this method.
     * @param assertion  The assertion which call this method.
     * @param info       Writable information about an assertion.
     * @param valuesList The list of values.
     * @param expected The expected boolean values.
     * @return {@code this} assertion object.
     * @throws AssertionError If the values of the column are not equal to the booleans in parameter.
     */
    public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                         List<Object> valuesList, Boolean... expected) {
        AssertionsOnColumnType.isBoolean(assertion, info, valuesList, true);
        AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
        int index = 0;
        for (Object value : valuesList) {
            Boolean val = (Boolean) value;
            if (!areEqual(val, expected[index])) {
                throw failures.failure(info, shouldBeEqual(index, val, expected[index]));
            }
            index++;
        }
        return assertion;
    }

    /**
     * Verifies that the values of a column are equal to numbers.
     *
     * @param <A>        The type of the assertion which call this method.
     * @param assertion  The assertion which call this method.
     * @param info       Writable information about an assertion.
     * @param valuesList The list of values.
     * @param expected The expected numbers values.
     * @return {@code this} assertion object.
     * @throws AssertionError If the values of the column are not equal to the numbers in parameter.
     */
    public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                         List<Object> valuesList, Number... expected) {
        AssertionsOnColumnType.isNumber(assertion, info, valuesList, true);
        AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
        int index = 0;
        for (Object value : valuesList) {
            Number val = (Number) value;
            if (!areEqual(val, expected[index])) {
                throw failures.failure(info,
                                       shouldBeEqual(index,
                                                     Values.getRepresentationFromValueInFrontOfExpected(val,
                                                                                                        expected[index]),
                                                     expected[index]));
            }
            index++;
        }
        return assertion;
    }

    /**
     * Verifies that the values of a column are equal to bytes.
     *
     * @param <A>        The type of the assertion which call this method.
     * @param assertion  The assertion which call this method.
     * @param info       Writable information about an assertion.
     * @param valuesList The list of values.
     * @param expected The expected bytes values.
     * @return {@code this} assertion object.
     * @throws AssertionError If the values of the column are not equal to the bytes in parameter.
     */
    public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                         List<Object> valuesList, byte[]... expected) {
        AssertionsOnColumnType.isBytes(assertion, info, valuesList, true);
        AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
        int index = 0;
        for (Object value : valuesList) {
            if (!areEqual(value, expected[index])) {
                throw failures.failure(info, shouldBeEqual(index));
            }
            index++;
        }
        return assertion;
    }

    /**
     * Verifies that the values of a column are equal to texts.
     *
     * @param <A>        The type of the assertion which call this method.
     * @param assertion  The assertion which call this method.
     * @param info       Writable information about an assertion.
     * @param valuesList The list of values.
     * @param expected The expected text values.
     * @return {@code this} assertion object.
     * @throws AssertionError If the values of the column are not equal to the texts in parameter.
     */
    public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                         List<Object> valuesList, String... expected) {
        AssertionsOnColumnType
            .isOfAnyTypeIn(assertion, info, valuesList, ValueType.TEXT, ValueType.NUMBER, ValueType.DATE,
                           ValueType.TIME, ValueType.DATE_TIME, ValueType.NOT_IDENTIFIED);
        AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
        int index = 0;
        for (Object value : valuesList) {
            if (!areEqual(value, expected[index])) {
                throw failures.failure(info,
                                       shouldBeEqual(index, Values
                                           .getRepresentationFromValueInFrontOfExpected(value, expected[index]),
                                                     expected[index]));
            }
            index++;
        }
        return assertion;
    }

    /**
     * Verifies that the values of a column are equal to UUIDs.
     *
     * @param <A>        The type of the assertion which call this method.
     * @param assertion  The assertion which call this method.
     * @param info       Writable information about an assertion.
     * @param valuesList The list of values.
     * @param expected The expected UUIDs values.
     * @return {@code this} assertion object.
     * @throws AssertionError If the values of the column are not equal to the UUIDs in parameter.
     */
    public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                         List<Object> valuesList, UUID... expected) {
        AssertionsOnColumnType.isOfAnyTypeIn(assertion, info, valuesList, ValueType.UUID, ValueType.NOT_IDENTIFIED);
        AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
        int index = 0;
        for (Object value : valuesList) {
            if (!areEqual(value, expected[index])) {
                throw failures.failure(info,
                                       shouldBeEqual(index, Values
                                           .getRepresentationFromValueInFrontOfExpected(value, expected[index]),
                                                     expected[index]));
            }
            index++;
        }
        return assertion;
    }

    /**
     * Verifies that the values of a column are equal to date values.
     *
     * @param <A>        The type of the assertion which call this method.
     * @param assertion  The assertion which call this method.
     * @param info       Writable information about an assertion.
     * @param valuesList The list of values.
     * @param expected The expected date values.
     * @return {@code this} assertion object.
     * @throws AssertionError If the values of the column are not equal to the date values in parameter.
     */
    public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                         List<Object> valuesList, DateValue... expected) {
        AssertionsOnColumnType
            .isOfAnyTypeIn(assertion, info, valuesList, ValueType.DATE, ValueType.DATE_TIME, ValueType.NOT_IDENTIFIED);
        AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
        int index = 0;
        for (Object value : valuesList) {
            if (!areEqual(value, expected[index])) {
                throw failures.failure(info,
                                       shouldBeEqual(index, Values
                                           .getRepresentationFromValueInFrontOfExpected(value, expected[index]),
                                                     expected[index]));
            }
            index++;
        }
        return assertion;
    }

    /**
     * Verifies that the values of a column are equal to time values.
     *
     * @param <A>        The type of the assertion which call this method.
     * @param assertion  The assertion which call this method.
     * @param info       Writable information about an assertion.
     * @param valuesList The list of values.
     * @param expected The expected time values.
     * @return {@code this} assertion object.
     * @throws AssertionError If the values of the column are not equal to the time values in parameter.
     */
    public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                         List<Object> valuesList, TimeValue... expected) {
        AssertionsOnColumnType.isOfAnyTypeIn(assertion, info, valuesList, ValueType.TIME, ValueType.NOT_IDENTIFIED);
        AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
        int index = 0;
        for (Object value : valuesList) {
            if (!areEqual(value, expected[index])) {
                throw failures.failure(info,
                                       shouldBeEqual(index,
                                                     Values.getRepresentationFromValueInFrontOfExpected(value,
                                                                                                        expected[index]),
                                                     expected[index]));
            }
            index++;
        }
        return assertion;
    }

    /**
     * Verifies that the values of a column are equal to date/time values.
     *
     * @param <A>        The type of the assertion which call this method.
     * @param assertion  The assertion which call this method.
     * @param info       Writable information about an assertion.
     * @param valuesList The list of values.
     * @param expected The expected date/time values.
     * @return {@code this} assertion object.
     * @throws AssertionError If the values of the column are not equal to the date/time values in parameter.
     */
    public static <A extends AbstractAssert> A hasValues(A assertion, WritableAssertionInfo info,
                                                         List<Object> valuesList, DateTimeValue... expected) {
        AssertionsOnColumnType.isOfAnyTypeIn(assertion, info, valuesList, ValueType.DATE, ValueType.DATE_TIME,
                                             ValueType.NOT_IDENTIFIED);
        AssertionsOnNumberOfRows.hasNumberOfRows(assertion, info, valuesList.size(), expected.length);
        int index = 0;
        for (Object value : valuesList) {
            if (!areEqual(value, expected[index])) {
                throw failures.failure(info,
                                       shouldBeEqual(index,
                                                     Values.getRepresentationFromValueInFrontOfExpected(value,
                                                                                                        expected[index]),
                                                     expected[index]));
            }
            index++;
        }
        return assertion;
    }
}
