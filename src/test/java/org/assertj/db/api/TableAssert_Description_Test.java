package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.description.Description;
import org.assertj.core.description.TextDescription;
import org.assertj.core.util.introspection.FieldSupport;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the description methods for assertion on {@code Table}.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableAssert_Description_Test extends AbstractTest {

  /**
   * This method tests the result of description from text and parameters.
   */
  @Test
  public void test_description_by_text() {

    Table table = new Table(source, "movie");
    TableAssert assertion = assertThat(table).as("test %s", "param");

    FieldSupport.setAllowExtractingPrivateFields(true);
    WritableAssertionInfo info = FieldSupport.instance().fieldValue("info", WritableAssertionInfo.class, assertion);

    assertThat(info.descriptionText()).isEqualTo("test param");
  }

  /**
   * This method tests the result of description from description object.
   */
  @Test
  public void test_description_by_description() {

    Table table = new Table(source, "movie");
    Description description = new TextDescription("test %s %s", "param", "param2");
    TableAssert assertion = assertThat(table).as(description);

    FieldSupport.setAllowExtractingPrivateFields(true);
    WritableAssertionInfo info = FieldSupport.instance().fieldValue("info", WritableAssertionInfo.class, assertion);

    assertThat(info.descriptionText()).isEqualTo("test param param2");
  }
}
