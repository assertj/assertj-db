package org.assertj.db.display;

import org.assertj.db.display.impl.RepresentationType;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.navigation.PositionWithColumns;
import org.assertj.db.navigation.element.RowElement;
import org.assertj.db.navigation.origin.OriginWithValuesFromRow;
import org.assertj.db.type.Row;
import org.assertj.db.type.Value;

import java.util.List;

import static org.assertj.db.util.Descriptions.getRowValueDescription;

/**
 * Display methods for a {@code Row} of a {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeRowDisplay
        extends AbstractDisplayWithOriginWithColumnsAndRowsFromChange<ChangeRowDisplay, ChangeDisplay>
        implements RowElement,
        OriginWithValuesFromRow<ChangesDisplay, ChangeDisplay, ChangeColumnDisplay, ChangeRowDisplay, ChangeRowValueDisplay> {

  /**
   * Position of navigation to value.
   */
  private final PositionWithColumns<ChangeRowDisplay, ChangeRowValueDisplay, Value> valuePosition;

  /**
   * The actual row on which the display is.
   */
  private final Row row;

  /**
   * Constructor.
   *
   * @param origin The display of {@link org.assertj.db.navigation.origin.Origin}.
   * @param row The {@link Row} on which are the displays.
   */
  public ChangeRowDisplay(ChangeDisplay origin, Row row) {
    super(ChangeRowDisplay.class, origin);
    this.row = row;
    valuePosition = new PositionWithColumns<ChangeRowDisplay, ChangeRowValueDisplay, Value>(this, ChangeRowValueDisplay.class) {
      @Override protected String getDescription(int index) {
        List<String> columnsNameList = ChangeRowDisplay.this.row.getColumnsNameList();
        String columnName = columnsNameList.get(index);
        return getRowValueDescription(info, index, columnName);
      }
    };
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueDisplay value() {
    if (row == null) {
      throw new AssertJDBException("Row do not exist");
    }
    return valuePosition.getInstance(row.getValuesList()).withType(displayType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueDisplay value(int index) {
    if (row == null) {
      throw new AssertJDBException("Row do not exist");
    }
    return valuePosition.getInstance(row.getValuesList(), index).withType(displayType);
  }

  /** {@inheritDoc} */
  @Override
  public ChangeRowValueDisplay value(String columnName) {
    if (row == null) {
      throw new AssertJDBException("Row do not exist");
    }
    return valuePosition.getInstance(row.getValuesList(), row.getColumnsNameList(),
                                     columnName, row.getColumnLetterCase()).withType(displayType);
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
    return displayType.getRowRepresentation(info, row);
  }
}
