package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Test on the type got from {@code getType} method from {@code ValueType} enum.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueType_GetType_Test extends AbstractTest {

  /**
   * This method tests the result of {@code getType} method from {@code ValueType} enum.
   */
  @Test
  public void test_result_when_getting_type() {
    assertThat(ValueType.getType(new byte[]{1})).isEqualTo(ValueType.BYTES);
    assertThat(ValueType.getType(true)).isEqualTo(ValueType.BOOLEAN);
    assertThat(ValueType.getType("")).isEqualTo(ValueType.TEXT);
    assertThat(ValueType.getType(new Date(10))).isEqualTo(ValueType.DATE);
    assertThat(ValueType.getType(new Time(10))).isEqualTo(ValueType.TIME);
    assertThat(ValueType.getType(new Timestamp(10))).isEqualTo(ValueType.DATE_TIME);
    assertThat(ValueType.getType((short) 10)).isEqualTo(ValueType.NUMBER);
    assertThat(ValueType.getType((int) 10)).isEqualTo(ValueType.NUMBER);
    assertThat(ValueType.getType((long) 10)).isEqualTo(ValueType.NUMBER);
    assertThat(ValueType.getType(10.5f)).isEqualTo(ValueType.NUMBER);
    assertThat(ValueType.getType(new BigDecimal(10.5f))).isEqualTo(ValueType.NUMBER);
    assertThat(ValueType.getType(null)).isEqualTo(ValueType.NOT_IDENTIFIED);
  }
}
