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
package org.assertj.db.error;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;

/**
 * Creates an error message indicating that an assertion that verifies that a value at end point is equal to another value.
 *
 * @author Régis Pouiller
 *
 */
public class ShouldBeEqualWithEndPoint extends BasicErrorMessageFactory {

    private static final String EXPECTED_MESSAGE = "%nExpecting that end point:%n  <%s>%nto be equal to: %n  <%s>";
    private static final String EXPECTED_MESSAGE_WITHOUT_VALUES = "%nExpecting that end point to be equal to the expected value but was not equal";

    /**
     * Constructor.
     *
     * @param actual The actual value in the failed assertion.
     * @param expected The expected value to compare to.
     */
    private ShouldBeEqualWithEndPoint(Object actual, Object expected) {
        super(EXPECTED_MESSAGE, actual, expected);
    }

    /**
     * Constructor.
     */
    private ShouldBeEqualWithEndPoint() {
        super(EXPECTED_MESSAGE_WITHOUT_VALUES);
    }

    /**
     * Creates a new <code>{@link org.assertj.db.error.ShouldBeEqualWithEndPoint}</code>.
     *
     * @param actual The actual value in the failed assertion.
     * @param expected The expected value to compare to.
     * @return the created {@code ErrorMessageFactory}.
     */
    public static ErrorMessageFactory shouldBeEqualWithEndPoint(Object actual, Object expected) {
        return new ShouldBeEqualWithEndPoint(actual, expected);
    }

    /**
     * Creates a new <code>{@link org.assertj.db.error.ShouldBeEqualWithEndPoint}</code>.
     *
     * @return the created {@code ErrorMessageFactory}.
     */
    public static ErrorMessageFactory shouldBeEqualWithEndPoint() {
        return new ShouldBeEqualWithEndPoint();
    }
}
