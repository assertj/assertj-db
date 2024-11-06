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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.api;

import org.assertj.db.api.assertions.*;
import org.assertj.db.api.assertions.impl.*;
import org.assertj.db.navigation.PositionWithColumnsChange;
import org.assertj.db.navigation.PositionWithPoints;
import org.assertj.db.navigation.element.ChangeElement;
import org.assertj.db.navigation.origin.OriginWithColumnsAndRowsFromChange;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.DataType;
import org.assertj.db.type.Row;
import org.assertj.db.util.Changes;

import static org.assertj.db.util.Descriptions.*;

/**
 * Assertion methods for a {@link Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeAssert
        extends AbstractAssertWithOriginWithChanges<ChangeAssert, ChangesAssert>
        implements ChangeElement,
                   OriginWithColumnsAndRowsFromChange<ChangesAssert, ChangeAssert, ChangeColumnAssert, ChangeRowAssert>,
                   AssertOnDataType<ChangeAssert>,
                   AssertOnPrimaryKey<ChangeAssert>,
                   AssertOnChangeType<ChangeAssert>,
                   AssertOnModifiedColumns<ChangeAssert>,
                   AssertOnNumberOfColumns<ChangeAssert> {

  /**
   * The actual change on which the assertion is.
   */
  protected final Change change;

  /**
   * Position of navigation to row.
   */
  private final PositionWithPoints<ChangeAssert, ChangeRowAssert, Row> rowPosition;

  /**
   * Position of navigation to column.
   */
  private final PositionWithColumnsChange<ChangeAssert, ChangeColumnAssert> columnPosition;

  /**
   * Constructor.
   *
   * @param origin The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param change The {@link Change} on which are the assertions.
   */
  public ChangeAssert(ChangesAssert origin, Change change) {
    super(ChangeAssert.class, origin);
    this.change = change;
    rowPosition = new PositionWithPoints<ChangeAssert, ChangeRowAssert, Row>(this, ChangeRowAssert.class, Row.class, change.getRowAtStartPoint(), change.getRowAtEndPoint()) {

      @Override protected String getDescriptionAtStartPoint() {
        return getRowAtStartPointDescription(info);
      }

      @Override protected String getDescriptionAtEndPoint() {
        return getRowAtEndPointDescription(info);
      }
    };
    columnPosition = new PositionWithColumnsChange<ChangeAssert, ChangeColumnAssert>(this, ChangeColumnAssert.class){

      @Override protected String getDescription(int index, String columnName) {
        return getColumnDescription(info, index, columnName);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowAssert rowAtStartPoint() {
    return rowPosition.getInstanceAtStartPoint();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowAssert rowAtEndPoint() {
    return rowPosition.getInstanceAtEndPoint();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert column() {
    return columnPosition.getChangeColumnInstance(change);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert column(int index) {
    return columnPosition.getChangeColumnInstance(change, index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert column(String columnName) {
    return columnPosition.getChangeColumnInstance(change, columnName, change.getColumnLetterCase());
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert columnAmongTheModifiedOnes() {
    return columnPosition.getModifiedChangeColumnInstance(change);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert columnAmongTheModifiedOnes(int index) {
    return columnPosition.getModifiedChangeColumnInstance(change, index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnAssert columnAmongTheModifiedOnes(String columnName) {
    return columnPosition.getModifiedChangeColumnInstance(change, columnName, change.getColumnLetterCase());
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isOnDataType(DataType expected) {
    return AssertionsOnDataType.isOnDataType(myself, info, change, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isOnTable() {
    return AssertionsOnDataType.isOnTable(myself, info, change);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isOnRequest() {
    return AssertionsOnDataType.isOnRequest(myself, info, change);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isOnTable(String name) {
    return AssertionsOnDataType.isOnTable(myself, info, change, change.getTableLetterCase(), name);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasPksNames(String... names) {
    return AssertionsOnPrimaryKey.hasPksNames(myself, info, change, change.getPrimaryKeyLetterCase(), names);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasPksValues(Object... values) {
    return AssertionsOnPrimaryKey.hasPksValues(myself, info, change, values);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isOfType(ChangeType expected) {
    return AssertionsOnChangeType.isOfType(myself, info, change, expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isCreation() {
    return AssertionsOnChangeType.isCreation(myself, info, change);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isModification() {
    return AssertionsOnChangeType.isModification(myself, info, change);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert isDeletion() {
    return AssertionsOnChangeType.isDeletion(myself, info, change);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasNumberOfModifiedColumns(int number) {
    return AssertionsOnModifiedColumns.hasNumberOfModifiedColumns(myself, info, change, number);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasNumberOfModifiedColumnsGreaterThan(int number) {
    return AssertionsOnModifiedColumns.hasNumberOfModifiedColumnsGreaterThan(myself, info, change, number);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasNumberOfModifiedColumnsLessThan(int number) {
    return AssertionsOnModifiedColumns.hasNumberOfModifiedColumnsLessThan(myself, info, change, number);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasNumberOfModifiedColumnsGreaterThanOrEqualTo(int number) {
    return AssertionsOnModifiedColumns.hasNumberOfModifiedColumnsGreaterThanOrEqualTo(myself, info, change, number);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasNumberOfModifiedColumnsLessThanOrEqualTo(int number) {
    return AssertionsOnModifiedColumns.hasNumberOfModifiedColumnsLessThanOrEqualTo(myself, info, change, number);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasModifiedColumns(Integer... indexes) {
    return AssertionsOnModifiedColumns.hasModifiedColumns(myself, info, change, indexes);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasModifiedColumns(String... names) {
    return AssertionsOnModifiedColumns.hasModifiedColumns(myself, info, change, change.getColumnLetterCase(), names);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasNumberOfColumns(int expected) {
    return AssertionsOnNumberOfColumns.hasNumberOfColumns(myself, info, change.getColumnsNameList().size(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasNumberOfColumnsGreaterThan(int expected) {
    return AssertionsOnNumberOfColumns.hasNumberOfColumnsGreaterThan(myself, info, change.getColumnsNameList().size(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasNumberOfColumnsLessThan(int expected) {
    return AssertionsOnNumberOfColumns.hasNumberOfColumnsLessThan(myself, info, change.getColumnsNameList().size(), expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasNumberOfColumnsGreaterThanOrEqualTo(int expected) {
    return AssertionsOnNumberOfColumns.hasNumberOfColumnsGreaterThanOrEqualTo(myself, info, change.getColumnsNameList().size(),
                                                                              expected);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert hasNumberOfColumnsLessThanOrEqualTo(int expected) {
    return AssertionsOnNumberOfColumns.hasNumberOfColumnsLessThanOrEqualTo(myself, info, change.getColumnsNameList().size(), expected);
  }

  /**
   * Returns to level of assertion methods on {@link Changes}.
   *
   * @return a object of assertion methods on {@link Changes}.
   */
  public ChangesAssert returnToChanges() {
    return returnToOrigin();
  }
}
