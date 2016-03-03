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
package org.assertj.db.api;

import org.assertj.db.api.assertions.AssertOnNumberOfChanges;
import org.assertj.db.api.assertions.impl.AssertionsOnNumberOfChanges;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.navigation.PositionWithChanges;
import org.assertj.db.navigation.element.ChangesElement;
import org.assertj.db.navigation.origin.OriginWithChanges;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Value;
import org.assertj.db.util.Descriptions;
import org.assertj.db.util.Values;

import java.util.Arrays;
import java.util.List;

/**
 * Assertion methods for {@link Changes}.
 *
 * @author Régis Pouiller
 */
public class ChangesAssert
        extends AbstractAssertWithOrigin<ChangesAssert, ChangesAssert>
        implements ChangesElement,
                   OriginWithChanges<ChangesAssert, ChangeAssert>,
                   AssertOnNumberOfChanges<ChangesAssert> {

  /**
   * The actual changes on which the assertion is.
   */
  private final Changes changes;

  /**
   * Position of navigation to changes and to change.
   */
  private final PositionWithChanges<ChangesAssert, ChangeAssert> changesPosition;

//  /**
//   * Index of the next change to get per type of change.
//   */
//  private final Map<ChangeType, Map<String, Integer>> indexNextChangeMap = new HashMap<>();
//
//  /**
//   * Map the change assert with the change in key (contains the change assert already generated).
//   */
//  private final Map<Change, ChangeAssert> changeMap = new HashMap<>();

  /**
   * Constructor.
   *
   * @param changes The {@link Changes} on which are the assertions.
   */
  ChangesAssert(Changes changes) {
    this(null, changes);
  }

  /**
   * Constructor.
   *
   * @param origin The assertion of {@link org.assertj.db.navigation.origin.Origin}.
   * @param changes The {@link Changes} on which are the assertions.
   */
  public ChangesAssert(ChangesAssert origin, Changes changes) {
    super(ChangesAssert.class, origin);
    this.changes = changes;
    changesPosition = new PositionWithChanges(this, ChangesAssert.class, ChangeAssert.class) {
      @Override
      protected String getChangesDescription(ChangeType changeType, String tableName) {
        return Descriptions.getChangesDescription(info, changeType, tableName);
      }

      @Override
      protected String getChangeDescription(Changes changes, Change change, int index,
                                            ChangeType changeType, String tableName) {

        return Descriptions.getChangeDescription(info, changes, change, index, changeType, tableName);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  public ChangesAssert ofAll() {
    if (origin != null) {
      return origin.ofAll();
    }
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ChangesAssert ofCreation() {
    if (origin != null) {
      return origin.ofCreation();
    }
    return changesPosition.getChangesInstance(changes, ChangeType.CREATION, null);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesAssert ofModification() {
    if (origin != null) {
      return origin.ofModification();
    }
    return changesPosition.getChangesInstance(changes, ChangeType.MODIFICATION, null);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesAssert ofDeletion() {
    if (origin != null) {
      return origin.ofDeletion();
    }
    return changesPosition.getChangesInstance(changes, ChangeType.DELETION, null);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesAssert ofCreationOnTable(String tableName) {
    if (origin != null) {
      return origin.ofCreationOnTable(tableName);
    }
    return changesPosition.getChangesInstance(changes, ChangeType.CREATION, tableName);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesAssert ofModificationOnTable(String tableName) {
    if (origin != null) {
      return origin.ofModificationOnTable(tableName);
    }
    return changesPosition.getChangesInstance(changes, ChangeType.MODIFICATION, tableName);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesAssert ofDeletionOnTable(String tableName) {
    if (origin != null) {
      return origin.ofDeletionOnTable(tableName);
    }
    return changesPosition.getChangesInstance(changes, ChangeType.DELETION, tableName);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesAssert onTable(String tableName) {
    if (origin != null) {
      return origin.onTable(tableName);
    }
    return changesPosition.getChangesInstance(changes, null, tableName);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert change() {
    return changesPosition.getChangeInstance(changes, null, null);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert change(int index) {
    return changesPosition.getChangeInstance(changes, null, null, index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOfCreation() {
    if (origin != null) {
      return origin.changeOfCreation();
    }
    return changesPosition.getChangeInstance(changes, ChangeType.CREATION, null);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOfCreation(int index) {
    if (origin != null) {
      return origin.changeOfCreation(index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.CREATION, null, index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOfModification() {
    if (origin != null) {
      return origin.changeOfModification();
    }
    return changesPosition.getChangeInstance(changes, ChangeType.MODIFICATION, null);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOfModification(int index) {
    if (origin != null) {
      return origin.changeOfModification(index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.MODIFICATION, null, index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOfDeletion() {
    if (origin != null) {
      return origin.changeOfDeletion();
    }
    return changesPosition.getChangeInstance(changes, ChangeType.DELETION, null);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOfDeletion(int index) {
    if (origin != null) {
      return origin.changeOfDeletion(index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.DELETION, null, index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOnTable(String tableName) {
    if (origin != null) {
      return origin.changeOnTable(tableName);
    }
    return changesPosition.getChangeInstance(changes, null, tableName);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOnTable(String tableName, int index) {
    if (origin != null) {
      return origin.changeOnTable(tableName, index);
    }
    return changesPosition.getChangeInstance(changes, null, tableName, index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOnTableWithPks(String tableName, Object... pksValues) {
    if (origin != null) {
      return origin.changeOnTableWithPks(tableName, pksValues);
    }
    Changes changes = this.changes.getChangesOfTable(tableName);
    List<Change> changesList = changes.getChangesList();
    int index = 0;
    for (Change change : changesList) {
      List<Value> pksValueList = change.getPksValueList();
      Value[] values = pksValueList.toArray(new Value[pksValueList.size()]);
      boolean equal = false;
      if (pksValues.length == values.length) {
        equal = true;
        for (int i = 0; i < pksValues.length; i++) {
          if (!Values.areEqual(values[i], pksValues[i])) {
            equal = false;
          }
        }
      }
      if (equal) {
        return changesPosition.getChangeInstance(changes, null, tableName, index);
      }
      index++;
    }
    throw new AssertJDBException("No change found for table " + tableName + " and primary keys " + Arrays.asList(pksValues));
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOfCreationOnTable(String tableName) {
    if (origin != null) {
      return origin.changeOfCreationOnTable(tableName);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.CREATION, tableName);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOfCreationOnTable(String tableName, int index) {
    if (origin != null) {
      return origin.changeOfCreationOnTable(tableName, index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.CREATION, tableName, index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOfModificationOnTable(String tableName) {
    if (origin != null) {
      return origin.changeOfModificationOnTable(tableName);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.MODIFICATION, tableName);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOfModificationOnTable(String tableName, int index) {
    if (origin != null) {
      return origin.changeOfModificationOnTable(tableName, index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.MODIFICATION, tableName, index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOfDeletionOnTable(String tableName) {
    if (origin != null) {
      return origin.changeOfDeletionOnTable(tableName);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.DELETION, tableName);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeAssert changeOfDeletionOnTable(String tableName, int index) {
    if (origin != null) {
      return origin.changeOfDeletionOnTable(tableName, index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.DELETION, tableName, index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesAssert hasNumberOfChanges(int expected) {
    return AssertionsOnNumberOfChanges.hasNumberOfChanges(myself, info, changes, expected);
  }
}
