package org.assertj.db.api;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.description.Description;
import org.assertj.core.description.TextDescription;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on {@code Descriptable} interface methods.
 *
 * @author Régis Pouiller
 *
 */
public class Descriptable_Test extends AbstractTest {

  /**
   * This method tests the {@code as} method to modify the description.
   */
  @Test
  public void test_as() throws Exception {
    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    Table table = new Table(source, "actor");

    TableRowAssert assertion = assertThat(table).row();
    WritableAssertionInfo info1 = (WritableAssertionInfo) field.get(assertion);
    assertThat(info1.descriptionText()).isEqualTo("Row at index 0 of actor table");

    TableRowAssert assertion2 = assertion.as("message %s with text", 1);
    WritableAssertionInfo info2 = (WritableAssertionInfo) field.get(assertion2);
    assertThat(info2.descriptionText()).isEqualTo("message 1 with text");

    Description description = new TextDescription("description %s %s", "with", "information");
    TableRowAssert assertion3 = assertion.as(description);
    WritableAssertionInfo info3 = (WritableAssertionInfo) field.get(assertion3);
    assertThat(info3.descriptionText()).isEqualTo("description with information");
  }
}
