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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.util;

import org.assertj.core.internal.cglib.proxy.Enhancer;
import org.assertj.core.internal.cglib.proxy.NoOp;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
    Object enhancedClass = Enhancer.create(ClassForProxiesTest.class, NoOp.INSTANCE);
    assertThat(Proxies.isProxified(enhancedClass.getClass())).isTrue();
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
    Object enhancedClass = Enhancer.create(ClassForProxiesTest.class, NoOp.INSTANCE);
    assertThat(Proxies.unProxy(enhancedClass.getClass())).isEqualTo(ClassForProxiesTest.class);
  }

  public static class ClassForProxiesTest {
  }

}
