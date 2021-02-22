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
package org.assertj.db.api.assertions;

import static org.assertj.db.api.BDDAssertions.then;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.api.RequestAssert;
import org.assertj.db.api.TableAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on {@code BDDAssertions} methods.
 *
 * @author Régis Pouiller
 *
 */
public class BDDAssertions_Test extends AbstractTest {

  /**
   * This method tests the {@code then} method for {@code Table}.
   */
  @Test
  public void test_then_for_table() throws Exception {
    Table table = new Table(source, "test");
    Assertions.assertThat(then(table)).isInstanceOf(TableAssert.class);
  }

  /**
   * This method tests the {@code then} method for {@code Request}.
   */
  @Test
  public void test_then_for_request() throws Exception {
    Request request = new Request(source, "select * from test");
    Assertions.assertThat(then(request)).isInstanceOf(RequestAssert.class);
  }

  /**
   * This method tests the {@code then} method for {@code Changes}.
   */
  @Test
  public void test_then_for_changes() throws Exception {
    Changes changes = new Changes(source);
    Assertions.assertThat(then(changes)).isInstanceOf(ChangesAssert.class);
  }
}
