package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Time;

import org.assertj.db.error.AssertJDBException;
import org.junit.Test;

/**
 * Tests on {@code areEqual} method for {@code Time}s and {@code String}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_AreEqual_Time_And_String_Test {

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_times() {
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), "09:01:06")).isTrue();
    assertThat(Values.areEqual(Time.valueOf("09:01:00"), "09:01:00")).isTrue();
    assertThat(Values.areEqual(Time.valueOf("09:01:00"), "09:01")).isTrue();

    assertThat(Values.areEqual(Time.valueOf("09:01:06"), "09:01:05")).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), "09:02:06")).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), "10:01:06")).isFalse();
    assertThat(Values.areEqual(Time.valueOf("09:01:06"), "09:01:06.000000003")).isFalse();
  }

  /**
   * This method should fail because the expected value ("***") is not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable() {
    Values.areEqual(Time.valueOf("09:01:06"), "***");
  }

  /**
   * This method should fail because the expected value is null.
   */
  @Test(expected = NullPointerException.class)
  public void should_fail_because_string_is_null_with_time() {
    Values.areEqual(Time.valueOf("09:01:06"), (String) null);
  }

}
