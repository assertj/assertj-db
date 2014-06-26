package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.description.Description;
import org.assertj.core.description.TextDescription;
import org.assertj.core.util.introspection.FieldSupport;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.junit.Test;

/**
 * Test on the description methods for assertion on {@code Request}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RequestAssert_Description_Test extends AbstractTest {

  /**
   * This method tests the result of description from text and parameters.
   */
  @Test
  public void test_description_by_text() {

    Request request = new Request(source, "select * from movie");
    RequestAssert assertion = assertThat(request).as("test %s", "param");

    FieldSupport.setAllowExtractingPrivateFields(true);
    WritableAssertionInfo info = FieldSupport.instance().fieldValue("info", WritableAssertionInfo.class, assertion);

    assertThat(info.descriptionText()).isEqualTo("test param");
  }

  /**
   * This method tests the result of description from description object.
   */
  @Test
  public void test_description_by_description() {

    Request request = new Request(source, "select * from movie");
    Description description = new TextDescription("test %s %s", "param", "param2");
    RequestAssert assertion = assertThat(request).as(description);

    FieldSupport.setAllowExtractingPrivateFields(true);
    WritableAssertionInfo info = FieldSupport.instance().fieldValue("info", WritableAssertionInfo.class, assertion);

    assertThat(info.descriptionText()).isEqualTo("test param param2");
  }
}
