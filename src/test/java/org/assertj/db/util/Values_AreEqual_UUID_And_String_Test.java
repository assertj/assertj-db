package org.assertj.db.util;

import org.assertj.db.exception.AssertJDBException;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@code areEqual} method for {@code UUID}s and {@code String}s.
 *
 * @author Otoniel Isidoro (otoniel.isidoro@sofist.com.br)
 */
public class Values_AreEqual_UUID_And_String_Test {

  /**
   * This method tests the {@code areEqual} method for {@code UUID}s.
   */
  @Test
  public void test_are_equal_for_UUIDs() {
    assertThat(Values.areEqual(UUID.fromString("88838129-291E-40A9-A94C-A15BE36CF7C3"),
                               "88838129-291E-40A9-A94C-A15BE36CF7C3")).isTrue();
  }

  /**
   * This method should fail because the expected value ("***") is not parsable to do the comparison.
   */
  @Test(expected = AssertJDBException.class)
  public void should_fail_because_string_is_not_parseable() {
    Values.areEqual(UUID.fromString("88838129-291E-40A9-A94C-A15BE36CF7C3"), "***");
  }

}
