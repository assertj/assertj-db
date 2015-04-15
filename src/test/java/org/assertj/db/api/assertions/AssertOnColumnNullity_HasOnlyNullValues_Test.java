package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertOnColumnNullity} class :
 * {@link org.assertj.db.api.assertions.AssertOnColumnNullity#hasOnlyNullValues()} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnColumnNullity_HasOnlyNullValues_Test extends AbstractTest {

  /**
   * This method tests the {@code hasOnlyNullValues} assertion method.
   */
  @Test
  public void test_has_only_null_values() {
    Table table = new Table(source, "test2");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var15");
    TableColumnAssert tableColumnAssert2 = tableColumnAssert.hasOnlyNullValues();
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssert2);
  }

  /**
   * This method should fail because the column has a not null value.
   */
  @Test
  public void should_fail_because_column_has_not_null_value() throws Exception {
    Table table = new Table(source, "test2");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var14");
    try {
      tableColumnAssert.hasOnlyNullValues();
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 13 (column name : VAR14) of test2 table] \n"
                                                      + "Expecting to contain only null:\n"
                                                      + "but contains not null at index: 0");
    }
  }
}
