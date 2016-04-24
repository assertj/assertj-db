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
 * Copyright 2012-2016 the original author or authors.
 */
package org.assertj.db.output;

import org.assertj.db.navigation.PositionWithChanges;
import org.assertj.db.navigation.element.ChangesElement;
import org.assertj.db.navigation.origin.OriginWithChanges;
import org.assertj.db.output.impl.Output;
import org.assertj.db.output.impl.OutputType;
import org.assertj.db.type.Change;
import org.assertj.db.type.ChangeType;
import org.assertj.db.type.Changes;
import org.assertj.db.util.Descriptions;

/**
 * Output methods for {@link Changes}.
 *
 * @author Régis Pouiller
 */
public class ChangesOutputter
        extends AbstractOutputterWithOrigin<ChangesOutputter, ChangesOutputter>
        implements ChangesElement,
        OriginWithChanges<ChangesOutputter, ChangeOutputter> {

  /**
   * The actual changes on which the output is.
   */
  private final Changes changes;

  /**
   * Position of navigation to changes and to change.
   */
  private final PositionWithChanges<ChangesOutputter, ChangeOutputter> changesPosition;

  /**
   * Constructor.
   *
   * @param changes The {@link Changes} on which are the displays.
   */
  ChangesOutputter(Changes changes) {
    this(null, changes);
  }

  /**
   * Constructor.
   *
   * @param origin The output of {@link org.assertj.db.navigation.origin.Origin}.
   * @param changes The {@link Changes} on which are the displays.
   */
  public ChangesOutputter(ChangesOutputter origin, Changes changes) {
    super(ChangesOutputter.class, origin);
    this.changes = changes;
    changesPosition = new PositionWithChanges(this, ChangesOutputter.class, ChangeOutputter.class) {
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
    withType(OutputType.PLAIN);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesOutputter ofAll() {
    if (origin != null) {
      return origin.ofAll();
    }
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ChangesOutputter ofCreation() {
    if (origin != null) {
      return origin.ofCreation();
    }
    return changesPosition.getChangesInstance(changes, ChangeType.CREATION, null).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesOutputter ofModification() {
    if (origin != null) {
      return origin.ofModification();
    }
    return changesPosition.getChangesInstance(changes, ChangeType.MODIFICATION, null).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesOutputter ofDeletion() {
    if (origin != null) {
      return origin.ofDeletion();
    }
    return changesPosition.getChangesInstance(changes, ChangeType.DELETION, null).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesOutputter ofCreationOnTable(String tableName) {
    if (origin != null) {
      return origin.ofCreationOnTable(tableName);
    }
    return changesPosition.getChangesInstance(changes, ChangeType.CREATION, tableName).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesOutputter ofModificationOnTable(String tableName) {
    if (origin != null) {
      return origin.ofModificationOnTable(tableName);
    }
    return changesPosition.getChangesInstance(changes, ChangeType.MODIFICATION, tableName).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesOutputter ofDeletionOnTable(String tableName) {
    if (origin != null) {
      return origin.ofDeletionOnTable(tableName);
    }
    return changesPosition.getChangesInstance(changes, ChangeType.DELETION, tableName).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangesOutputter onTable(String tableName) {
    if (origin != null) {
      return origin.onTable(tableName);
    }
    return changesPosition.getChangesInstance(changes, null, tableName).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter change() {
    return changesPosition.getChangeInstance(changes, null, null).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter change(int index) {
    return changesPosition.getChangeInstance(changes, null, null, index).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOfCreation() {
    if (origin != null) {
      return origin.changeOfCreation();
    }
    return changesPosition.getChangeInstance(changes, ChangeType.CREATION, null).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOfCreation(int index) {
    if (origin != null) {
      return origin.changeOfCreation(index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.CREATION, null, index).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOfModification() {
    if (origin != null) {
      return origin.changeOfModification();
    }
    return changesPosition.getChangeInstance(changes, ChangeType.MODIFICATION, null).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOfModification(int index) {
    if (origin != null) {
      return origin.changeOfModification(index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.MODIFICATION, null, index).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOfDeletion() {
    if (origin != null) {
      return origin.changeOfDeletion();
    }
    return changesPosition.getChangeInstance(changes, ChangeType.DELETION, null).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOfDeletion(int index) {
    if (origin != null) {
      return origin.changeOfDeletion(index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.DELETION, null, index).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOnTable(String tableName) {
    if (origin != null) {
      return origin.changeOnTable(tableName);
    }
    return changesPosition.getChangeInstance(changes, null, tableName).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOnTable(String tableName, int index) {
    if (origin != null) {
      return origin.changeOnTable(tableName, index);
    }
    return changesPosition.getChangeInstance(changes, null, tableName, index).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOnTableWithPks(String tableName, Object... pksValues) {
    if (origin != null) {
      return origin.changeOnTableWithPks(tableName, pksValues);
    }
    return changesPosition.getChangeInstanceWithPK(changes, tableName, pksValues).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOfCreationOnTable(String tableName) {
    if (origin != null) {
      return origin.changeOfCreationOnTable(tableName);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.CREATION, tableName).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOfCreationOnTable(String tableName, int index) {
    if (origin != null) {
      return origin.changeOfCreationOnTable(tableName, index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.CREATION, tableName, index).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOfModificationOnTable(String tableName) {
    if (origin != null) {
      return origin.changeOfModificationOnTable(tableName);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.MODIFICATION, tableName).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOfModificationOnTable(String tableName, int index) {
    if (origin != null) {
      return origin.changeOfModificationOnTable(tableName, index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.MODIFICATION, tableName, index).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOfDeletionOnTable(String tableName) {
    if (origin != null) {
      return origin.changeOfDeletionOnTable(tableName);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.DELETION, tableName).withType(outputType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeOutputter changeOfDeletionOnTable(String tableName, int index) {
    if (origin != null) {
      return origin.changeOfDeletionOnTable(tableName, index);
    }
    return changesPosition.getChangeInstance(changes, ChangeType.DELETION, tableName, index).withType(outputType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getOutput(Output outputType) {
    return outputType.getChangesOutput(info, changes);
  }
}
