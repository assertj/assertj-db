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
package org.assertj.db.display.impl;

import org.assertj.core.api.WritableAssertionInfo;
import org.assertj.db.type.*;

/**
 * Enumeration of the different types of representation.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public enum RepresentationType implements Representation {

  /**
   * Plain display.
   * @see PlainRepresentation
   */
  PLAIN(PlainRepresentation.INSTANCE);

  /**
   * The implementation of the display.
   */
  private Representation displayer;

  /**
   * Constructor.
   * @param displayer The implementation of the display.
   */
  RepresentationType(Representation displayer) {
    this.displayer = displayer;
  }

  /** {@inheritDoc} */
  @Override
  public String getTableRepresentation(WritableAssertionInfo info, Table table) {
    return displayer.getTableRepresentation(info, table);
  }

  /** {@inheritDoc} */
  @Override
  public String getRequestRepresentation(WritableAssertionInfo info, Request request) {
    return displayer.getRequestRepresentation(info, request);
  }

  /** {@inheritDoc} */
  @Override
  public String getChangesRepresentation(WritableAssertionInfo info, Changes changes) {
    return displayer.getChangesRepresentation(info, changes);
  }

  /** {@inheritDoc} */
  @Override
  public String getChangeRepresentation(WritableAssertionInfo info, Change change) {
    return displayer.getChangeRepresentation(info, change);
  }

  /** {@inheritDoc} */
  @Override
  public String getRowRepresentation(WritableAssertionInfo info, Row row) {
    return displayer.getRowRepresentation(info, row);
  }

  /** {@inheritDoc} */
  @Override
  public String getColumnRepresentation(WritableAssertionInfo info, Column column) {
    return displayer.getColumnRepresentation(info, column);
  }

  /** {@inheritDoc} */
  @Override
  public String getValueRepresentation(WritableAssertionInfo info, Value value) {
    return displayer.getValueRepresentation(info, value);
  }
}
