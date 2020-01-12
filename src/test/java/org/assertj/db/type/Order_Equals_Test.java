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
package org.assertj.db.type;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table.Order;
import org.junit.Test;

/**
 * Test on the type got from {@code equals} method from {@code Order} enum.
 * 
 * @author Régis Pouiller
 * 
 */
public class Order_Equals_Test extends AbstractTest {

	  /**
	   * This method tests the result of {@code equals} method from {@code Order} enum.
	   */
	  @Test
	  public void test_result_of_equals() {
	    assertTrue(Order.asc("test").equals(Order.asc("test")));
	    assertFalse(Order.asc("test").equals(Order.asc("testt")));
	    assertFalse(Order.asc("test").equals(Order.desc("test")));
	    assertTrue(Order.asc(null).equals(Order.asc(null)));
	    assertFalse(Order.asc("test").equals(Order.asc(null)));
	    assertFalse(Order.asc(null).equals(Order.asc("test")));
	    assertFalse(Order.asc("test").equals("test"));
	  }
}
