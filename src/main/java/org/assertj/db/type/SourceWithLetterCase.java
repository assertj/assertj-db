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
package org.assertj.db.type;

import org.assertj.db.type.lettercase.LetterCase;
import org.assertj.db.type.lettercase.WithLetterCase;

/**
 * A source to indicates the informations to connect to the database with letter case.
 *
 * @author Régis Pouiller
 * @since 1.1.0
 */
public class SourceWithLetterCase extends Source implements WithLetterCase {

  /**
   * Letter case of the tables.
   */
  private final LetterCase tableLetterCase;
  /**
   * Letter case of the columns.
   */
  private final LetterCase columnLetterCase;
  /**
   * Letter case of the primary keys.
   */
  private final LetterCase primaryKeyLetterCase;

  /**
   * Constructor with the informations.
   *
   * @param url URL to the database.
   * @param user User to connect.
   * @param password Password to connect.
   * @param tableLetterCase Letter case of the tables.
   * @param columnLetterCase Letter case of the columns.
   * @param primaryKeyLetterCase Letter case of the primary keys.
   */
  public SourceWithLetterCase(String url, String user, String password,
                LetterCase tableLetterCase, LetterCase columnLetterCase, LetterCase primaryKeyLetterCase) {

    super(url, user, password);
    this.tableLetterCase = tableLetterCase;
    this.columnLetterCase = columnLetterCase;
    this.primaryKeyLetterCase = primaryKeyLetterCase;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LetterCase getColumnLetterCase() {
    return columnLetterCase;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LetterCase getPrimaryKeyLetterCase() {
    return primaryKeyLetterCase;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LetterCase getTableLetterCase() {
    return tableLetterCase;
  }
}
