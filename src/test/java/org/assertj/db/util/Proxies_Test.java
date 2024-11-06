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

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import net.bytebuddy.ByteBuddy;

/**
 * Test for Proxies utility class.
 *
 * @author Julien Roy
 */
public class Proxies_Test {

  /**
   * Test the {@code isProxified} method with not enhanced class.
   */
  @Test
  public void test_is_proxy_with_classic_class() {
    assertThat(Proxies.isProxified(String.class)).isFalse();
  }

  /**
   * Test the {@code isProxified} method with enhanced class.
   */
  @Test
  public void test_is_proxy_with_enhanced_class() {
    Class enhancedClass = new ByteBuddy()
      .subclass(ClassForProxiesTest.class)
      .make()
      .load(ClassForProxiesTest.class.getClassLoader())
      .getLoaded();
    assertThat(Proxies.isProxified(enhancedClass)).isTrue();
  }

  /**
   * Test the {@code unProxy} method with not enhanced class.
   */
  @Test
  public void test_unproxy_with_classic_class() {
    assertThat(Proxies.unProxy(String.class)).isEqualTo(String.class);
  }

  /**
   * Test the {@code unProxy} method with enhanced class.
   */
  @Test
  public void test_unproxy_with_enhanced_class() {
    Class enhancedClass = new ByteBuddy()
      .subclass(ClassForProxiesTest.class)
      .make()
      .load(ClassForProxiesTest.class.getClassLoader())
      .getLoaded();
    assertThat(Proxies.unProxy(enhancedClass)).isEqualTo(ClassForProxiesTest.class);
  }

  public static class ClassForProxiesTest {
  }

}
