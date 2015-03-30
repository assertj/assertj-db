package org.assertj.db.api.assertions.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.*;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertionsOnChangeType} class :
 * {@link AssertionsOnChangeType#isOfType(org.assertj.db.api.AbstractAssert, org.assertj.core.api.WritableAssertionInfo, org.assertj.db.type.Change, org.assertj.db.type.ChangeType)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertionsOnChangeType_IsOfType_Test extends AbstractTest {

  /**
   * This method tests the {@code isOfType} assertion method.
   */
  @Test
  public void test_is_of_type() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(null, null, null);
    Row rowAtEndPoint = getRow(null, null, null);
    Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
    TableAssert tableAssert2 = AssertionsOnChangeType.isOfType(tableAssert, info, change, ChangeType.CREATION);
    assertThat(tableAssert2).isSameAs(tableAssert);
  }

  /**
   * This method should fail because the type of change is different.
   */
  @Test
  public void should_fail_because_type_of_change_is_different() throws Exception {
    WritableAssertionInfo info = new WritableAssertionInfo();
    info.description("description");
    Table table = new Table();
    TableAssert tableAssert = assertThat(table);
    Row rowAtStartPoint = getRow(null, null, null);
    Row rowAtEndPoint = getRow(null, null, null);
    Change change = getChange(DataType.TABLE, "test", ChangeType.CREATION, rowAtStartPoint, rowAtEndPoint);
    try {
      AssertionsOnChangeType.isOfType(tableAssert, info, change, ChangeType.MODIFICATION);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      assertThat(e.getMessage()).isEqualTo("[description] \n"
                                           + "Expecting:\n"
                                           + "to be of type\n"
                                           + "  <MODIFICATION>\n"
                                           + "but was of type\n"
                                           + "  <CREATION>");
    }
  }
}
