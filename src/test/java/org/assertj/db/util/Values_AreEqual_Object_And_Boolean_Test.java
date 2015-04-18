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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@code areEqual} method for {@code Boolean}s.
 * 
 * @author Régis Pouiller
 * 
 */
public class Values_AreEqual_Object_And_Boolean_Test {

  /**
   * This method tests the {@code areEqual} method for {@code Boolean}s.
   */
  @Test
  public void test_are_equal_for_booleans() {
    assertThat(Values.areEqual(true, true)).isTrue();
    assertThat(Values.areEqual(true, false)).isFalse();
    assertThat(Values.areEqual(false, true)).isFalse();
    assertThat(Values.areEqual(false, false)).isTrue();
    assertThat(Values.areEqual(null, (Boolean) null)).isTrue();
    assertThat(Values.areEqual(false, (Boolean) null)).isFalse();
  }
}
