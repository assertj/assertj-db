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

import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;

import static org.assertj.db.error.ShouldBeChangeType.shouldBeChangeType;

/**
 * Assertion methods about the {@link Change}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ChangeAssert extends AbstractAssert<ChangeAssert> {

  /**
   * The actual change on which the assertion is.
   */
  private final Change change;

  /**
   * The original assert.
   */
  private final ChangesAssert originalAssert;

  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param change The {@link Change} on which are the assertions.
   */
  ChangeAssert(ChangesAssert originalAssert, Change change) {
    super(ChangeAssert.class);
    this.originalAssert = originalAssert;
    this.change = change;
  }

  /**
   * Returns the assert on the changes.
   * 
   * @return The assert on the changes.
   */
  public ChangesAssert returnToOriginAssert() {
    return originalAssert;
  }

  /**
   * Returns assertion methods on the next change in the list of changes.
   *
   * @return An object to make assertions on the next change.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert change() throws AssertJDBException {
    return returnToOriginAssert().change();
  }

  /**
   * Returns assertion methods on the change at the {@code index} in parameter.
   *
   * @param index The index corresponding to the change.
   * @return An object to make assertions on the change.
   * @throws AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeAssert change(int index) throws AssertJDBException {
    return returnToOriginAssert().change(index);
  }

  /**
   * Verifies that the type of the change is equal to the type in parameter.
   * <p>
   * Example where the assertion verifies that the change is of type {@code CREATION} :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isOfType(ChangeType.CREATION);
   * </code>
   * </pre>
   *
   * @param expected The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert isOfType(ChangeType expected) {
    ChangeType type = change.getChangeType();
    if (type != expected) {
      throw failures.failure(info, shouldBeChangeType(expected, type));
    }
    return this;
  }

  /**
   * Verifies that the type of the change is a creation.
   * <p>
   * Example where the assertion verifies that the change is a creation :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isCreation();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert isCreation() {
    return isOfType(ChangeType.CREATION);
  }

  /**
   * Verifies that the type of the change is a modification.
   * <p>
   * Example where the assertion verifies that the change is a modification :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isModification();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert isModification() {
    return isOfType(ChangeType.MODIFICATION);
  }

  /**
   * Verifies that the type of the change is a deletion.
   * <p>
   * Example where the assertion verifies that the change is a deletion :
   * </p>
   *
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isDeletion();
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   */
  public ChangeAssert isDeletion() {
    return isOfType(ChangeType.DELETION);
  }
}
