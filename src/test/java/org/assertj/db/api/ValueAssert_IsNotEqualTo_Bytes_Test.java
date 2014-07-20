package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Tests on the methods which verifies if a value is not equal to a array of bytes.
 * 
 * @author Régis Pouiller
 * 
 */
public class ValueAssert_IsNotEqualTo_Bytes_Test extends AbstractTest {

  private byte[] bytesTest = bytesContentFromClassPathOf("test.txt");
  private byte[] bytesDev = bytesContentFromClassPathOf("logo-dev.jpg");
  private byte[] bytesH2 = bytesContentFromClassPathOf("h2-logo-2.png");

  /**
   * This method tests that the value is not equal to a array of bytes.
   */
  @Test
  public void test_if_value_is_not_equal_to_bytes() {
    Table table = new Table(source, "test");
    assertThat(table).column("var11")
        .value().isNotEqualTo(bytesDev).returnToColumn()
        .value().isNotEqualTo(bytesH2);
  }

  /**
   * This method should fail because the value is equal to the array of bytes.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_equal() {
    Table table = new Table(source, "test");
    assertThat(table).column("var11")
        .value().isNotEqualTo(bytesH2);
  }

  /**
   * This method should fail because the value is not a array of bytes.
   */
  @Test(expected = AssertionError.class)
  public void should_fail_because_value_is_not_a_bytes() {
    Table table = new Table(source, "test");
    assertThat(table).column("var1")
        .value().as("var1").isNotEqualTo(bytesTest);
  }

}
