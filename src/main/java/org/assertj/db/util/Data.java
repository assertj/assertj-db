package org.assertj.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.assertj.db.type.Source;

/**
 * Utility methods related to data and database.
 * 
 * @author Régis Pouiller
 * 
 */
public class Data {

  /**
   * Private constructor.
   */
  private Data() {
    // Empty
  }

  /**
   * Returns a {@link Connection} from a {@link DataSource} or from a {@link Source}.
   * 
   * @return A {@link Connection} differently, depending if it is a {@link DataSource} or a {@link Source}.
   * @throws SQLException SQL Exception
   */
  public static Connection getConnection(DataSource dataSource, Source source) throws SQLException {
    // Get a Connection differently, depending if it is a DataSource or a Source.
    if (dataSource != null) {
      return dataSource.getConnection();
    } else {
      return DriverManager.getConnection(source.getUrl(), source.getUser(), source.getPassword());
    }
  }

}
