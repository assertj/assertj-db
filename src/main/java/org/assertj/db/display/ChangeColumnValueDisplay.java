package org.assertj.db.display;

import org.assertj.db.display.impl.RepresentationType;
import org.assertj.db.navigation.ToValueFromColumn;
import org.assertj.db.type.Value;

/**
 * Display methods for a value of a {@code Column} of a {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeColumnValueDisplay
        extends AbstractDisplayWithValues<ChangeColumnValueDisplay, ChangeColumnDisplay>
        implements ToValueFromColumn<ChangeColumnValueDisplay> {

  /**
   * Constructor.
   *
   * @param origin The display of {@link org.assertj.db.navigation.origin.Origin}.
   * @param value The value on which are the displays.
   */
  public ChangeColumnValueDisplay(ChangeColumnDisplay origin, Value value) {
    super(ChangeColumnValueDisplay.class, origin, value);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueDisplay valueAtStartPoint() {
    return origin.valueAtStartPoint();
  }

  /** {@inheritDoc} */
  @Override
  public ChangeColumnValueDisplay valueAtEndPoint() {
    return origin.valueAtEndPoint();
  }

  /**
   * Returns to level of display methods on a {@link org.assertj.db.type.Column}.
   *
   * @return a object of display methods on a {@link org.assertj.db.type.Column}.
   */
  public ChangeColumnDisplay returnToColumn() {
    return returnToOrigin();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getRepresentation(RepresentationType displayType) {
    return displayType.getValueRepresentation(info, value);
  }
}
