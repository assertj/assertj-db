package org.assertj.db.display;

import org.assertj.db.display.impl.RepresentationType;
import org.assertj.db.navigation.PositionWithPoints;
import org.assertj.db.navigation.element.ColumnElement;
import org.assertj.db.navigation.origin.OriginWithValuesFromColumn;
import org.assertj.db.type.Value;

import static org.assertj.db.util.Descriptions.getColumnValueAtEndPointDescription;
import static org.assertj.db.util.Descriptions.getColumnValueAtStartPointDescription;

/**
 * Display methods for a {@code Column} of a {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeColumnDisplay
        extends AbstractDisplayWithOriginWithColumnsAndRowsFromChange<ChangeColumnDisplay, ChangeDisplay>
        implements ColumnElement,
        OriginWithValuesFromColumn<ChangesDisplay, ChangeDisplay, ChangeColumnDisplay, ChangeRowDisplay, ChangeColumnValueDisplay> {

  /**
   * The name of the column.
   */
  private final String columnName;
  /**
   * The actual value at start point.
   */
  private final Value valueAtStartPoint;

  /**
   * The actual value at end point.
   */
  private final Value valueAtEndPoint;

  /**
   * Position of navigation to row.
   */
  private final PositionWithPoints<ChangeColumnDisplay, ChangeColumnValueDisplay, Value> valuePosition;

  /**
   * Constructor.
   *
   * @param columnName The column name.
   * @param origin The display of {@link org.assertj.db.navigation.origin.Origin}.
   * @param valueAtStartPoint The value at start point.
   * @param valueAtEndPoint The value at end point.
   */
  public ChangeColumnDisplay(ChangeDisplay origin, String columnName, Value valueAtStartPoint, Value valueAtEndPoint) {
    super(ChangeColumnDisplay.class, origin);
    this.columnName = columnName;
    this.valueAtStartPoint = valueAtStartPoint;
    this.valueAtEndPoint = valueAtEndPoint;
    valuePosition = new PositionWithPoints<ChangeColumnDisplay, ChangeColumnValueDisplay, Value>(this, ChangeColumnValueDisplay.class, Value.class, valueAtStartPoint, valueAtEndPoint) {

      @Override protected String getDescriptionAtStartPoint() {
        return getColumnValueAtStartPointDescription(info);
      }

      @Override protected String getDescriptionAtEndPoint() {
        return getColumnValueAtEndPointDescription(info);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueDisplay valueAtStartPoint() {
    return valuePosition.getInstanceAtStartPoint().withType(displayType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueDisplay valueAtEndPoint() {
    return valuePosition.getInstanceAtEndPoint().withType(displayType);
  }

  /**
   * Returns to level of display methods on a {@link org.assertj.db.type.Change}.
   *
   * @return a object of display methods on a {@link org.assertj.db.type.Change}.
   */
  public ChangeDisplay returnToChange() {
    return returnToOrigin();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getRepresentation(RepresentationType displayType) {
    return displayType.getChangeColumnRepresentation(info, columnName, valueAtStartPoint, valueAtEndPoint);
  }
}
