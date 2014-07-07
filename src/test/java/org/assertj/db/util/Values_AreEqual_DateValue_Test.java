package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateValue;
import org.junit.Test;

/**
 * Tests on {@code areEqual} method for {@code DateValue}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_AreEqual_DateValue_Test extends AbstractTest {

  /**
   * This method tests the {@code areEqual} method for {@code DateValue}s.
   */
  @Test
  public void test_are_equal_for_dates() {
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), DateValue.of(2007, 12, 23))).isTrue();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), DateValue.of(2007, 1, 2))).isFalse();
    assertThat(Values.areEqual("", DateValue.of(2007, 12, 23))).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), (DateValue) null)).isFalse();

    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), DateValue.of(2007, 12, 2))).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), DateValue.of(2007, 1, 23))).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), DateValue.of(2006, 12, 23))).isFalse();
  }

}
