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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;

/**
 * @author Otoniel Isidoro (otoniel.isidoro@sofist.com.br)
 */
public class TextTypeTest extends AbstractTest {

  @Test
  @NeedReload
  public void testName() throws Exception {
    Table test3Table = new Table(dataSource, "test3");
    assertThat(test3Table).hasNumberOfRows(1).row().column().hasValues("TESTE");
    assertThat(test3Table).hasNumberOfRows(1).row().column().value().isEqualTo("TESTE");
  }
}
