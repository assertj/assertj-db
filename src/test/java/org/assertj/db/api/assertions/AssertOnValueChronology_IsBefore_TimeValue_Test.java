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
package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeColumnValueAssert;
import org.assertj.db.api.TableColumnValueAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

import java.text.ParseException;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnValueChronology} class :
 * {@link org.assertj.db.api.assertions.AssertOnValueChronology#isBefore(org.assertj.db.type.TimeValue)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnValueChronology_IsBefore_TimeValue_Test extends AbstractTest {

    /**
     * This method tests the {@code isBefore} assertion method.
     */
    @Test
    @NeedReload
    public void test_is_before() throws ParseException {
        Table table = new Table(source, "test");
        Changes changes = new Changes(table).setStartPointNow();
        update("update test set var14 = 1 where var1 = 1");
        changes.setEndPointNow();

        ChangeColumnValueAssert changeColumnValueAssert = assertThat(changes).change().column("var8").valueAtEndPoint();
        ChangeColumnValueAssert changeColumnValueAssert2 = changeColumnValueAssert
            .isBefore(TimeValue.parse("09:46:31"));
        Assertions.assertThat(changeColumnValueAssert).isSameAs(changeColumnValueAssert2);

        TableColumnValueAssert tableColumnValueAssert = assertThat(table).column("var8").value();
        TableColumnValueAssert tableColumnValueAssert2 = tableColumnValueAssert.isBefore(TimeValue.parse("09:46:31"));
        Assertions.assertThat(tableColumnValueAssert).isSameAs(tableColumnValueAssert2);
    }

    /**
     * This method should fail because the value is after or equal to.
     */
    @Test
    @NeedReload
    public void should_fail_because_value_is_after_or_equal_to() throws ParseException {
        Table table = new Table(source, "test");
        Changes changes = new Changes(table).setStartPointNow();
        update("update test set var14 = 1 where var1 = 1");
        changes.setEndPointNow();

        try {
            assertThat(changes).change().column("var8").valueAtEndPoint().isBefore(TimeValue.parse("09:46:30"));
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format(
                "[Value at end point of Column at index 7 (column name : VAR8) of Change at index 0 (with primary key : [1]) of Changes on test table of 'sa/jdbc:h2:mem:test' source] %n"
                + "Expecting:%n"
                + "  <09:46:30.000000000>%n"
                + "to be before %n"
                + "  <09:46:30.000000000>"));
        }
        try {
            assertThat(table).column("var8").value().isBefore(TimeValue.parse("09:46:30"));
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(
                String.format("[Value at index 0 of Column at index 7 (column name : VAR8) of test table] %n"
                              + "Expecting:%n"
                              + "  <09:46:30.000000000>%n"
                              + "to be before %n"
                              + "  <09:46:30.000000000>"));
        }
    }
}
