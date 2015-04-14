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
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api.assertions;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.TableColumnAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;
import static org.junit.Assert.fail;

/**
 * Tests on {@link AssertOnColumnEquality} class :
 * {@link org.assertj.db.api.assertions.AssertOnColumnEquality#hasValuesEqualTo(byte[]...)} method.
 *
 * @author Régis Pouiller
 *
 */
public class AssertOnColumnEquality_HasValuesEqualTo_Bytes_Test extends AbstractTest {

  /**
   * This method tests the {@code hasValuesEqualTo} assertion method.
   */
  @Test
  public void test_has_values_equal_to() throws Exception {
    byte[] bytesH2 = bytesContentFromClassPathOf("h2-logo-2.png");
    byte[] bytesDev = bytesContentFromClassPathOf("logo-dev.jpg");

    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var11");
    TableColumnAssert tableColumnAssertReturn = tableColumnAssert.hasValuesEqualTo(bytesH2, bytesDev, bytesDev, bytesDev);
    Assertions.assertThat(tableColumnAssert).isSameAs(tableColumnAssertReturn);

    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var11");
    TableColumnAssert tableColumnAssertReturn2 = tableColumnAssert2.hasValuesEqualTo(bytesH2, null);
    Assertions.assertThat(tableColumnAssert2).isSameAs(tableColumnAssertReturn2);
  }

  /**
   * This method should fail because the values are different.
   */
  @Test
  public void should_fail_because_values_are_different() throws Exception {
    byte[] bytesH2 = bytesContentFromClassPathOf("h2-logo-2.png");

    Table table = new Table(source, "test");
    TableColumnAssert tableColumnAssert = assertThat(table).column("var11");
    try {
      tableColumnAssert.hasValuesEqualTo(bytesH2, bytesH2, bytesH2, bytesH2);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 10 (column name : VAR11) of test table] \n"
                                                      + "Expecting that the value at index 1 to be equal to the expected value but was not equal");
    }

    Table table2 = new Table(source, "test2");
    TableColumnAssert tableColumnAssert2 = assertThat(table2).column("var11");
    try {
      tableColumnAssert2.hasValuesEqualTo(bytesH2, bytesH2);
      fail("An exception must be raised");
    } catch (AssertionError e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("[Column at index 10 (column name : VAR11) of test2 table] \n"
                                                      + "Expecting that the value at index 1 to be equal to the expected value but was not equal");
    }
  }
}
