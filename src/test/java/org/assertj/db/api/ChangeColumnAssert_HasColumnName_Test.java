package org.assertj.db.api;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Test on the {@code hasColumnName} assertion method on {@code Column}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeColumnAssert_HasColumnName_Test extends AbstractTest {

  /**
   * This method test the assertion on the column name of a {@code Column} from a {@code Change}.
   */
  @Test
  @NeedReload
  public void test_column_name() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).change()
            .column().hasColumnName("id")
            .column().hasColumnName("name");
  }

  /**
   * This test should fail because the column name is null.
   */
  @Test(expected = NullPointerException.class)
  @NeedReload
  public void should_fail_because_the_parameter_is_null() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).change().column().hasColumnName(null);
  }

  /**
   * This test should fail because the column name is different ("id").
   */
  @Test
  @NeedReload
  public void should_fail_because_the_column_name_is_different() {
    try {
      Changes changes = new Changes(source).setStartPointNow();
      updateChangesForTests();
      changes.setEndPointNow();

      assertThat(changes).change().column().hasColumnName("not_that");

      fail("An exception must be raised");
    }
    catch (AssertionError e) {
      assertThat(e.getLocalizedMessage()).isEqualTo("[Column at index 0 of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                                    + "Expecting :\n" + "  \"not_that\"\n"
                                                    + "to be the name of the column but was:\n" + "  \"ID\"");
    }
  }
}
