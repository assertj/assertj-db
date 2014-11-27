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

import static org.assertj.db.error.ShouldHaveChangesSize.shouldHaveChangesSize;

import java.util.List;

import org.assertj.db.type.Change;
import org.assertj.db.type.Changes;

/**
 * Assertion methods about the {@link Changes}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ChangesAssert extends AbstractAssert<ChangesAssert> {

  /**
   * The actual changes on which the assertion is.
   */
  private final Changes changes;

  /**
   * Constructor.
   * 
   * @param changes The {@link Changes} on which are the assertions.
   */
  ChangesAssert(Changes changes) {
    super(ChangesAssert.class);
    this.changes = changes;
  }

  /**
   * Verifies that the size (number) of {@link Changes} is equal to the number in parameter.
   * <p>
   * Example where the assertion verifies that there are 8 changes :
   * </p>
   * 
   * <pre><code class='java'>
   * assertThat(changes).hasSize(8);
   * </code></pre>
   * 
   * @param expected The number to compare to the size.
   * @return {@code this} assertion object.
   * @throws AssertionError If the size is different to the number in parameter.
   */
  public ChangesAssert hasSize(int expected) {
    List<Change> changesList = changes.getChangesList();
    int size = changesList.size();
    if (size != expected) {
      throw failures.failure(info, shouldHaveChangesSize(size, expected));
    }
    return myself;
  }

}
