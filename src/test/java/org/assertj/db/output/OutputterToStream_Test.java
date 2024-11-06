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
package org.assertj.db.output;

import java.io.ByteArrayOutputStream;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test the output to Stream.
 *
 * @author Régis Pouiller
 */
public class OutputterToStream_Test extends AbstractTest {

  /**
   * This method tests the {@code output} output method to stream.
   */
  @Test
  public void test_output_to_stream() throws Exception {
    Table table = new Table(source, "actor");

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    Outputs.output(table).row().value().toStream(byteArrayOutputStream0);
    Assertions.assertThat(byteArrayOutputStream0.toString()).isEqualTo(String.format("[Value at index 0 (column name : ID) of Row at index 0 of ACTOR table]%n"
      + "|----------|%n"
      + "| ID       |%n"
      + "| (NUMBER) |%n"
      + "|----------|%n"
      + "| 1        |%n"
      + "|----------|%n"));
  }
}
