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
import org.assertj.db.navigation.ToValue;
import org.assertj.db.navigation.ToValueFromRow;
import org.assertj.db.type.Value;

/**
 * Display methods for a value of a {@code Row} of a {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeRowValueDisplay
        extends AbstractDisplayWithValues<ChangeRowValueDisplay, ChangeRowDisplay>
        implements ToValue<ChangeRowValueDisplay>,
        ToValueFromRow<ChangeRowValueDisplay> {

  /**
   * Constructor.
   *
   * @param origin The display of {@link org.assertj.db.navigation.origin.Origin}.
   * @param value The value on which are the displays.
   */
  public ChangeRowValueDisplay(ChangeRowDisplay origin, Value value) {
    super(ChangeRowValueDisplay.class, origin, value);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueDisplay value() {
    return origin.value();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueDisplay value(int index) {
    return origin.value(index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueDisplay value(String columnName) {
    return origin.value(columnName);
  }

  /**
   * Returns to level of display methods on a {@link org.assertj.db.type.Row}.
   *
   * @return a object of display methods on a {@link org.assertj.db.type.Row}.
   */
  public ChangeRowDisplay returnToRow() {
    return returnToOrigin();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getRepresentation(Representation representationType) {
    return representationType.getValueRepresentation(info, value);
  }
}
