package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Time;

import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Tests on {@code areEqual} method for {@code TimeValue}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_AreEqual_Object_TimeValue_Test {

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_times() {
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), TimeValue.of(9, 1, 6))).isTrue();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), TimeValue.of(9, 1, 5))).isFalse();
    assertThat(Values.areEqual("", TimeValue.of(9, 1, 6))).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), (TimeValue) null)).isFalse();

    assertThat(Values.areEqual(Time.valueOf("09:01:06"), TimeValue.of(9, 1, 5))).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), TimeValue.of(9, 2, 6))).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), TimeValue.of(10, 1, 6))).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), TimeValue.of(9, 1, 6, 3))).isFalse();
  }

}
