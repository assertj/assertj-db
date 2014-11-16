package org.assertj.db.type;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * This class represents element from the database.
 * 
 * @author Régis Pouiller
 *
 * @param <D> Class of the subclass (an implementation of {@link AbstractDbElement}) : useful for the fluent methods
 *          (setters).
 */
public class AbstractDbElement<D extends AbstractDbElement<D>> {

  /**
   * Class of the element.
   */
  protected final D myself;
  /**
   * Source of the data.
   */
  private Source source;
  /**
   * Data source.
   */
  private DataSource dataSource;

  /**
   * Default constructor.
   * @param selfType Class of this element : a sub-class of {@code AbstractDbElement}.
   */
  AbstractDbElement(Class<D> selfType) {
    myself = selfType.cast(this);
  }

  /**
   * Constructor.
   * @param selfType Class of this element : a sub-class of {@code AbstractDbElement}.
   * @param source The {@link Source} to connect to the database (must be not {@code null}).
   * @throws NullPointerException If {@code source} is {@code null}.
   */
  AbstractDbElement(Class<D> selfType, Source source) {
    this(selfType);
    this.source = source;
  }

  /**
   * Constructor.
   * @param selfType Class of this element : a sub-class of {@code AbstractDbElement}.
   * @param dataSource The {@link DataSource} (must be not {@code null}).
   * @throws NullPointerException If {@code dataSource} is {@code null}.
   */
  AbstractDbElement(Class<D> selfType, DataSource dataSource) {
    this(selfType);
    this.dataSource = dataSource;
  }

  /**
   * Return the source.
   * 
   * @see #setSource(Source)
   * @return The {@link Source} to connect.
   */
  public Source getSource() {
    return source;
  }

  /**
   * Sets the source.
   * 
   * @see #getSource()
   * @param source {@link Source} to connect to the database (must be not {@code null}).
   * @return The actual instance.
   * @throws NullPointerException If {@code source} is {@code null}.
   */
  public D setSource(Source source) {
    if (source == null) {
      throw new NullPointerException("source must be not null");
    }
    this.source = source;
    this.dataSource = null;
    return myself;
  }

  /**
   * Return the data source.
   * 
   * @see #setDataSource(DataSource)
   * @return The data source.
   */
  public DataSource getDataSource() {
    return dataSource;
  }

  /**
   * Sets the data source.
   * 
   * @see #getDataSource()
   * @param dataSource The {@link DataSource} (must be not {@code null}).
   * @return The actual instance.
   * @throws NullPointerException If {@code dataSource} is {@code null}.
   */
  public D setDataSource(DataSource dataSource) {
    if (dataSource == null) {
      throw new NullPointerException("dataSource must be not null");
    }
    this.source = null;
    this.dataSource = dataSource;
    return myself;
  }

  /**
   * Returns a {@link Connection} from a {@link DataSource} or from a {@link Source}.
   * 
   * @return A {@link Connection} differently, depending if it is a {@link DataSource} or a {@link Source}.
   * @throws SQLException SQL Exception
   */
  protected Connection getConnection() throws SQLException {
    if (dataSource == null && source == null) {
      throw new NullPointerException("connection or dataSource must be not null");
    }

    // Get a Connection differently, depending if it is a DataSource or a Source.
    if (dataSource != null) {
      return dataSource.getConnection();
    } else {
      return DriverManager.getConnection(source.getUrl(), source.getUser(), source.getPassword());
    }
  }

}
