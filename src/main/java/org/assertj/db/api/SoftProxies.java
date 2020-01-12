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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.api;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import org.assertj.core.util.Arrays;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Proxy implementation utilities.
 *
 * @author Julien Roy
 */
class SoftProxies {
  private final ErrorCollector collector = new ErrorCollector();

  SoftProxies() {
  }

  List<Throwable> errorsCollected() {
    return this.collector.errors();
  }

  @SuppressWarnings("unchecked")
  <V, T> V create(Class<V> assertClass, Class<T> actualClass, T actual) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(assertClass);
    enhancer.setCallbackFilter(SoftProxies.CollectErrorsOrCreateExtractedProxy.FILTER);
    enhancer.setCallbacks(new Callback[] { this.collector, new ProxifyPositionResult(this) });
    return (V) enhancer.create((Class[]) Arrays.array(new Class[] { actualClass }), Arrays.array(new Object[] { actual }));
  }

  @SuppressWarnings("unchecked")
  <V> V create(Class<V> assertClass, Class[] paramClass, Object[] params) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(assertClass);
    enhancer.setCallbackFilter(SoftProxies.CollectErrorsOrCreateExtractedProxy.FILTER);
    enhancer.setCallbacks(new Callback[] { this.collector, new ProxifyPositionResult(this) });
    return (V) enhancer.create(paramClass, params);
  }

  public boolean wasSuccess() {
    return this.collector.wasSuccess();
  }

  private enum CollectErrorsOrCreateExtractedProxy implements CallbackFilter {
    FILTER;

    public int accept(Method method) {
      return this.isPositionMethod(method) ? 1 : 0;
    }

    private boolean isPositionMethod(Method method) {
      String methodName = method.getName();
      return
          java.util.Arrays.asList("change", "column", "row", "value").contains(methodName) ||
          methodName.startsWith("changeOf") ||
          methodName.startsWith("rowAt") ||
          methodName.startsWith("of");
    }
  }
}
