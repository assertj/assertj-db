package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

import java.lang.reflect.Field;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.core.description.Description;
import org.assertj.core.description.TextDescription;
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
   * 
   * @throws SecurityException
   * @throws NoSuchFieldException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   */
  @Test
  public void test_description_by_text() throws SecurityException, NoSuchFieldException, IllegalArgumentException,
      IllegalAccessException {

    Request request = new Request(source, "select * from movie");

    RequestAssert assertion = assertThat(request).as("test %s", "param");

    Field field = AbstractDbAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("test param");
  }

  /**
   * This method tests the result of description from description object.
   * 
   * @throws SecurityException
   * @throws NoSuchFieldException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   */
  @Test
  public void test_description_by_description() throws SecurityException, NoSuchFieldException, IllegalArgumentException,
      IllegalAccessException {

    Request request = new Request(source, "select * from movie");

    Description description = new TextDescription("test %s %s", "param", "param2");
    RequestAssert assertion = assertThat(request).as(description);

    Field field = AbstractDbAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    assertThat(info.descriptionText()).isEqualTo("test param param2");
  }
}
