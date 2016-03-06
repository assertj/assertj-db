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

import org.assertj.db.navigation.ToChange;
import org.assertj.db.navigation.ToChanges;
import org.assertj.db.navigation.origin.OriginWithChanges;

/**
 * Base class for all displays with an {@link org.assertj.db.navigation.origin.Origin}
 * and have {@link org.assertj.db.type.Changes}.
 *
 * @param <E> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/1IZIRcY"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @param <O> The type of the assertion class of {@link org.assertj.db.navigation.origin.Origin}.
 * @author Régis Pouiller
 */
public abstract class AbstractDisplayWithOriginWithChanges<E extends AbstractDisplayWithOriginWithChanges<E, O>, O extends OriginWithChanges<ChangesDisplay, ChangeDisplay>>
        extends AbstractDisplayWithOrigin<E, O>
        implements ToChanges<ChangesDisplay>,
        ToChange<ChangeDisplay> {

  /**
   * Constructor.
   *
   * @param selfType Type of this display class : a sub-class of {@code AbstractDisplayWithOriginWithChanges}.
   * @param origin The display of {@link org.assertj.db.navigation.origin.Origin}.
   */
  AbstractDisplayWithOriginWithChanges(Class<E> selfType, O origin) {
    super(selfType, origin);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesDisplay ofAll() {
    return origin.ofAll();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesDisplay ofCreation() {
    return origin.ofCreation();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesDisplay ofModification() {
    return origin.ofModification();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesDisplay ofDeletion() {
    return origin.ofDeletion();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesDisplay ofCreationOnTable(String tableName) {
    return origin.ofCreationOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesDisplay ofModificationOnTable(String tableName) {
    return origin.ofModificationOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesDisplay ofDeletionOnTable(String tableName) {
    return origin.ofDeletionOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesDisplay onTable(String tableName) {
    return origin.onTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay change() {
    return origin.change();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay change(int index) {
    return origin.change(index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOfCreation() {
    return origin.changeOfCreation();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOfCreation(int index) {
    return origin.changeOfCreation(index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOfModification() {
    return origin.changeOfModification();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOfModification(int index) {
    return origin.changeOfModification(index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOfDeletion() {
    return origin.changeOfDeletion();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOfDeletion(int index) {
    return origin.changeOfDeletion(index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOnTable(String tableName) {
    return origin.changeOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOnTable(String tableName, int index) {
    return origin.changeOnTable(tableName, index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOnTableWithPks(String tableName, Object... pksValues) {
    return origin.changeOnTableWithPks(tableName, pksValues);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOfCreationOnTable(String tableName) {
    return origin.changeOfCreationOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOfCreationOnTable(String tableName, int index) {
    return origin.changeOfCreationOnTable(tableName, index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOfModificationOnTable(String tableName) {
    return origin.changeOfModificationOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOfModificationOnTable(String tableName, int index) {
    return origin.changeOfModificationOnTable(tableName, index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOfDeletionOnTable(String tableName) {
    return origin.changeOfDeletionOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeDisplay changeOfDeletionOnTable(String tableName, int index) {
    return origin.changeOfDeletionOnTable(tableName, index);
  }
}
