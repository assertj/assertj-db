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
 * Copyright 2015-2021 the original author or authors.
 */
package org.assertj.db.output;

import org.assertj.db.navigation.ToValue;
import org.assertj.db.navigation.ToValueFromRow;
import org.assertj.db.output.impl.Output;
import org.assertj.db.type.Value;

/**
 * Output methods for a value of a {@code Row} of a {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeRowValueOutputter
        extends AbstractOutputterWithValues<ChangeRowValueOutputter, ChangeRowOutputter>
        implements ToValue<ChangeRowValueOutputter>,
        ToValueFromRow<ChangeRowValueOutputter> {

  /**
   * Constructor.
   *
   * @param origin The output of {@link org.assertj.db.navigation.origin.Origin}.
   * @param value The value on which are the displays.
   */
  public ChangeRowValueOutputter(ChangeRowOutputter origin, Value value) {
    super(ChangeRowValueOutputter.class, origin, value);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueOutputter value() {
    return origin.value();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueOutputter value(int index) {
    return origin.value(index);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueOutputter value(String columnName) {
    return origin.value(columnName);
  }

  /**
   * Returns to level of output methods on a {@link org.assertj.db.type.Row}.
   *
   * @return a object of output methods on a {@link org.assertj.db.type.Row}.
   */
  public ChangeRowOutputter returnToRow() {
    return returnToOrigin();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getOutput(Output outputType) {
    return outputType.getValueOutput(info, value);
  }
}