package org.assertj.db.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests on {@code areEqual} method for {@code String}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_AreEqual_Object_And_String_Test {

  /**
   * This method tests the {@code areEqual} method for {@code String}s.
   */
  @Test
  public void test_are_equal_for_string() {
    assertThat(Values.areEqual("text", "text")).isTrue();
    assertThat(Values.areEqual("Text", "text")).isFalse();
  }

}
