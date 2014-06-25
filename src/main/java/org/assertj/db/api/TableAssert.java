package org.assertj.db.api;

import org.assertj.db.type.Table;

/**
 * Assertion about the datas in a <code>{@link Table}</code>.
 * 
 * @author Régis Pouiller
 * 
 */
public class TableAssert extends AbstractDbAssert<TableAssert, Table> {

  /**
   * Constructor of the assertion.
   * 
   * @param table Table on which the assertion is.
   */
  TableAssert(Table table) {
    super(table, TableAssert.class);
  }
}
