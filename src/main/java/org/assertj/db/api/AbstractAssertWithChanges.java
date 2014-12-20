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

/**
 * Abstract class that represents a assert with an origin assert.
 *
 * @param <E> The "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *            target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *            for more details.
 * @param <O> The class of the assert of origin
 * @author Régis Pouiller
 */
public abstract class AbstractAssertWithChanges<E extends AbstractAssertWithChanges<E, O>, O extends OriginAssertWithChanges>
        extends AbstractAssertWithOriginAssert<E, O> implements OriginAssertWithChanges {

  /**
   * Constructor.
   *
   * @param selfType     Class of this assert : a sub-class of {@code AbstractAssertWithChanges}.
   * @param originAssert The assert of origin.
   */
  AbstractAssertWithChanges(Class<E> selfType, O originAssert) {
    super(selfType, originAssert);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert ofCreation() {
    return originAssert.ofCreation();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert ofModification() {
    return originAssert.ofModification();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert ofDeletion() {
    return originAssert.ofDeletion();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert ofCreationOnTable(String tableName) {
    return originAssert.ofCreationOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert ofModificationOnTable(String tableName) {
    return originAssert.ofModificationOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert ofDeletionOnTable(String tableName) {
    return originAssert.ofDeletionOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangesAssert onTable(String tableName) {
    return originAssert.onTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert change() {
    return originAssert.change();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert change(int index) {
    return originAssert.change(index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfCreation() {
    return originAssert.changeOfCreation();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfCreation(int index) {
    return originAssert.changeOfCreation(index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfModification() {
    return originAssert.changeOfModification();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfModification(int index) {
    return originAssert.changeOfModification(index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfDeletion() {
    return originAssert.changeOfDeletion();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfDeletion(int index) {
    return originAssert.changeOfDeletion(index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOnTable(String tableName) {
    return originAssert.changeOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOnTable(String tableName, int index) {
    return originAssert.changeOnTable(tableName, index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfCreationOnTable(String tableName) {
    return originAssert.changeOfCreationOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfCreationOnTable(String tableName, int index) {
    return originAssert.changeOfCreationOnTable(tableName, index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfModificationOnTable(String tableName) {
    return originAssert.changeOfModificationOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfModificationOnTable(String tableName, int index) {
    return originAssert.changeOfModificationOnTable(tableName, index);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfDeletionOnTable(String tableName) {
    return originAssert.changeOfDeletionOnTable(tableName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ChangeAssert changeOfDeletionOnTable(String tableName, int index) {
    return originAssert.changeOfDeletionOnTable(tableName, index);
  }
}
