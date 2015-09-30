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
package org.assertj.db.display;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.type.Request;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.db.display.Displaying.display;

/**
 * Test the display of requests.
 *
 * @author Régis Pouiller
 */
public class DisplayRequest_Test extends AbstractTest {

  /**
   * This method tests the {@code display} display method.
   */
  @Test
  public void test_display() throws Exception {
    Request request = new Request(source, "select * from actor");

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    display(request).display(new PrintStream(byteArrayOutputStream));
    Assertions.assertThat(byteArrayOutputStream.toString()).isEqualTo(String.format("['select * from actor' request]%n"
                                                                                    + "|-----------|---------|-----------|-------------|-----------|------------|--------------------------------------|%n"
                                                                                    + "|           |         |           |             |           |            |                                      |%n"
                                                                                    + "|           | PRIMARY | ID        | NAME        | FIRSTNAME | BIRTH      | ACTOR_IMDB                           |%n"
                                                                                    + "|           | KEY     | (NUMBER)  | (TEXT)      | (TEXT)    | (DATE)     | (UUID)                               |%n"
                                                                                    + "|           |         | Index : 0 | Index : 1   | Index : 2 | Index : 3  | Index : 4                            |%n"
                                                                                    + "|-----------|---------|-----------|-------------|-----------|------------|--------------------------------------|%n"
                                                                                    + "| Index : 0 |         | 1         | Weaver      | Sigourney | 1949-10-08 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                                                                                    + "| Index : 1 |         | 2         | Phoenix     | Joaquim   | 1974-10-28 | 16319617-ae95-4087-9264-d3d21bf611b6 |%n"
                                                                                    + "| Index : 2 |         | 3         | Worthington | Sam       | 1976-08-02 | d735221b-5de5-4112-aa1e-49090cb75ada |%n"
                                                                                    + "|-----------|---------|-----------|-------------|-----------|------------|--------------------------------------|%n"));
  }
}
