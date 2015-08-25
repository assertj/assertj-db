package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeColumnValueAssert;
import org.assertj.db.api.TableColumnValueAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.api.assertions.AssertOnValueType} class :
 * {@link org.assertj.db.api.assertions.AssertOnValueType#isUUID()} method.
 *
 * @author Otoniel Isidoro (otoniel.isidoro@sofist.com.br)
 */
public class AssertOnValueType_IsUUID_Test extends AbstractTest {

    /**
     * This method tests the {@code isUUID} assertion method.
     */
    @Test
    @NeedReload
    public void test_is_UUID() {
        Table table = new Table(source, "test");
        Changes changes = new Changes(table).setStartPointNow();
        update("update test set var15 = 'F96EC595-CE91-47CC-9152-CCC8AC48AAD6' where var1 = 1");
        changes.setEndPointNow();

        ChangeColumnValueAssert changeColumnValueAssert = assertThat(changes).change().column("var15")
                                                                             .valueAtEndPoint();
        ChangeColumnValueAssert changeColumnValueAssert2 = changeColumnValueAssert.isUUID();
        Assertions.assertThat(changeColumnValueAssert).isSameAs(changeColumnValueAssert2);

        TableColumnValueAssert tableColumnValueAssert = assertThat(table).column("var15").value();
        TableColumnValueAssert tableColumnValueAssert2 = tableColumnValueAssert.isUUID();
        Assertions.assertThat(tableColumnValueAssert).isSameAs(tableColumnValueAssert2);
    }

    /**
     * This method should fail because the value is not a UUID.
     */
    @Test
    @NeedReload
    public void should_fail_because_value_is_not_a_UUID() {
        Table table = new Table(source, "test");
        Changes changes = new Changes(table).setStartPointNow();
        update("update test set var14 = 1 where var1 = 1");
        changes.setEndPointNow();

        try {
            assertThat(changes).change().column("var1").valueAtEndPoint().isUUID();
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format(
                "[Value at end point of Column at index 0 (column name : VAR1) of Change at index 0 (with primary key : [1]) of Changes on test table of 'sa/jdbc:h2:mem:test' source] %n"
                + "Expecting:%n"
                + "  <1>%n"
                + "to be of type%n"
                + "  <UUID>%n"
                + "but was of type%n"
                + "  <NUMBER>"));
        }
        try {
            assertThat(table).column("var1").value().isUUID();
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(
                String.format("[Value at index 0 of Column at index 0 (column name : VAR1) of test table] %n"
                              + "Expecting:%n"
                              + "  <1>%n"
                              + "to be of type%n"
                              + "  <UUID>%n"
                              + "but was of type%n"
                              + "  <NUMBER>"));
        }
    }
}
