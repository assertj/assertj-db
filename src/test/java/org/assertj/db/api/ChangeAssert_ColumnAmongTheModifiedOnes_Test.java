package org.assertj.db.api;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Change;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on the assertion methods on modified column of {@code Change}.
 *
 * @author Régis Pouiller
 */
public class ChangeAssert_ColumnAmongTheModifiedOnes_Test extends AbstractTest {

  /**
   * This method tests the {@code column} methods.
   *
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  @NeedReload
  public void test_when_get_columns()
          throws IllegalArgumentException, IllegalAccessException, SecurityException, NoSuchFieldException {

    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangesAssert assertion = assertThat(changes);

    Field field1 = ChangeColumnAssert.class.getDeclaredField("valueAtStartPoint");
    Field field2 = ChangeColumnAssert.class.getDeclaredField("valueAtEndPoint");
    field1.setAccessible(true);
    field2.setAccessible(true);

    List<Change> changesList = changes.getChangesList();
    Change change0 = changesList.get(0);
    Change change3 = changesList.get(3);
    Change change7 = changesList.get(7);

    ChangeAssert changeAssert0 = assertion.change();
    changeAssert0.isCreation();
    ChangeColumnAssert changeColumnAssert0 = changeAssert0.columnAmongTheModifiedOnes();
    assertThat(field1.get(changeColumnAssert0)).isNull();
    assertThat(field2.get(changeColumnAssert0)).isSameAs(change0.getRowAtEndPoint().getValuesList().get(0));

    ChangeAssert changeAssert3 = assertion.change(3);
    ChangeColumnAssert changeColumnAssert3 = changeAssert3.columnAmongTheModifiedOnes();
    assertThat(field1.get(changeColumnAssert3)).isEqualTo("Sigourney")
                                               .isSameAs(change3.getRowAtStartPoint().getValuesList().get(2));
    assertThat(field2.get(changeColumnAssert3)).isEqualTo("Susan Alexandra")
                                               .isSameAs(change3.getRowAtEndPoint().getValuesList().get(2));

    ChangeAssert changeAssert7 = assertion.change(7);
    ChangeColumnAssert changeColumnAssert7 = changeAssert7.columnAmongTheModifiedOnes();
    assertThat(field1.get(changeColumnAssert7)).isSameAs(change7.getRowAtStartPoint().getValuesList().get(0));
    assertThat(field2.get(changeColumnAssert7)).isNull();
  }

  /**
   * Test that the value got from the cache is the same than the original.
   */
  @Test
  @NeedReload
  public void test_the_value_from_the_cache() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    ChangeAssert assertion = assertThat(changes).change();
    ChangeColumnAssert changeColumnAssert = assertion.column();

    assertThat(changeColumnAssert).isSameAs(assertion.columnAmongTheModifiedOnes(0)).isSameAs(changeColumnAssert.columnAmongTheModifiedOnes(
            0));
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is less than the
   * minimum.
   * <p>
   * Message :
   * </p>
   * <p/>
   * <pre>
   * Index -1 out of the limits of the modified columns [0, 1[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_throw_AssertJDBException_because_index_is_less_than_the_minimum() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).changeOfModification().columnAmongTheModifiedOnes(-1);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is less than the
   * minimum.
   * <p>
   * Message :
   * </p>
   * <p/>
   * <pre>
   * Index 4 out of the limits of the modified columns [0, 1[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_throw_AssertJDBException_because_index_is_more_than_the_maximum() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).changeOfModification().columnAmongTheModifiedOnes(4);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because of reading after the last change.
   * <p>
   * Message :
   * </p>
   * <p/>
   * <pre>
   * No more modified columns
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_throw_AssertJDBException_because_reading_after_the_end() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).changeOfModification()
                       .columnAmongTheModifiedOnes()
                       .columnAmongTheModifiedOnes();
  }

  /**
   * This method should throw a {@code NullPointerException}, because the column name in parameter is null.
   */
  @Test(expected = NullPointerException.class)
  @NeedReload
  public void should_throw_NullPointerException_because_column_name_is_null() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).change().columnAmongTheModifiedOnes(null);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the column name in parameter don't exist in the list
   * of columns.
   * <p>
   * Message :
   * </p>
   *
   * <pre>
   * Column &lt;notexist> do not exist among the modified columns
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  @NeedReload
  public void should_throw_AssertJDBException_because_column_dont_exist() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();
    assertThat(changes).change().columnAmongTheModifiedOnes("notexist");
  }
}
