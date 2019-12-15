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

import org.assertj.core.api.ObjectArrayAssert;
import org.assertj.core.internal.cglib.proxy.MethodInterceptor;
import org.assertj.core.internal.cglib.proxy.MethodProxy;
import org.assertj.core.util.Arrays;
import org.assertj.db.type.Change;
import org.assertj.db.type.Column;
import org.assertj.db.type.Row;
import org.assertj.db.type.Value;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.assertj.db.util.Proxies.isProxified;
import static org.assertj.db.util.Proxies.unProxy;

/**
 * Method interceptor that proxify result of assertions methods.
 * Useful for navigation assertion methods like ( column(...) , row(...) ).
 *
 * @author Julien Roy
 */
class ProxifyPositionResult implements MethodInterceptor {
  private final SoftProxies proxies;

  ProxifyPositionResult(SoftProxies proxies) {
    this.proxies = proxies;
  }

  public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
    Object result = proxy.invokeSuper(obj, args);
    if (isProxified(result.getClass()) || actual(result) == null) {
      return result;
    }
    return this.proxies.create(result.getClass(), actualClass(result), actual(result));
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
    } else if (result instanceof ObjectArrayAssert) {
      return Arrays.array(Array.newInstance(Object.class, 0).getClass());
    } else {
      Type actualType = ((ParameterizedType) result.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
      Class type = actualType instanceof ParameterizedType ?
          (Class) ((ParameterizedType) actualType).getRawType() :
          (Class) actualType;
      return Arrays.array(type);
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
      return null;
    }
  }
}
