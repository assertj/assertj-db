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
package org.assertj.db.type;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table.Order.OrderType;
import org.junit.Test;

/**
 * Test on the type got from {@code valueOf} method from {@code OrderType} enum.
 * 
 * @author Régis Pouiller
 * 
 */
public class OrderType_ValueOf_Test extends AbstractTest {

	  /**
	   * This method tests the result of {@code valueOf} method from {@code OrderType} enum.
	   */
	  @Test
	  public void test_result_of_valueOf() {
	    assertThat(OrderType.valueOf("ASC")).isEqualTo(OrderType.ASC);
	    assertThat(OrderType.valueOf("DESC")).isEqualTo(OrderType.DESC);
	  }
}
