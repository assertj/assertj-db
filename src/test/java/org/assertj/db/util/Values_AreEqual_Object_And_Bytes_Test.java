/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;

/**
 * Tests on {@code areEqual} method for arrays of {@code byte}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_AreEqual_Object_And_Bytes_Test extends AbstractTest {

  /**
   * This method tests the {@code areEqual} method for arrays of {@code byte}s.
   */
  @Test
  public void test_are_equal_for_bytes() throws Exception {
    byte[] bytes = bytesContentFromClassPathOf("test.txt");
    byte[] goodBytes = new byte[] { 'T', 'e', 'x', 't', ' ', 'f', 'o', 'r', ' ', 't', 'e', 's', 't', 's' };
    byte[] badBytes = new byte[] { 'T', 'e', 'x', 't', ' ', 'f', 'o', 'r', ' ', ' ', 'e', 's', 't', 's' };
    assertThat(Values.areEqual(getValue(null, bytes), goodBytes)).isTrue();
    assertThat(Values.areEqual(getValue(null, bytes), badBytes)).isFalse();
    assertThat(Values.areEqual(getValue(null, ""), goodBytes)).isFalse();
    assertThat(Values.areEqual(getValue(null, null), (byte[]) null)).isTrue();
    assertThat(Values.areEqual(getValue(null, bytes), (byte[]) null)).isFalse();
  }
}
