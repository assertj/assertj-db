/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests on {@code areEqual} method for {@code UUID}s.
 *
 * @author Otoniel Isidoro
 */
public class Values_AreEqual_Value_And_UUID_Test extends AbstractTest {

  /**
   * This method tests the {@code areEqual} method for {@code UUID}s.
   */
  @Test
  public void test_are_equal_for_UUID() throws Exception {
    assertThat(Values.areEqual(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
                               UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))).isTrue();
    assertThat(Values.areEqual(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")),
                               UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"))).isFalse();
    assertThat(Values.areEqual(getValue(null, null), (UUID) null)).isTrue();
    assertThat(Values.areEqual(getValue(null, UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435")), (UUID) null)).isFalse();
    assertThat(Values.areEqual(getValue(null, null), UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))).isFalse();
  }

}
