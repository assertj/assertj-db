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

import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;

import static org.assertj.db.error.ShouldBeChangeType.shouldBeChangeType;

/**
 * Assertion methods about the {@link Change}.
 * 
 * @author Régis Pouiller
 * 
 */
public class ChangeAssert extends AbstractAssertWithChanges<ChangeAssert, ChangesAssert> {

  /**
   * The actual change on which the assertion is.
   */
  private final Change change;

  /**
   * Constructor.
   * 
   * @param originalAssert The original assert.
   * @param change The {@link Change} on which are the assertions.
   */
  ChangeAssert(ChangesAssert originalAssert, Change change) {
    super(ChangeAssert.class, originalAssert);
    this.change = change;
  }

  /**
   * Returns the assert on the row at start point.
   *
   * @return The assert on the row at start point.
   */
  public ChangeRowAssert rowAtStartPoint() {
    StringBuilder stringBuilder = new StringBuilder("Row at start point of ");
    stringBuilder.append(info.descriptionText());
    return new ChangeRowAssert(this, change.getRowAtStartPoint()).as(stringBuilder.toString());
  }

  /**
   * Returns the assert on the row at end point.
   *
   * @return The assert on the row at end point.
   */
  public ChangeRowAssert rowAtEndPoint() {
    StringBuilder stringBuilder = new StringBuilder("Row at end point of ");
    stringBuilder.append(info.descriptionText());
    return new ChangeRowAssert(this, change.getRowAtEndPoint()).as(stringBuilder.toString());
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
