package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on {@code areEqual} method for {@code Boolean}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_AreEqual_Boolean_Test extends AbstractTest {

  /**
   * This method tests the {@code areEqual} method for {@code Boolean}s.
   */
  @Test
  public void test_are_equal_for_booleans() {
    assertThat(Values.areEqual(true, true)).isTrue();
    assertThat(Values.areEqual(true, false)).isFalse();
    assertThat(Values.areEqual(false, true)).isFalse();
    assertThat(Values.areEqual(false, false)).isTrue();
  }
}
