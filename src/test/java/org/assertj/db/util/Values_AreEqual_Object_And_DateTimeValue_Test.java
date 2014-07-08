package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

/**
 * Tests on {@code areEqual} method for {@code DateTimeValue}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_AreEqual_Object_And_DateTimeValue_Test extends AbstractTest {

  /**
   * This method tests the {@code areEqual} method for {@code TimeValue}s.
   */
  @Test
  public void test_are_equal_for_timestamps() {
    assertThat(
        Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"),
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)))).isTrue();
    assertThat(
        Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"),
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 5, 3)))).isFalse();
    assertThat(Values.areEqual("", DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();
    assertThat(Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"), (DateTimeValue) null)).isFalse();

    assertThat(
        Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"),
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 6, 2)))).isFalse();
    assertThat(
        Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"),
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1, 5, 3)))).isFalse();
    assertThat(
        Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"),
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 2, 6, 3)))).isFalse();
    assertThat(
        Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"),
            DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(10, 1, 6, 3)))).isFalse();
    assertThat(
        Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"),
            DateTimeValue.of(DateValue.of(2007, 12, 3), TimeValue.of(9, 1, 6, 3)))).isFalse();
    assertThat(
        Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"),
            DateTimeValue.of(DateValue.of(2007, 2, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();
    assertThat(
        Values.areEqual(Timestamp.valueOf("2007-12-23 09:01:06.000000003"),
            DateTimeValue.of(DateValue.of(2008, 12, 23), TimeValue.of(9, 1, 6, 3)))).isFalse();
  }

}
