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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test the output to File.
 *
 * @author Régis Pouiller
 * @author Julien Roy
 */
public class OutputterToFile_Test extends AbstractTest {

  /**
   * This method tests the {@code output} output method to file.
   */
  @Test
  public void test_output_to_file() throws Exception {
    Table table = new Table(jdbcConnectionProvider, "actor");

    Outputs.output(table).row().value().toFile("target" + File.separator + "test.txt");
    try (InputStream inputStream = new FileInputStream(new File("target" + File.separator + "test.txt"))) {
      byte[] bytes = new byte[2048];
      int nb = inputStream.read(bytes);
      Assertions.assertThat(new String(bytes, 0, nb))
        .isEqualTo(String.format("[Value at index 0 (column name : ID) of Row at index 0 of ACTOR table]%n"
          + "|----------|%n"
          + "| ID       |%n"
          + "| (NUMBER) |%n"
          + "|----------|%n"
          + "| 1        |%n"
          + "|----------|%n"));
    }
  }
}
