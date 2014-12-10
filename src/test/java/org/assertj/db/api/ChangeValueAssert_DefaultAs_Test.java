package org.assertj.db.api;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on default description for value of {@code Row} of {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeValueAssert_DefaultAs_Test extends AbstractTest {

  /**
   * This method tests the description of value.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_value() throws NoSuchFieldException, SecurityException,
          IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeValueAssert assertion = assertThat(changes).ofDeletion().change().rowAtStartPoint().value();

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    org.assertj.core.api.Assertions.assertThat(info.descriptionText()).isEqualTo("Value at index 0 of Row at start point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source (only deletion changes)");
  }

  /**
   * This method tests the description of value with index.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_default_description_of_value_with_index() throws NoSuchFieldException, SecurityException,
          IllegalArgumentException, IllegalAccessException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeValueAssert assertion = assertThat(changes).change().rowAtEndPoint().value(2);

    Field field = AbstractAssert.class.getDeclaredField("info");
    field.setAccessible(true);
    WritableAssertionInfo info = (WritableAssertionInfo) field.get(assertion);

    org.assertj.core.api.Assertions.assertThat(info.descriptionText()).isEqualTo("Value at index 2 of Row at end point of Change at index 0 of Changes on tables of 'sa/jdbc:h2:mem:test' source");
  }

}
