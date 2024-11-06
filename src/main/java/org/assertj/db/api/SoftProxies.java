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
package org.assertj.db.api;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.matcher.ElementMatcher;

import java.lang.reflect.Constructor;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * Proxy implementation utilities.
 *
 * @author Julien Roy
 */
class SoftProxies {

  private static final ByteBuddy BYTE_BUDDY = new ByteBuddy()
      .with(new AuxiliaryType.NamingStrategy.SuffixingRandom("AssertJDb$SoftProxies"))
      .with(TypeValidation.DISABLED);

  private static final ElementMatcher.Junction<MethodDescription> METHODS_TO_EXTRACT_PROXY = nameContains("change")
      .or(nameContains("column"))
      .or(nameContains("row"))
      .or(nameContains("value"))
      .or(nameStartsWith("changeOf"))
      .or(nameStartsWith("of"))
      .or(nameStartsWith("rowAt"));

  private static final ElementMatcher.Junction<MethodDescription> METHODS_NOT_TO_PROXY = METHODS_TO_EXTRACT_PROXY
      .or(named("equals"))
      .or(named("hashCode"))
      .or(named("clone"))
      .or(named("as"))
      .or(named("toString"))
      .or(named("describedAs"))
      .or(named("descriptionText"))
      .or(named("getWritableAssertionInfo"))
      .or(named("inBinary"))
      .or(named("inHexadecimal"))
      .or(named("newAbstractIterableAssert"))
      .or(named("newObjectArrayAssert"))
      .or(named("removeCustomAssertRelatedElementsFromStackTraceIfNeeded"))
      .or(named("overridingErrorMessage"))
      .or(named("usingComparator"))
      .or(named("usingDefaultComparator"))
      .or(named("usingElementComparator"))
      .or(named("withComparatorsForElementPropertyOrFieldNames"))
      .or(named("withComparatorsForElementPropertyOrFieldTypes"))
      .or(named("withIterables"))
      .or(named("withFailMessage"))
      .or(named("withAssertionInfo"))
      .or(named("withAssertionState"))
      .or(named("withRepresentation"))
      .or(named("withTypeComparators"))
      .or(named("withThreadDumpOnError"))
      .or(named("succeedsWithin"))
      .or(named("isEmpty"));

  private final ErrorCollector collector = new ErrorCollector();

  SoftProxies() {
  }

  List<Throwable> errorsCollected() {
    return this.collector.errors();
  }

  <V, T> V create(Class<V> assertClass, Class<T> actualClass, T actual) {
    try {
      Class<? extends V> proxyClass = createProxyClass(assertClass);
      Constructor<? extends V> constructor = proxyClass.getConstructor(actualClass);
      return constructor.newInstance(actual);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  <V> V create(Class<V> assertClass, Class[] paramClass, Object[] params) {
    try {
      Class<? extends V> proxyClass = createProxyClass(assertClass);
      Constructor<? extends V> constructor = proxyClass.getConstructor(paramClass);
      return constructor.newInstance(params);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public boolean wasSuccess() {
    return this.collector.wasSuccess();
  }

  @SuppressWarnings("unchecked")
  private <V> Class<V> createProxyClass(Class<V> assertClass) {
    return (Class<V>) BYTE_BUDDY
        .subclass(assertClass)
        .method(any().and(not(METHODS_NOT_TO_PROXY)))
        .intercept(MethodDelegation.to(collector))
        .method(METHODS_TO_EXTRACT_PROXY)
        .intercept(MethodDelegation.to(new ProxifyPositionResult(this)))
        .make()
        .load(assertClass.getClassLoader())
        .getLoaded();
  }
}
