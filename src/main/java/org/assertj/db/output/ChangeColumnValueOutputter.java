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
package org.assertj.db.output;

import org.assertj.db.navigation.ToValueFromColumn;
import org.assertj.db.output.impl.Output;
import org.assertj.db.type.Value;

/**
 * Output methods for a value of a {@code Column} of a {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeColumnValueOutputter
        extends AbstractOutputterWithValues<ChangeColumnValueOutputter, ChangeColumnOutputter>
        implements ToValueFromColumn<ChangeColumnValueOutputter> {

  /**
   * Constructor.
   *
   * @param origin The output of {@link org.assertj.db.navigation.origin.Origin}.
   * @param value The value on which are the displays.
   */
  public ChangeColumnValueOutputter(ChangeColumnOutputter origin, Value value) {
    super(ChangeColumnValueOutputter.class, origin, value);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueOutputter valueAtStartPoint() {
    return origin.valueAtStartPoint();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueOutputter valueAtEndPoint() {
    return origin.valueAtEndPoint();
  }

  /**
   * Returns to level of output methods on a {@link org.assertj.db.type.Column}.
   *
   * @return a object of output methods on a {@link org.assertj.db.type.Column}.
   */
  public ChangeColumnOutputter returnToColumn() {
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
