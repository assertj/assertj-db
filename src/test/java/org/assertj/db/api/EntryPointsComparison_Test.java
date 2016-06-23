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
package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Sets;
import org.junit.Test;

public class EntryPointsComparison_Test {

  private static final int ACCESS_MODIFIERS = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;
  private static final Comparator<Method> IGNORING_DECLARING_CLASS_AND_METHOD_NAME = internalMethodComparator(true, false, true);

  @Test
  public void assertions_and_bdd_assertions_should_have_the_same_methods() {
    Method[] thenMethods = findMethodsWithName(BDDAssertions.class, "then");
    Method[] assertThatMethods = findMethodsWithName(Assertions.class, "assertThat");

    assertThat(thenMethods).usingElementComparator(IGNORING_DECLARING_CLASS_AND_METHOD_NAME)
                           .containsExactlyInAnyOrder(assertThatMethods);
  }

  private static Method[] findMethodsWithName(Class<?> clazz, String name, Class<?>... ignoredReturnTypes) {
    List<Method> matchingMethods = new ArrayList<>();
    Set<Class<?>> ignoredReturnTypesSet = Sets.newLinkedHashSet(ignoredReturnTypes);
    for (Method method : clazz.getMethods()) {
      if (!ignoredReturnTypesSet.contains(method.getReturnType()) && method.getName().equals(name)) {
        matchingMethods.add(method);
      }
    }
    return matchingMethods.toArray(new Method[matchingMethods.size()]);
  }

  private static Comparator<Method> internalMethodComparator(final boolean ignoreDeclaringClass,
                                                             final boolean ignoreReturnType,
                                                             final boolean ignoreMethodName) {
    return new Comparator<Method>() {
      @Override
      public int compare(Method method1, Method method2) {

        // the methods should be with the same access type
        boolean equal = (ACCESS_MODIFIERS & method1.getModifiers() & method2.getModifiers()) != 0;
        equal = equal && (ignoreDeclaringClass || method1.getDeclaringClass().equals(method2.getDeclaringClass()));
        equal = equal && (ignoreReturnType || method1.getReturnType().equals(method2.getReturnType()));
        equal = equal && (ignoreMethodName || method1.getName().equals(method2.getName()));

        Class<?>[] pTypes1 = method1.getParameterTypes();
        Class<?>[] pTypes2 = method2.getParameterTypes();
        equal = equal && pTypes1.length == pTypes2.length;
        if (equal) {
          for (int i = 0; i < pTypes1.length; i++) {
            equal = equal && pTypes1[i].equals(pTypes2[i]);
          }
        }
        return equal ? 0 : 1;
      }
    };
  }
}
