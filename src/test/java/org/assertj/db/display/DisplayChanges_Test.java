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
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.db.display.Displaying.display;

/**
 * Test the display of changes.
 *
 * @author Régis Pouiller
 */
public class DisplayChanges_Test extends AbstractTest {

  /**
   * This method tests the {@code display} display method.
   */
  @Test
  @NeedReload
  public void test_display() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    display(changes).inStream(byteArrayOutputStream);
    Assertions.assertThat(byteArrayOutputStream.toString()).isEqualTo(String.format("[Changes on tables of 'sa/jdbc:h2:mem:test' source]%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         |                | *         |           |           |            |                                      |%n"
                                                                                    + "|           | TYPE         | TABLE          | PRIMARY |                | ID        | NAME      | FIRSTNAME | BIRTH      | ACTOR_IMDB                           |%n"
                                                                                    + "|           |              |                | KEY     |                | (NUMBER)  | (TEXT)    | (TEXT)    | (DATE)     | (UUID)                               |%n"
                                                                                    + "|           |              |                |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3  | Index : 4                            |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         | At start point |           |           |           |            |                                      |%n"
                                                                                    + "| Index : 0 | CREATION     | ACTOR          | 4       |----------------|-----------|-----------|-----------|------------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         | At end point   | 4         | Murray    | Bill      | 1950-09-21 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------|--------------------------------------|%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------------|%n"
                                                                                    + "|           |              |                |         |                | *         |           |           |                  |%n"
                                                                                    + "|           | TYPE         | TABLE          | PRIMARY |                | ID        | ID_MOVIE  | ID_ACTOR  | CHARACTER        |%n"
                                                                                    + "|           |              |                | KEY     |                | (NUMBER)  | (NUMBER)  | (NUMBER)  | (TEXT)           |%n"
                                                                                    + "|           |              |                |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3        |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------------|%n"
                                                                                    + "|           |              |                |         | At start point |           |           |           |                  |%n"
                                                                                    + "| Index : 1 | CREATION     | INTERPRETATION | 6       |----------------|-----------|-----------|-----------|------------------|%n"
                                                                                    + "|           |              |                |         | At end point   | 6         | 4         | 4         | Dr Peter Venkman |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------------|%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|--------------|-----------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         |                | *         |              |           |                                      |%n"
                                                                                    + "|           | TYPE         | TABLE          | PRIMARY |                | ID        | TITLE        | YEAR      | MOVIE_IMDB                           |%n"
                                                                                    + "|           |              |                | KEY     |                | (NUMBER)  | (TEXT)       | (NUMBER)  | (UUID)                               |%n"
                                                                                    + "|           |              |                |         |                | Index : 0 | Index : 1    | Index : 2 | Index : 3                            |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|--------------|-----------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         | At start point |           |              |           |                                      |%n"
                                                                                    + "| Index : 2 | CREATION     | MOVIE          | 4       |----------------|-----------|--------------|-----------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         | At end point   | 4         | Ghostbusters | 1984      | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|--------------|-----------|--------------------------------------|%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------------|------------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         |                | *         |           |                 |            |                                      |%n"
                                                                                    + "|           | TYPE         | TABLE          | PRIMARY |                | ID        | NAME      | FIRSTNAME       | BIRTH      | ACTOR_IMDB                           |%n"
                                                                                    + "|           |              |                | KEY     |                | (NUMBER)  | (TEXT)    | (TEXT)          | (DATE)     | (UUID)                               |%n"
                                                                                    + "|           |              |                |         |                | Index : 0 | Index : 1 | Index : 2       | Index : 3  | Index : 4                            |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------------|------------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         | At start point | 1         | Weaver    | Sigourney       | 1949-10-08 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                                                                                    + "| Index : 3 | MODIFICATION | ACTOR          | 1       |----------------|-----------|-----------|-----------------|------------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         | At end point   | 1         | Weaver    | Susan Alexandra | 1949-10-08 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------------|------------|--------------------------------------|%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------------------|%n"
                                                                                    + "|           |              |                |         |                | *         |           |           |                        |%n"
                                                                                    + "|           | TYPE         | TABLE          | PRIMARY |                | ID        | ID_MOVIE  | ID_ACTOR  | CHARACTER              |%n"
                                                                                    + "|           |              |                | KEY     |                | (NUMBER)  | (NUMBER)  | (NUMBER)  | (TEXT)                 |%n"
                                                                                    + "|           |              |                |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3              |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------------------|%n"
                                                                                    + "|           |              |                |         | At start point | 3         | 3         | 1         | Dr Grace Augustine     |%n"
                                                                                    + "| Index : 4 | MODIFICATION | INTERPRETATION | 3       |----------------|-----------|-----------|-----------|------------------------|%n"
                                                                                    + "|           |              |                |         | At end point   | 3         | 3         | 1         | Doctor Grace Augustine |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------------------|%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|------------|-----------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         |                | *         |            |           |                                      |%n"
                                                                                    + "|           | TYPE         | TABLE          | PRIMARY |                | ID        | TITLE      | YEAR      | MOVIE_IMDB                           |%n"
                                                                                    + "|           |              |                | KEY     |                | (NUMBER)  | (TEXT)     | (NUMBER)  | (UUID)                               |%n"
                                                                                    + "|           |              |                |         |                | Index : 0 | Index : 1  | Index : 2 | Index : 3                            |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|------------|-----------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         | At start point | 3         | Avatar     | 2009      | d735221b-5de5-4112-aa1e-49090cb75ada |%n"
                                                                                    + "| Index : 5 | MODIFICATION | MOVIE          | 3       |----------------|-----------|------------|-----------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         | At end point   | 3         | The Avatar | 2009      | d735221b-5de5-4112-aa1e-49090cb75ada |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|------------|-----------|--------------------------------------|%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-------------|-----------|------------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         |                | *         |             |           |            |                                      |%n"
                                                                                    + "|           | TYPE         | TABLE          | PRIMARY |                | ID        | NAME        | FIRSTNAME | BIRTH      | ACTOR_IMDB                           |%n"
                                                                                    + "|           |              |                | KEY     |                | (NUMBER)  | (TEXT)      | (TEXT)    | (DATE)     | (UUID)                               |%n"
                                                                                    + "|           |              |                |         |                | Index : 0 | Index : 1   | Index : 2 | Index : 3  | Index : 4                            |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-------------|-----------|------------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         | At start point | 3         | Worthington | Sam       | 1976-08-02 | d735221b-5de5-4112-aa1e-49090cb75ada |%n"
                                                                                    + "| Index : 6 | DELETION     | ACTOR          | 3       |----------------|-----------|-------------|-----------|------------|--------------------------------------|%n"
                                                                                    + "|           |              |                |         | At end point   |           |             |           |            |                                      |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-------------|-----------|------------|--------------------------------------|%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------|%n"
                                                                                    + "|           |              |                |         |                | *         |           |           |            |%n"
                                                                                    + "|           | TYPE         | TABLE          | PRIMARY |                | ID        | ID_MOVIE  | ID_ACTOR  | CHARACTER  |%n"
                                                                                    + "|           |              |                | KEY     |                | (NUMBER)  | (NUMBER)  | (NUMBER)  | (TEXT)     |%n"
                                                                                    + "|           |              |                |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3  |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------|%n"
                                                                                    + "|           |              |                |         | At start point | 5         | 3         | 3         | Jake Sully |%n"
                                                                                    + "| Index : 7 | DELETION     | INTERPRETATION | 5       |----------------|-----------|-----------|-----------|------------|%n"
                                                                                    + "|           |              |                |         | At end point   |           |           |           |            |%n"
                                                                                    + "|-----------|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------|%n"));
  }
}
