package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;

import org.assertj.db.error.AssertJDBException;
import org.junit.Test;

public class Values_AreEqual_DateTime_And_String_Test {

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_datestimes() {
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), "2007-12-23T09:01:06.000000003")).isTrue();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), "2007-12-23T00:00:00.000000000")).isTrue();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), "2007-12-23T00:00:00")).isTrue();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), "2007-12-23T00:00")).isTrue();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), "2007-12-23")).isTrue();

    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), "2007-12-23T09:01:06.000000004")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), "2007-12-23T09:01:07.000000003")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), "2007-12-23T09:02:06.000000003")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), "2007-12-23T10:01:06.000000003")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), "2007-12-24T09:01:06.000000003")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), "2007-01-23T09:01:06.000000003")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), "2008-12-23T09:01:06.000000003")).isFalse();

    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), "2007-12-23T00:00:00.000000001")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), "2007-12-23T00:00:01.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), "2007-12-23T00:01:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), "2007-12-23T01:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), "2007-12-24T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), "2007-01-23T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00.000000000"), "2008-12-23T00:00:00.000000000")).isFalse();

    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), "2007-12-23T00:00:00.000000001")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), "2007-12-23T00:00:01.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), "2007-12-23T00:01:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), "2007-12-23T01:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), "2007-12-24T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), "2007-01-23T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 00:00:00"), "2008-12-23T00:00:00.000000000")).isFalse();
  }

  /**
   * This method should fail because the expected value ("***") is not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable() {
    Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), "***");
  }

  /**
   * This method should fail because the expected value is null.
   */
  @Test
  public void should_fail_because_string_is_null_with_datetime() {
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), (String) null)).isFalse();
  }

}
