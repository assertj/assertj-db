package org.assertj.db.util;

import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on {@code areEqual} method for arrays of {@code byte}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_AreEqual_Bytes_Test extends AbstractTest {

  /**
   * This method tests the {@code areEqual} method for arrays of {@code byte}s.
   */
  @Test
  public void test_are_equal_for_bytes() {
    byte[] bytes = bytesContentFromClassPathOf("test.txt");
    byte[] goodBytes = new byte[] { 'T', 'e', 'x', 't', ' ', 'f', 'o', 'r', ' ', 't', 'e', 's', 't', 's' };
    byte[] badBytes = new byte[] { 'T', 'e', 'x', 't', ' ', 'f', 'o', 'r', ' ', ' ', 'e', 's', 't', 's' };
    assertThat(Values.areEqual(bytes, goodBytes)).isTrue();
    assertThat(Values.areEqual(bytes, badBytes)).isFalse();
    assertThat(Values.areEqual("", goodBytes)).isFalse();
  }
}
