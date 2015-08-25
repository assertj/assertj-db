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
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertOnColumnEquality} class :
 * {@link org.assertj.db.api.assertions.AssertOnColumnEquality#hasValues(org.assertj.db.type.DateValue...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnColumnEquality_HasValues_DateValue_Test extends AbstractTest {

    /**
     * This method tests the {@code hasValues} assertion method.
     */
    @Test
    @NeedReload
    public void test_has_values() throws Exception {
        update("update test2 set var10 = '2014-05-24' where var1 = 1");
        Table table = new Table(source, "test");
        TableColumnAssert tableColumnAssert = assertThat(table).column("var9");
        TableColumnAssert tableColumnAssertReturn = tableColumnAssert.hasValues(
            DateValue.of(2014, 5, 24),
            DateValue.of(2014, 5, 30),
            DateValue.of(2014, 5, 30),
            DateValue.of(2014, 5, 30));
        Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssertReturn);

        Table table2 = new Table(source, "test2");
        TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var9");
        TableColumnAssert tableColumnAssertReturn2 = tableColumnAssert2.hasValues(DateValue.of(2014, 5, 24),
                                                                                  null);
        Assertions.assertThat(tableColumnAssert2).isSameAs(tableColumnAssertReturn2);
        TableColumnAssert tableColumnAssert3 = assertThat(table2).column("var10");
        TableColumnAssert tableColumnAssertReturn3 = tableColumnAssert3.hasValues(DateValue.of(2014, 5, 24),
                                                                                  null);
        Assertions.assertThat(tableColumnAssert3).isSameAs(tableColumnAssertReturn3);
    }

    /**
     * This method should fail because the values are different.
     */
    @Test
    public void should_fail_because_values_are_different() throws Exception {
        Table table = new Table(source, "test");
        TableColumnAssert tableColumnAssert = assertThat(table).column("var9");
        try {
            tableColumnAssert.hasValues(DateValue.of(2014, 5, 24),
                                        DateValue.of(2014, 5, 29),
                                        DateValue.of(2014, 5, 30),
                                        DateValue.of(2014, 5, 30));
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage())
                      .isEqualTo(String.format("[Column at index 8 (column name : VAR9) of test table] %n"
                                               + "Expecting that the value at index 1:%n"
                                               + "  <2014-05-30>%n"
                                               + "to be equal to: %n"
                                               + "  <2014-05-29>"));
        }
        Table table2 = new Table(source, "test2");
        TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var9");
        try {
            tableColumnAssert2.hasValues(DateValue.of(2014, 5, 24),
                                         DateValue.of(2014, 5, 24));
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage())
                      .isEqualTo(String.format("[Column at index 8 (column name : VAR9) of test2 table] %n"
                                               + "Expecting that the value at index 1:%n"
                                               + "  <null>%n"
                                               + "to be equal to: %n"
                                               + "  <2014-05-24>"));
        }
        TableColumnAssert tableColumnAssert3 = assertThat(table2).column("var10");
        try {
            tableColumnAssert3.hasValues(DateValue.of(2014, 5, 23),
                                         null);
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage())
                      .isEqualTo(String.format("[Column at index 9 (column name : VAR10) of test2 table] %n"
                                               + "Expecting that the value at index 0:%n"
                                               + "  <2014-05-24T09:46:30.000000000>%n"
                                               + "to be equal to: %n"
                                               + "  <2014-05-23>"));
        }
    }
}
