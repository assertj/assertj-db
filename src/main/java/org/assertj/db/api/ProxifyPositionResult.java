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

import static org.assertj.db.util.Proxies.isProxied;
import static org.assertj.db.util.Proxies.unProxy;

import java.util.concurrent.Callable;

import org.assertj.core.util.Arrays;
import org.assertj.db.type.Change;
import org.assertj.db.type.Column;
import org.assertj.db.type.Row;
import org.assertj.db.type.Value;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

/**
 * Method interceptor that proxify result of assertions methods.
 * Useful for navigation assertion methods like ( column(...) , row(...) ).
 *
 * @author Julien Roy
 */
public class ProxifyPositionResult {
  private final SoftProxies proxies;

  ProxifyPositionResult(SoftProxies proxies) {
    this.proxies = proxies;
  }

  private static Class[] actualClass(Object result) {

    if (result instanceof AbstractColumnAssert) {
      return Arrays.array(
        unProxy(((AbstractColumnAssert) result).origin.getClass()),
        Column.class
      );
    } else if (result instanceof AbstractRowAssert) {
      return Arrays.array(
        unProxy(((AbstractRowAssert) result).origin.getClass()),
        Row.class
      );
    } else if (result instanceof AbstractValueAssert) {
      return Arrays.array(
        unProxy(((AbstractValueAssert) result).origin.getClass()),
        Value.class
      );
    } else if (result instanceof ChangeAssert) {
      return Arrays.array(
        unProxy(((ChangeAssert) result).origin.getClass()),
        Change.class
      );
    } else if (result instanceof ChangeColumnAssert) {
      return Arrays.array(
        unProxy(((ChangeColumnAssert) result).origin.getClass()),
        String.class,
        Value.class,
        Value.class
      );
    } else {
      return Arrays.array();
    }
  }

  private static Object[] actual(Object result) {
    if (result instanceof AbstractColumnAssert) {
      return Arrays.array(
        ((AbstractColumnAssert) result).origin,
        ((AbstractColumnAssert) result).column
      );
    } else if (result instanceof AbstractRowAssert) {
      return Arrays.array(
        ((AbstractRowAssert) result).origin,
        ((AbstractRowAssert) result).row
      );
    } else if (result instanceof AbstractValueAssert) {
      return Arrays.array(
        ((AbstractValueAssert) result).origin,
        ((AbstractValueAssert) result).value
      );
    } else if (result instanceof ChangeAssert) {
      return Arrays.array(
        ((ChangeAssert) result).origin,
        ((ChangeAssert) result).change
      );
    } else if (result instanceof ChangeColumnAssert) {
      return Arrays.array(
        ((ChangeColumnAssert) result).origin,
        ((ChangeColumnAssert) result).columnName,
        ((ChangeColumnAssert) result).valueAtStartPoint,
        ((ChangeColumnAssert) result).valueAtEndPoint
      );
    } else {
      return Arrays.array();
    }
  }

  /**
   * Method called during interception of positional method.
   *
   * @param proxy Proxy method to use
   * @return the object result proxied
   * @throws Exception When method call fail
   */
  @RuntimeType
  public Object intercept(@SuperCall Callable<?> proxy) throws Exception {
    Object result = proxy.call();

    if (isProxied(result.getClass()) || Arrays.isNullOrEmpty(actual(result))) {
      return result;
    }
    return this.proxies.create(result.getClass(), actualClass(result), actual(result));
  }
}
