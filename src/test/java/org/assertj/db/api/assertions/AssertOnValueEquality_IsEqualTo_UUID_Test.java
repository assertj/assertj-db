package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeColumnValueAssert;
import org.assertj.db.api.TableColumnValueAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 *  Tests on {@link  org.assertj.db.api.assertions.AssertOnValueEquality} class :
 * {@link  org.assertj.db.api.assertions.AssertOnValueEquality#isEqualTo(UUID)} method.
 *
 * @author Otoniel Isidoro (otoniel.isidoro@sofist.com.br)
 */
public class AssertOnValueEquality_IsEqualTo_UUID_Test extends AbstractTest {

    /**
     * This method tests the {@code isEqualTo} assertion method.
     */
    @Test
    @NeedReload
    public void test_is_equal_to() {
        Table table = new Table(source, "test");
        Changes changes = new Changes(table).setStartPointNow();
        update("update test set var15 = '2B0D1BDD-909E-4362-BA10-C930BA82718D' where var1 = 1");
        changes.setEndPointNow();

        ChangeColumnValueAssert changeColumnValueAssert = assertThat(changes).change().column("var15").valueAtEndPoint();
        ChangeColumnValueAssert changeColumnValueAssert2 = changeColumnValueAssert.isEqualTo(UUID.fromString("2b0d1bdd-909e-4362-ba10-c930ba82718d"));
        Assertions.assertThat(changeColumnValueAssert).isSameAs(changeColumnValueAssert2);

        TableColumnValueAssert tableColumnValueAssert = assertThat(table).column("var15").value();
        TableColumnValueAssert tableColumnValueAssert2 = tableColumnValueAssert.isEqualTo(UUID.fromString("2b0d1bdd-909e-4362-ba10-c930ba82718d"));
        Assertions.assertThat(tableColumnValueAssert).isSameAs(tableColumnValueAssert2);
    }

    /**
     * This method should fail because the value is no equal to.
     */
    @Test
    @NeedReload
    public void should_fail_because_value_is_not_equal_to() {
        Table table = new Table(source, "test");
        Changes changes = new Changes(table).setStartPointNow();
        update("update test set var15 = '2B0D1BDD-909E-4362-BA10-C930BA82718D' where var1 = 10");
        changes.setEndPointNow();

        try {
            assertThat(changes).change().column("var15").valueAtEndPoint().isEqualTo(UUID.fromString("0e2a1269-eff0-4233-b87b-b53e8b6f164d"));
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at end point of Column at index 14 (column name : VAR15) of Change at index 0 (with primary key : [10]) of Changes on test table of 'sa/jdbc:h2:mem:test' source] %n"
                    + "Expecting:%n"
                    + "  <2b0d1bdd-909e-4362-ba10-c930ba82718d>%n"
                    + "to be equal to: %n"
                    + "  <0e2a1269-eff0-4233-b87b-b53e8b6f164d>"));
        }
        try {
            assertThat(table).column("var15").value(1).isEqualTo(UUID.fromString("0e2a1269-eff0-4233-b87b-b53e8b6f164d"));
            fail("An exception must be raised");
        } catch (AssertionError e) {
            Assertions.assertThat(e.getMessage()).isEqualTo(String.format("[Value at index 1 of Column at index 14 (column name : VAR15) of test table] %n"
                    + "Expecting:%n"
                    + "  <2b0d1bdd-909e-4362-ba10-c930ba82718d>%n"
                    + "to be equal to: %n"
                    + "  <0e2a1269-eff0-4233-b87b-b53e8b6f164d>"));
        }
    }
}
