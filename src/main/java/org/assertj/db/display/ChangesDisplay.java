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

import org.assertj.db.display.impl.Representation;
import org.assertj.db.display.impl.RepresentationType;
import org.assertj.db.navigation.PositionWithChanges;
import org.assertj.db.navigation.element.ChangesElement;
import org.assertj.db.navigation.origin.OriginWithChanges;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Changes;
import org.assertj.db.util.Descriptions;

/**
 * Display methods for {@link Changes}.
 *
 * @author Régis Pouiller
 */
public class ChangesDisplay
        extends AbstractDisplayWithOrigin<ChangesDisplay, ChangesDisplay>
        implements ChangesElement,
        OriginWithChanges<ChangesDisplay, ChangeDisplay> {

  /**
   * The actual changes on which the display is.
   */
  private final Changes changes;

  /**
   * Position of navigation to changes and to change.
   */
  private final PositionWithChanges<ChangesDisplay, ChangeDisplay> changesPosition;

  /**
   * Constructor.
   *
   * @param changes The {@link Changes} on which are the displays.
   */
  ChangesDisplay(Changes changes) {
    this(null, changes);
  }

  /**
   * Constructor.
   *
   * @param origin The display of {@link org.assertj.db.navigation.origin.Origin}.
   * @param changes The {@link Changes} on which are the displays.
   */
  public ChangesDisplay(ChangesDisplay origin, Changes changes) {
    super(ChangesDisplay.class, origin);
    this.changes = changes;
    changesPosition = new PositionWithChanges(this, ChangesDisplay.class, ChangeDisplay.class) {
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
    withType(RepresentationType.PLAIN);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesDisplay ofAll() {
    if (origin != null) {
      return origin.ofAll();
    }
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ChangesDisplay ofCreation() {
    if (origin != null) {
      return origin.ofCreation();
    }
    return changesPosition.getChangesInstance(changes, ChangeType.CREATION, null).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesDisplay ofModification() {
    if (origin != null) {
      return origin.ofModification();
    }
    return changesPosition.getChangesInstance(changes, ChangeType.MODIFICATION, null).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesDisplay ofDeletion() {
    if (origin != null) {
      return origin.ofDeletion();
    }
    return changesPosition.getChangesInstance(changes, ChangeType.DELETION, null).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesDisplay ofCreationOnTable(String tableName) {
    if (origin != null) {
      return origin.ofCreationOnTable(tableName);
    }
    return changesPosition.getChangesInstance(changes, ChangeType.CREATION, tableName).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesDisplay ofModificationOnTable(String tableName) {
    if (origin != null) {
      return origin.ofModificationOnTable(tableName);
    }
    return changesPosition.getChangesInstance(changes, ChangeType.MODIFICATION, tableName).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesDisplay ofDeletionOnTable(String tableName) {
    if (origin != null) {
      return origin.ofDeletionOnTable(tableName);
    }
    return changesPosition.getChangesInstance(changes, ChangeType.DELETION, tableName).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesDisplay onTable(String tableName) {
    if (origin != null) {
      return origin.onTable(tableName);
    }
    return changesPosition.getChangesInstance(changes, null, tableName).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay change() {
    return changesPosition.getChangeInstance(changes, null, null).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay change(int index) {
    return changesPosition.getChangeInstance(changes, null, null, index).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOfCreation() {
    if (origin != null) {
      return origin.changeOfCreation();
    }
    return changesPosition.getChangeInstance(changes, ChangeType.CREATION, null).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOfCreation(int index) {
    if (origin != null) {
      return origin.changeOfCreation(index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.CREATION, null, index).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOfModification() {
    if (origin != null) {
      return origin.changeOfModification();
    }
    return changesPosition.getChangeInstance(changes, ChangeType.MODIFICATION, null).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOfModification(int index) {
    if (origin != null) {
      return origin.changeOfModification(index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.MODIFICATION, null, index).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOfDeletion() {
    if (origin != null) {
      return origin.changeOfDeletion();
    }
    return changesPosition.getChangeInstance(changes, ChangeType.DELETION, null).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOfDeletion(int index) {
    if (origin != null) {
      return origin.changeOfDeletion(index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.DELETION, null, index).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOnTable(String tableName) {
    if (origin != null) {
      return origin.changeOnTable(tableName);
    }
    return changesPosition.getChangeInstance(changes, null, tableName).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOnTable(String tableName, int index) {
    if (origin != null) {
      return origin.changeOnTable(tableName, index);
    }
    return changesPosition.getChangeInstance(changes, null, tableName, index).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOnTableWithPks(String tableName, Object... pksValues) {
    if (origin != null) {
      return origin.changeOnTableWithPks(tableName, pksValues);
    }
    return changesPosition.getChangeInstanceWithPK(changes, tableName, pksValues).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOfCreationOnTable(String tableName) {
    if (origin != null) {
      return origin.changeOfCreationOnTable(tableName);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.CREATION, tableName).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOfCreationOnTable(String tableName, int index) {
    if (origin != null) {
      return origin.changeOfCreationOnTable(tableName, index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.CREATION, tableName, index).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOfModificationOnTable(String tableName) {
    if (origin != null) {
      return origin.changeOfModificationOnTable(tableName);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.MODIFICATION, tableName).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOfModificationOnTable(String tableName, int index) {
    if (origin != null) {
      return origin.changeOfModificationOnTable(tableName, index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.MODIFICATION, tableName, index).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOfDeletionOnTable(String tableName) {
    if (origin != null) {
      return origin.changeOfDeletionOnTable(tableName);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.DELETION, tableName).withType(representationType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeDisplay changeOfDeletionOnTable(String tableName, int index) {
    if (origin != null) {
      return origin.changeOfDeletionOnTable(tableName, index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.DELETION, tableName, index).withType(representationType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getRepresentation(Representation representationType) {
    return representationType.getChangesRepresentation(info, changes);
  }
}
