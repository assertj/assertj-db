package org.assertj.db.display;

import org.assertj.db.display.impl.RepresentationType;
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
  protected String getRepresentation(RepresentationType displayType) {
    return displayType.getValueRepresentation(info, value);
  }
}
