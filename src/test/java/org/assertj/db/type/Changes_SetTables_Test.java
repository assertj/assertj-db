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
package org.assertj.db.type;

import org.assertj.db.common.AbstractTest;
import org.junit.Test;

/**
 * Tests on the setTables method.
 * 
 * @author Régis Pouiller
 * 
 */
public class Changes_SetTables_Test extends AbstractTest {

  /**
   * This method test when setting with empty parameters.
   */
  @Test
  public void test_when_setting_tables_with_empty_parameters() {
    Changes changes = new Changes(source);
    changes.setTables();
  }
}
