package org.assertj.db.type;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test on the type got from {@code valueOf} method from {@code DataType} enum.
 *
 * @author Régis Pouiller
 *
 */
public class DataType_ValueOf_Test {

  /**
   * This method tests the result of {@code valueOf} method from {@code DataType} enum.
   */
  @Test
  public void test_result_of_valueOf() {
    assertThat(DataType.valueOf("REQUEST")).isEqualTo(DataType.REQUEST);
    assertThat(DataType.valueOf("TABLE")).isEqualTo(DataType.TABLE);
  }
}
