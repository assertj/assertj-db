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
package org.assertj.db.output;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.db.output.Outputs.output;

/**
 * Test the output of changes.
 *
 * @author Régis Pouiller
 */
public class OutputterChange_Test extends AbstractTest {

  /**
   * This method tests the {@code output} output method.
   */
  @Test
  @NeedReload
  public void test_display() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream5 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream6 = new ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream7 = new ByteArrayOutputStream();
    output(changes).change().inStream(byteArrayOutputStream0)
                    .change().inStream(byteArrayOutputStream1)
                    .change().inStream(byteArrayOutputStream2)
                    .change().inStream(byteArrayOutputStream3)
                    .change().inStream(byteArrayOutputStream4)
                    .change().inStream(byteArrayOutputStream5)
                    .change().inStream(byteArrayOutputStream6)
                    .change().inStream(byteArrayOutputStream7);
    Assertions.assertThat(byteArrayOutputStream0.toString()).isEqualTo(String.format("[Change at index 0 (on table : ACTOR and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source]%n"
                                                                                    + "|----------|-------|---------|----------------|-----------|-----------|-----------|------------|--------------------------------------|%n"
                                                                                    + "|          |       |         |                | *         |           |           |            |                                      |%n"
                                                                                    + "| TYPE     | TABLE | PRIMARY |                | ID        | NAME      | FIRSTNAME | BIRTH      | ACTOR_IMDB                           |%n"
                                                                                    + "|          |       | KEY     |                | (NUMBER)  | (TEXT)    | (TEXT)    | (DATE)     | (UUID)                               |%n"
                                                                                    + "|          |       |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3  | Index : 4                            |%n"
                                                                                    + "|----------|-------|---------|----------------|-----------|-----------|-----------|------------|--------------------------------------|%n"
                                                                                    + "|          |       |         | At start point |           |           |           |            |                                      |%n"
                                                                                    + "| CREATION | ACTOR | 4       |----------------|-----------|-----------|-----------|------------|--------------------------------------|%n"
                                                                                    + "|          |       |         | At end point   | 4         | Murray    | Bill      | 1950-09-21 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                                                                                    + "|----------|-------|---------|----------------|-----------|-----------|-----------|------------|--------------------------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream1.toString()).isEqualTo(String.format("[Change at index 1 (on table : INTERPRETATION and with primary key : [6]) of Changes on tables of 'sa/jdbc:h2:mem:test' source]%n"
                                                                                     + "|----------|----------------|---------|----------------|-----------|-----------|-----------|------------------|%n"
                                                                                     + "|          |                |         |                | *         |           |           |                  |%n"
                                                                                     + "| TYPE     | TABLE          | PRIMARY |                | ID        | ID_MOVIE  | ID_ACTOR  | CHARACTER        |%n"
                                                                                     + "|          |                | KEY     |                | (NUMBER)  | (NUMBER)  | (NUMBER)  | (TEXT)           |%n"
                                                                                     + "|          |                |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3        |%n"
                                                                                     + "|----------|----------------|---------|----------------|-----------|-----------|-----------|------------------|%n"
                                                                                     + "|          |                |         | At start point |           |           |           |                  |%n"
                                                                                     + "| CREATION | INTERPRETATION | 6       |----------------|-----------|-----------|-----------|------------------|%n"
                                                                                     + "|          |                |         | At end point   | 6         | 4         | 4         | Dr Peter Venkman |%n"
                                                                                     + "|----------|----------------|---------|----------------|-----------|-----------|-----------|------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream2.toString()).isEqualTo(String.format("[Change at index 2 (on table : MOVIE and with primary key : [4]) of Changes on tables of 'sa/jdbc:h2:mem:test' source]%n"
                                                                                     + "|----------|-------|---------|----------------|-----------|--------------|-----------|--------------------------------------|%n"
                                                                                     + "|          |       |         |                | *         |              |           |                                      |%n"
                                                                                     + "| TYPE     | TABLE | PRIMARY |                | ID        | TITLE        | YEAR      | MOVIE_IMDB                           |%n"
                                                                                     + "|          |       | KEY     |                | (NUMBER)  | (TEXT)       | (NUMBER)  | (UUID)                               |%n"
                                                                                     + "|          |       |         |                | Index : 0 | Index : 1    | Index : 2 | Index : 3                            |%n"
                                                                                     + "|----------|-------|---------|----------------|-----------|--------------|-----------|--------------------------------------|%n"
                                                                                     + "|          |       |         | At start point |           |              |           |                                      |%n"
                                                                                     + "| CREATION | MOVIE | 4       |----------------|-----------|--------------|-----------|--------------------------------------|%n"
                                                                                     + "|          |       |         | At end point   | 4         | Ghostbusters | 1984      | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                                                                                     + "|----------|-------|---------|----------------|-----------|--------------|-----------|--------------------------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream3.toString()).isEqualTo(String.format("[Change at index 3 (on table : ACTOR and with primary key : [1]) of Changes on tables of 'sa/jdbc:h2:mem:test' source]%n"
                                                                                     + "|--------------|-------|---------|----------------|-----------|-----------|-----------------|------------|--------------------------------------|%n"
                                                                                     + "|              |       |         |                | *         |           |                 |            |                                      |%n"
                                                                                     + "| TYPE         | TABLE | PRIMARY |                | ID        | NAME      | FIRSTNAME       | BIRTH      | ACTOR_IMDB                           |%n"
                                                                                     + "|              |       | KEY     |                | (NUMBER)  | (TEXT)    | (TEXT)          | (DATE)     | (UUID)                               |%n"
                                                                                     + "|              |       |         |                | Index : 0 | Index : 1 | Index : 2       | Index : 3  | Index : 4                            |%n"
                                                                                     + "|--------------|-------|---------|----------------|-----------|-----------|-----------------|------------|--------------------------------------|%n"
                                                                                     + "|              |       |         | At start point | 1         | Weaver    | Sigourney       | 1949-10-08 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                                                                                     + "| MODIFICATION | ACTOR | 1       |----------------|-----------|-----------|-----------------|------------|--------------------------------------|%n"
                                                                                     + "|              |       |         | At end point   | 1         | Weaver    | Susan Alexandra | 1949-10-08 | 30b443ae-c0c9-4790-9bec-ce1380808435 |%n"
                                                                                     + "|--------------|-------|---------|----------------|-----------|-----------|-----------------|------------|--------------------------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream4.toString()).isEqualTo(String.format("[Change at index 4 (on table : INTERPRETATION and with primary key : [3]) of Changes on tables of 'sa/jdbc:h2:mem:test' source]%n"
                                                                                     + "|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------------------|%n"
                                                                                     + "|              |                |         |                | *         |           |           |                        |%n"
                                                                                     + "| TYPE         | TABLE          | PRIMARY |                | ID        | ID_MOVIE  | ID_ACTOR  | CHARACTER              |%n"
                                                                                     + "|              |                | KEY     |                | (NUMBER)  | (NUMBER)  | (NUMBER)  | (TEXT)                 |%n"
                                                                                     + "|              |                |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3              |%n"
                                                                                     + "|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------------------|%n"
                                                                                     + "|              |                |         | At start point | 3         | 3         | 1         | Dr Grace Augustine     |%n"
                                                                                     + "| MODIFICATION | INTERPRETATION | 3       |----------------|-----------|-----------|-----------|------------------------|%n"
                                                                                     + "|              |                |         | At end point   | 3         | 3         | 1         | Doctor Grace Augustine |%n"
                                                                                     + "|--------------|----------------|---------|----------------|-----------|-----------|-----------|------------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream5.toString()).isEqualTo(String.format("[Change at index 5 (on table : MOVIE and with primary key : [3]) of Changes on tables of 'sa/jdbc:h2:mem:test' source]%n"
                                                                                     + "|--------------|-------|---------|----------------|-----------|------------|-----------|--------------------------------------|%n"
                                                                                     + "|              |       |         |                | *         |            |           |                                      |%n"
                                                                                     + "| TYPE         | TABLE | PRIMARY |                | ID        | TITLE      | YEAR      | MOVIE_IMDB                           |%n"
                                                                                     + "|              |       | KEY     |                | (NUMBER)  | (TEXT)     | (NUMBER)  | (UUID)                               |%n"
                                                                                     + "|              |       |         |                | Index : 0 | Index : 1  | Index : 2 | Index : 3                            |%n"
                                                                                     + "|--------------|-------|---------|----------------|-----------|------------|-----------|--------------------------------------|%n"
                                                                                     + "|              |       |         | At start point | 3         | Avatar     | 2009      | d735221b-5de5-4112-aa1e-49090cb75ada |%n"
                                                                                     + "| MODIFICATION | MOVIE | 3       |----------------|-----------|------------|-----------|--------------------------------------|%n"
                                                                                     + "|              |       |         | At end point   | 3         | The Avatar | 2009      | d735221b-5de5-4112-aa1e-49090cb75ada |%n"
                                                                                     + "|--------------|-------|---------|----------------|-----------|------------|-----------|--------------------------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream6.toString()).isEqualTo(String.format("[Change at index 6 (on table : ACTOR and with primary key : [3]) of Changes on tables of 'sa/jdbc:h2:mem:test' source]%n"
                                                                                     + "|----------|-------|---------|----------------|-----------|-------------|-----------|------------|--------------------------------------|%n"
                                                                                     + "|          |       |         |                | *         |             |           |            |                                      |%n"
                                                                                     + "| TYPE     | TABLE | PRIMARY |                | ID        | NAME        | FIRSTNAME | BIRTH      | ACTOR_IMDB                           |%n"
                                                                                     + "|          |       | KEY     |                | (NUMBER)  | (TEXT)      | (TEXT)    | (DATE)     | (UUID)                               |%n"
                                                                                     + "|          |       |         |                | Index : 0 | Index : 1   | Index : 2 | Index : 3  | Index : 4                            |%n"
                                                                                     + "|----------|-------|---------|----------------|-----------|-------------|-----------|------------|--------------------------------------|%n"
                                                                                     + "|          |       |         | At start point | 3         | Worthington | Sam       | 1976-08-02 | d735221b-5de5-4112-aa1e-49090cb75ada |%n"
                                                                                     + "| DELETION | ACTOR | 3       |----------------|-----------|-------------|-----------|------------|--------------------------------------|%n"
                                                                                     + "|          |       |         | At end point   |           |             |           |            |                                      |%n"
                                                                                     + "|----------|-------|---------|----------------|-----------|-------------|-----------|------------|--------------------------------------|%n"));
    Assertions.assertThat(byteArrayOutputStream7.toString()).isEqualTo(String.format("[Change at index 7 (on table : INTERPRETATION and with primary key : [5]) of Changes on tables of 'sa/jdbc:h2:mem:test' source]%n"
                                                                                     + "|----------|----------------|---------|----------------|-----------|-----------|-----------|------------|%n"
                                                                                     + "|          |                |         |                | *         |           |           |            |%n"
                                                                                     + "| TYPE     | TABLE          | PRIMARY |                | ID        | ID_MOVIE  | ID_ACTOR  | CHARACTER  |%n"
                                                                                     + "|          |                | KEY     |                | (NUMBER)  | (NUMBER)  | (NUMBER)  | (TEXT)     |%n"
                                                                                     + "|          |                |         |                | Index : 0 | Index : 1 | Index : 2 | Index : 3  |%n"
                                                                                     + "|----------|----------------|---------|----------------|-----------|-----------|-----------|------------|%n"
                                                                                     + "|          |                |         | At start point | 5         | 3         | 3         | Jake Sully |%n"
                                                                                     + "| DELETION | INTERPRETATION | 5       |----------------|-----------|-----------|-----------|------------|%n"
                                                                                     + "|          |                |         | At end point   |           |           |           |            |%n"
                                                                                     + "|----------|----------------|---------|----------------|-----------|-----------|-----------|------------|%n"));
  }
}
