package org.assertj.db.database.sqlite;

import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Source;
import org.assertj.db.type.Table;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on the Sqlite database.
 *
 * @author Régis Pouiller
 */
public class SqliteDatabase_Source_DDD_Test extends AbstractSqliteTest {

  private Source source;

  @Before
  public void init() {
    source = sourceDDD;
  }

  @Test
  @NeedReload
  public void test_PrimaryKey_hasPksNames() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(changes).change().hasPksNames("var1")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column().hasColumnName("var1")
            .column().hasColumnName("var2")
            .column().hasColumnName("var3")
            .column().hasColumnName("var4")
            .column().hasColumnName("var5")
            .column().hasColumnName("var6")
            .column().hasColumnName("var7")
            .column().hasColumnName("var8")
            .column().hasColumnName("var9")
            .column().hasColumnName("var10")
            .column().hasColumnName("var11")
            .column().hasColumnName("var12")
            .column().hasColumnName("var13")
            .column().hasColumnName("var14")
            .column().hasColumnName("var15")
            .column().hasColumnName("var16")
            .column().hasColumnName("var17")
            .column().hasColumnName("var18")
            .column().hasColumnName("var19")
            .column().hasColumnName("var20")
    ;

    assertThat(changes).change()
                       .column().hasColumnName("var1")
                       .column().hasColumnName("var2")
                       .column().hasColumnName("var3")
                       .column().hasColumnName("var4")
                       .column().hasColumnName("var5")
                       .column().hasColumnName("var6")
                       .column().hasColumnName("var7")
                       .column().hasColumnName("var8")
                       .column().hasColumnName("var9")
                       .column().hasColumnName("var10")
                       .column().hasColumnName("var11")
                       .column().hasColumnName("var12")
                       .column().hasColumnName("var13")
                       .column().hasColumnName("var14")
                       .column().hasColumnName("var15")
                       .column().hasColumnName("var16")
                       .column().hasColumnName("var17")
                       .column().hasColumnName("var18")
                       .column().hasColumnName("var19")
                       .column().hasColumnName("var20")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnClass_isOfClass() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column("var1").isOfClass(Integer.class, false)
            .column("var2").isOfClass(String.class, false)
            .column("var3").isOfClass(String.class, false)
            .column("var4").isOfClass(String.class, false)
            .column("var5").isOfClass(String.class, false)
            .column("var6").isOfClass(Date.class, false)
            .column("var7").isOfClass(Double.class, false)
            .column("var8").isOfClass(Double.class, false)
            .column("var9").isOfClass(Double.class, false)
            .column("var10").isOfClass(Double.class, false)
            .column("var11").isOfClass(Integer.class, false)
            .column("var12").isOfClass(String.class, false)
            .column("var13").isOfClass(String.class, false)
            .column("var14").isOfClass(Integer.class, false)
            .column("var15").isOfClass(Double.class, false)
            .column("var16").isOfClass(Integer.class, false)
            .column("var17").isOfClass(String.class, false)
            .column("var18").isOfClass(String.class, false)
            .column("var19").isOfClass(String.class, false)
            .column("var20").isOfClass(String.class, false)
    ;

    assertThat(changes).change()
                       .column("var1").isOfClass(Integer.class, false)
                       .column("var2").isOfClass(String.class, false)
                       .column("var3").isOfClass(String.class, false)
                       .column("var4").isOfClass(String.class, false)
                       .column("var5").isOfClass(String.class, false)
                       .column("var6").isOfClass(Date.class, false)
                       .column("var7").isOfClass(Double.class, false)
                       .column("var8").isOfClass(Double.class, false)
                       .column("var9").isOfClass(Double.class, false)
                       .column("var10").isOfClass(Double.class, false)
                       .column("var11").isOfClass(Integer.class, false)
                       .column("var12").isOfClass(String.class, false)
                       .column("var13").isOfClass(String.class, false)
                       .column("var14").isOfClass(Integer.class, false)
                       .column("var15").isOfClass(Double.class, false)
                       .column("var16").isOfClass(Integer.class, false)
                       .column("var17").isOfClass(String.class, false)
                       .column("var18").isOfClass(String.class, false)
                       .column("var19").isOfClass(String.class, false)
                       .column("var20").isOfClass(String.class, false)
    ;
  }
}
