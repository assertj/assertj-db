package org.assertj.db.api;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Test on the {@code hasValuesEqualTo} assertion method on {@code Column}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeColumnAssert_HasValuesEqualTo_Test extends AbstractTest {

  /**
   * This method test the assertion on the values of a {@code Column} from a {@code Change}.
   */
  @Test
  @NeedReload
  public void test_values() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).changeOfModification()
                       .column(1).hasValuesEqualTo("Weaver")
                       .column().hasValuesEqualTo("Sigourney", "Susan Alexandra");
  }

  /**
   * This test should fail because the value at end point is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_the_value_at_end_point_is_different() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).changeOfModification(2).column(1).hasValuesEqualTo("Avatar");

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Value at end point of Column at index 1 of Change at index 2 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only modification changes)] \n"
                                                    + "Expecting:\n" + "  <\"The Avatar\">\n" + "to be equal to: \n"
                                                    + "  <\"Avatar\">");
    }
  }
}
