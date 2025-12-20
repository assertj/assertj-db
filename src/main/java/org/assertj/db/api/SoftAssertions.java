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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.api;

import java.util.List;

import org.assertj.core.api.SoftAssertionError;
import org.assertj.core.groups.Properties;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;

/**
 * Implementation of AssertJ SoftAssertions for {@link Table}, {@link Request} and {@link Changes}.
 * <p>
 * This implementation works like AssertJ SoftAssertions implementation by providing you with
 * proxies of the AssertJ-DB assertion objects.
 * <p>
 * For more details see AssertJ implementation : {@link org.assertj.core.api.SoftAssertions}
 *
 * @author Julien Roy
 */
public final class SoftAssertions extends AbstractSoftAssertions {

  /**
   * Create a new SoftAssertions class that allow chain many assertion and detect all assertion failure ( not only the first one ).
   */
  public SoftAssertions() {
    super();
  }

  /**
   * Creates a new instance of {@link TableAssert}.
   *
   * @param table The table to assert on.
   * @return The created assertion object.
   */
  public TableAssert assertThat(Table table) {
    return proxy(TableAssert.class, Table.class, table);
  }

  /**
   * Creates a new instance of {@link RequestAssert}.
   *
   * @param request The request to assert on.
   * @return The created assertion object.
   */
  public RequestAssert assertThat(Request request) {
    return proxy(RequestAssert.class, Request.class, request);
  }

  /**
   * Creates a new instance of {@link ChangesAssert}.
   *
   * @param changes The changes to assert on.
   * @return The created assertion object.
   */
  public ChangesAssert assertThat(Changes changes) {
    return proxy(ChangesAssert.class, Changes.class, changes);
  }

  /**
   * Assert that all assertions succeed.
   *
   * @throws SoftAssertionError If any assertion failed.
   */
  public void assertAll() {
    List<Throwable> errors = this.errorsCollected();
    if (!errors.isEmpty()) {
      throw new SoftAssertionError(Properties.extractProperty("message", String.class).from(errors));
    }
  }
}
