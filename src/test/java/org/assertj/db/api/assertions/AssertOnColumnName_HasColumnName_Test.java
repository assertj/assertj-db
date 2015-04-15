package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.*;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertOnColumnName} class :
 * {@link AssertOnColumnName#hasColumnName(String)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnColumnName_HasColumnName_Test extends AbstractTest {

  /**
   * This method tests the {@code hasColumnName} assertion method.
   */
  @Test
  @NeedReload
  public void test_has_column_name() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).change();
    ChangeColumnAssert changeColumnAssert = changeAssert.column();
    ChangeColumnAssert changeColumnAssert2 = changeColumnAssert.hasColumnName("ID");
    Assertions.assertThat(changeColumnAssert).isSameAs(changeColumnAssert2);
    ChangeRowValueAssert changeRowValueAssert = changeAssert.rowAtEndPoint().value();
    ChangeRowValueAssert changeRowValueAssert2 = changeRowValueAssert.hasColumnName("id");
    Assertions.assertThat(changeRowValueAssert).isSameAs(changeRowValueAssert2);

    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    TableColumnAssert tableColumnAssert = tableAssert.column();
    TableColumnAssert tableColumnAssert2 = tableColumnAssert.hasColumnName("id");
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssert2);
    TableRowValueAssert tableRowValueAssert = tableAssert.row().value();
    TableRowValueAssert tableRowValueAssert2 = tableRowValueAssert.hasColumnName("ID");
    Assertions.assertThat(tableRowValueAssert).isSameAs(tableRowValueAssert2);
  }

  /**
   * This method should fail because the column name is different.
   */
  @Test
  @NeedReload
  public void should_fail_because_column_name_is_different() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangeAssert changeAssert = assertThat(changes).change();
    try {
      changeAssert.column().hasColumnName("ID2");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 0 (column name : ID) of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                                      + "Expecting :\n"
                                                      + "  \"ID2\"\n"
                                                      + "to be the name of the column but was:\n"
                                                      + "  \"ID\"");
    }
    try {
      changeAssert.rowAtEndPoint().value().hasColumnName("ID2");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Value at index 0 (column name : ID) of Row at end point of Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source] \n"
                                                      + "Expecting :\n"
                                                      + "  \"ID2\"\n"
                                                      + "to be the name of the column but was:\n"
                                                      + "  \"ID\"");
    }

    Table table = new Table(source, "actor");
    TableAssert tableAssert = assertThat(table);
    try {
      tableAssert.column().hasColumnName("ID2");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 0 (column name : ID) of actor table] \n"
                                                      + "Expecting :\n"
                                                      + "  \"ID2\"\n"
                                                      + "to be the name of the column but was:\n"
                                                      + "  \"ID\"");
    }
    try {
      tableAssert.row().value().hasColumnName("ID2");
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Value at index 0 (column name : ID) of Row at index 0 of actor table] \n"
                                                      + "Expecting :\n"
                                                      + "  \"ID2\"\n"
                                                      + "to be the name of the column but was:\n"
                                                      + "  \"ID\"");
    }
  }
}
