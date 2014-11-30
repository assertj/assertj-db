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
package org.assertj.db.api;

import static org.assertj.db.api.Assertions.assertThat;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

/**
 * Test on {@code ofCreation()}, {@code ofModification()}, {@code ofDeletion()}, {@code onTable()},
 * {@code ofCreationOnTable()}, {@code ofModificationOnTable()} and {@code ofDeletionOnTable()} methods.
 * 
 * @author Régis Pouiller
 * 
 */
public class ChangesAssert_Of_Test extends AbstractTest {

  /**
   * This method test the assertion on the size of {@code Changes} on source.
   */
  @Test
  @NeedReload
  public void test_of_methods() {
    Changes changes = new Changes(source);

    changes.setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    assertThat(changes).hasSize(8).ofCreation().hasSize(3).ofModification().hasSize(3).ofDeletion().hasSize(2)
        .onTable("movie").hasSize(2).onTable("actor").hasSize(3).ofCreationOnTable("movie").hasSize(1)
        .ofModificationOnTable("movie").hasSize(1).ofDeletionOnTable("movie").hasSize(0).ofCreation().hasSize(3);
  }

}
