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
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.api.assertions;

import org.assertj.db.type.ChangeType;

/**
 * Defines the assertion methods on the type of a change (creation, modification or deletion of a row).
 * <p>The different type of changes are enumerated in {@link org.assertj.db.type.ChangeType}.</p>
 *
 * @param <T> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @author Régis Pouiller
 */
public interface AssertOnChangeType<T extends AssertOnChangeType<T>> {

  /**
   * Verifies that the type of the change is equal to the type in parameter.
   * <p>
   * Example where the assertion verifies that the change is of type {@code CREATION} :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isOfType(ChangeType.CREATION);
   * </code>
   * </pre>
   *
   * @param expected The expected type to compare to.
   * @return {@code this} assertion object.
   * @throws AssertionError If the type is different to the type in parameter.
   * @see #isCreation()
   * @see #isModification()
   * @see #isDeletion()
   * @see org.assertj.db.api.ChangeAssert#isOfType(org.assertj.db.type.ChangeType)
   */
  T isOfType(ChangeType expected);

  /**
   * Verifies that the type of the change is a creation.
   * <p>
   * Example where the assertion verifies that the change is a creation :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isCreation();
   * </code>
   * </pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ChangeType.CREATION);
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the change is not a creation.
   * @see org.assertj.db.type.ChangeType#CREATION
   * @see org.assertj.db.api.ChangeAssert#isCreation()
   */
  T isCreation();

  /**
   * Verifies that the type of the change is a modification.
   * <p>
   * Example where the assertion verifies that the change is a modification :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isModification();
   * </code>
   * </pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ChangeType.MODIFICATION);
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the change is not a modification.
   * @see org.assertj.db.type.ChangeType#MODIFICATION
   * @see org.assertj.db.api.ChangeAssert#isModification()
   */
  T isModification();

  /**
   * Verifies that the type of the change is a deletion.
   * <p>
   * Example where the assertion verifies that the change is a deletion :
   * </p>
   * <pre>
   * <code class='java'>
   * assertThat(changes).change(1).isDeletion();
   * </code>
   * </pre>
   * <p>
   * This assertion method is equivalent to :
   * </p>
   * <pre>
   * <code class='java'>
   * xxxxx.isOfType(ChangeType.DELETION);
   * </code>
   * </pre>
   *
   * @return {@code this} assertion object.
   * @throws AssertionError If the type of the change is not a deletion.
   * @see org.assertj.db.type.ChangeType#DELETION
   * @see org.assertj.db.api.ChangeAssert#isDeletion()
   */
  T isDeletion();
}
