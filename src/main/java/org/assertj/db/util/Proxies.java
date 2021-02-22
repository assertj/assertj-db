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
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.util;

/**
 * Utilities for manage proxies.
 *
 * @author Julien Roy
 */
public class Proxies {

  public static final String BYTE_BUDDY_PATTERN = "$ByteBuddy$";

  /**
   * Check if class is proxified.
   *
   * @param clazz Class to check
   * @return True if class is proxified by CGLIB
   */
  public static boolean isProxified(Class clazz) {
    return clazz.getName().contains(BYTE_BUDDY_PATTERN);
  }

  /**
   * Return base of proxified class if it is proxified otherwise return class.
   *
   * @param clazz Clazz to evaluate
   * @return Class based of proxified
   */
  public static Class unProxy(Class clazz) {
    if (isProxified(clazz)) {
      return clazz.getSuperclass();
    }
    return clazz;
  }

}
