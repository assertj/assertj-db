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
package org.assertj.db.database.h2;

import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Table;
import org.junit.Test;

import java.util.Locale;

import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on the H2 database.
 *
 * @author Régis Pouiller
 *
 */
public class H2Database_Test extends AbstractH2Test {

  @Test
  @NeedReload
  public void test() {
    Table table = new Table(source, "test");

    assertThat(table).row().value("var35").isEqualTo(Locale.FRENCH);
  }
}
