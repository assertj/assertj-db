package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangeColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * * Tests on {@link org.assertj.db.api.assertions.AssertOnColumnOfChangeEquality} class :
 * {@link org.assertj.db.api.assertions.AssertOnColumnOfChangeEquality#hasValues(UUID)} method.
 *
 * @author Otoniel Isidoro (otoniel.isidoro@sofist.com.br)
 */
public class AssertOnColumnOfChangeEquality_HasValues_One_UUID_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValues} assertion method.
   */
  @Test
  @NeedReload
  public void test_have_values_equal_to() {
    Changes changes = new Changes(source).setStartPointNow();
    update("update test set var14 = 1 where var1 = 1");
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).change();
    ChangeColumnAssert changeColumnAssert = changeAssert.column("var15");
    ChangeColumnAssert changeColumnAssert2 = changeColumnAssert
        .hasValues(UUID.fromString("30b443ae-c0c9-4790-9bec-ce1380808435"));
    Assertions.assertThat(changeColumnAssert).isSameAs(changeColumnAssert2);
  }

  /**
   * This method should fail because the value at start point is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_at_start_point_is_different() {
    Changes changes = new Changes(source).setStartPointNow();
    update("insert into test(var1, var15) values(5, 'F96EC595-CE91-47CC-9152-CCC8AC48AAD6')");
    changes.setEndPointNow();

    try {
      assertThat(changes).change().column("var15").hasValues(UUID.fromString("f96ec595-ce91-47cc-9152-ccc8ac48aad6"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format(
          "[Column at index 14 (column name : VAR15) of Change at index 0 (on table : TEST and with primary key : [5]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] %n"
          + "Expecting that start point:%n"
          + "  <null>%n"
          + "to be equal to: %n"
          + "  <f96ec595-ce91-47cc-9152-ccc8ac48aad6>"));
    }
  }

  /**
   * This method should fail because the value at end point is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_value_at_end_point_is_different() {
    Changes changes = new Changes(source).setStartPointNow();
    update("delete from test where var1 = 1");
    changes.setEndPointNow();

    try {
      assertThat(changes).change().column("var15").hasValues(UUID.fromString("30b443ae-c0c9-4790-9bec-ce1380808435"));
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo(String.format(
          "[Column at index 14 (column name : VAR15) of Change at index 0 (on table : TEST and with primary key : [1]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] %n"
          + "Expecting that end point:%n"
          + "  <null>%n"
          + "to be equal to: %n"
          + "  <30b443ae-c0c9-4790-9bec-ce1380808435>"));
    }
  }
}
