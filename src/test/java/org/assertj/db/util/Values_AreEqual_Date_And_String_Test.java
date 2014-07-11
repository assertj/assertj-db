package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;

import org.assertj.db.error.AssertJDBException;
import org.junit.Test;

public class Values_AreEqual_Date_And_String_Test {

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_dates() {
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-23")).isTrue();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-23T00:00")).isTrue();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-23T00:00:00")).isTrue();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-23T00:00:00.000000000")).isTrue();

    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-24")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-01-23")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2008-12-23")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-23T00:01")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-23T01:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-24T00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-01-23T00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2008-12-23T00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-23T00:00:01")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-23T00:01:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-23T01:00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-24T00:00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-01-23T00:00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2008-12-23T00:00:00")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-23T00:00:00.000000001")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-23T00:00:01.000000000")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-23T00:01:00.000000000")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-23T01:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-12-24T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2007-01-23T00:00:00.000000000")).isFalse();
    assertThat(Values.areEqual(Date.valueOf("2007-12-23"), "2008-12-23T00:00:00.000000000")).isFalse();
  }

  /**
   * This method should fail because the expected value ("***") is not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable() {
    Values.areEqual(Date.valueOf("2007-12-23"), "***");
  }

  /**
   * This method should fail because the expected value is null.
   */
  @Test(expected = NullPointerException.class)
  public void should_fail_because_string_is_null_with_date() {
    Values.areEqual(Date.valueOf("2007-12-23"), (String) null);
  }

}
