/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2015-2020 the original author or authors.
 */
package org.assertj.db.database.h2;

import org.assertj.core.api.Assertions;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.*;
import org.assertj.db.type.lettercase.CaseComparisons;
import org.assertj.db.type.lettercase.CaseConversions;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.UUID;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;

/**
 * Test on the H2 database.
 *
 * @author Régis Pouiller
 */
public class H2Database_DataSource_UIUIUI_Test extends AbstractH2Test {

  private DataSource dataSource;

  @Before
  public void init() {
    dataSource = dataSourceUIUIUI;
  }

  @Test
  @NeedReload
  public void test_PrimaryKey_hasPksNames() {
    Table table = new Table(dataSource, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(changes).change().hasPksNames("var1")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnName_hasColumnName() {
    Table table = new Table(dataSource, "test");
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
            .column().hasColumnName("var21")
            .column().hasColumnName("var22")
            .column().hasColumnName("var23")
            .column().hasColumnName("var24")
            .column().hasColumnName("var25")
            .column().hasColumnName("var26")
            .column().hasColumnName("var27")
            .column().hasColumnName("var28")
            .column().hasColumnName("var29")
            .column().hasColumnName("var30")
            .column().hasColumnName("var31")
            .column().hasColumnName("var32")
            .column().hasColumnName("var33")
            .column().hasColumnName("var34")
            .column().hasColumnName("var35")
            .column().hasColumnName("var36")
            .column().hasColumnName("var37")
            .column().hasColumnName("var38")
            .column().hasColumnName("var39")
            .column().hasColumnName("var40")
            .column().hasColumnName("var41")
            .column().hasColumnName("var42")
            .column().hasColumnName("var43")
            .column().hasColumnName("var44")
            .column().hasColumnName("var45")
            .column().hasColumnName("var46")
            .column().hasColumnName("var47")
            .column().hasColumnName("var48")
            .column().hasColumnName("var49")
            .column().hasColumnName("var50")
            .column().hasColumnName("var51")
            .column().hasColumnName("var52")
            .column().hasColumnName("var53")
            .column().hasColumnName("var54")
            .column().hasColumnName("var55")
            .column().hasColumnName("var56")
            .column().hasColumnName("var57")
            .column().hasColumnName("var58")
            .column().hasColumnName("var59")
            .column().hasColumnName("var60")
            .column().hasColumnName("var61")
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
                       .column().hasColumnName("var21")
                       .column().hasColumnName("var22")
                       .column().hasColumnName("var23")
                       .column().hasColumnName("var24")
                       .column().hasColumnName("var25")
                       .column().hasColumnName("var26")
                       .column().hasColumnName("var27")
                       .column().hasColumnName("var28")
                       .column().hasColumnName("var29")
                       .column().hasColumnName("var30")
                       .column().hasColumnName("var31")
                       .column().hasColumnName("var32")
                       .column().hasColumnName("var33")
                       .column().hasColumnName("var34")
                       .column().hasColumnName("var35")
                       .column().hasColumnName("var36")
                       .column().hasColumnName("var37")
                       .column().hasColumnName("var38")
                       .column().hasColumnName("var39")
                       .column().hasColumnName("var40")
                       .column().hasColumnName("var41")
                       .column().hasColumnName("var42")
                       .column().hasColumnName("var43")
                       .column().hasColumnName("var44")
                       .column().hasColumnName("var45")
                       .column().hasColumnName("var46")
                       .column().hasColumnName("var47")
                       .column().hasColumnName("var48")
                       .column().hasColumnName("var49")
                       .column().hasColumnName("var50")
                       .column().hasColumnName("var51")
                       .column().hasColumnName("var52")
                       .column().hasColumnName("var53")
                       .column().hasColumnName("var54")
                       .column().hasColumnName("var55")
                       .column().hasColumnName("var56")
                       .column().hasColumnName("var57")
                       .column().hasColumnName("var58")
                       .column().hasColumnName("var59")
                       .column().hasColumnName("var60")
                       .column().hasColumnName("var61")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnClass_isOfClass() {
    Table table = new Table(dataSource, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column("var1").isOfClass(Long.class, false)
            .column("var2").isOfClass(Integer.class, false)
            .column("var3").isOfClass(Integer.class, false)
            .column("var4").isOfClass(Integer.class, false)
            .column("var5").isOfClass(Integer.class, false)
            .column("var6").isOfClass(Integer.class, false)
            .column("var7").isOfClass(Boolean.class, false)
            .column("var8").isOfClass(Boolean.class, false)
            .column("var9").isOfClass(Boolean.class, false)
            .column("var10").isOfClass(Byte.class, false)
            .column("var11").isOfClass(Short.class, false)
            .column("var12").isOfClass(Short.class, false)
            .column("var13").isOfClass(Short.class, false)
            .column("var14").isOfClass(Long.class, false)
            .column("var15").isOfClass(Long.class, false)
            .column("var16").isOfClass(BigDecimal.class, false)
            .column("var17").isOfClass(BigDecimal.class, false)
            .column("var18").isOfClass(BigDecimal.class, false)
            .column("var19").isOfClass(BigDecimal.class, false)
            .column("var20").isOfClass(Double.class, false)
            .column("var21").isOfClass(Double.class, false)
            .column("var22").isOfClass(Double.class, false)
            .column("var23").isOfClass(Float.class, false)
            .column("var24").isOfClass(Float.class, false)
            .column("var25").isOfClass(Time.class, false)
            .column("var26").isOfClass(Date.class, false)
            .column("var27").isOfClass(Timestamp.class, false)
            .column("var28").isOfClass(Timestamp.class, false)
            .column("var29").isOfClass(Timestamp.class, false)
            .column("var30").isOfClass(byte[].class, false)
            .column("var31").isOfClass(byte[].class, false)
            .column("var32").isOfClass(byte[].class, false)
            .column("var33").isOfClass(byte[].class, false)
            .column("var34").isOfClass(byte[].class, false)
            .column("var35").isOfClass(Locale.class, false)
            .column("var36").isOfClass(String.class, false)
            .column("var37").isOfClass(String.class, false)
            .column("var38").isOfClass(String.class, false)
            .column("var39").isOfClass(String.class, false)
            .column("var40").isOfClass(String.class, false)
            .column("var41").isOfClass(String.class, false)
            .column("var42").isOfClass(String.class, false)
            .column("var43").isOfClass(String.class, false)
            .column("var44").isOfClass(String.class, false)
            .column("var45").isOfClass(String.class, false)
            .column("var46").isOfClass(byte[].class, false)
            .column("var47").isOfClass(byte[].class, false)
            .column("var48").isOfClass(byte[].class, false)
            .column("var49").isOfClass(byte[].class, false)
            .column("var50").isOfClass(byte[].class, false)
            .column("var51").isOfClass(byte[].class, false)
            .column("var52").isOfClass(String.class, false)
            .column("var53").isOfClass(String.class, false)
            .column("var54").isOfClass(String.class, false)
            .column("var55").isOfClass(String.class, false)
            .column("var56").isOfClass(String.class, false)
            .column("var57").isOfClass(String.class, false)
            .column("var58").isOfClass(String.class, false)
            .column("var59").isOfClass(UUID.class, false)
            .column("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .column("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;

    assertThat(changes).change()
                       .column("var1").isOfClass(Long.class, false)
                       .column("var2").isOfClass(Integer.class, false)
                       .column("var3").isOfClass(Integer.class, false)
                       .column("var4").isOfClass(Integer.class, false)
                       .column("var5").isOfClass(Integer.class, false)
                       .column("var6").isOfClass(Integer.class, false)
                       .column("var7").isOfClass(Boolean.class, false)
                       .column("var8").isOfClass(Boolean.class, false)
                       .column("var9").isOfClass(Boolean.class, false)
                       .column("var10").isOfClass(Byte.class, false)
                       .column("var11").isOfClass(Short.class, false)
                       .column("var12").isOfClass(Short.class, false)
                       .column("var13").isOfClass(Short.class, false)
                       .column("var14").isOfClass(Long.class, false)
                       .column("var15").isOfClass(Long.class, false)
                       .column("var16").isOfClass(BigDecimal.class, false)
                       .column("var17").isOfClass(BigDecimal.class, false)
                       .column("var18").isOfClass(BigDecimal.class, false)
                       .column("var19").isOfClass(BigDecimal.class, false)
                       .column("var20").isOfClass(Double.class, false)
                       .column("var21").isOfClass(Double.class, false)
                       .column("var22").isOfClass(Double.class, false)
                       .column("var23").isOfClass(Float.class, false)
                       .column("var24").isOfClass(Float.class, false)
                       .column("var25").isOfClass(Time.class, false)
                       .column("var26").isOfClass(Date.class, false)
                       .column("var27").isOfClass(Timestamp.class, false)
                       .column("var28").isOfClass(Timestamp.class, false)
                       .column("var29").isOfClass(Timestamp.class, false)
                       .column("var30").isOfClass(byte[].class, false)
                       .column("var31").isOfClass(byte[].class, false)
                       .column("var32").isOfClass(byte[].class, false)
                       .column("var33").isOfClass(byte[].class, false)
                       .column("var34").isOfClass(byte[].class, false)
                       .column("var35").isOfClass(Locale.class, false)
                       .column("var36").isOfClass(String.class, false)
                       .column("var37").isOfClass(String.class, false)
                       .column("var38").isOfClass(String.class, false)
                       .column("var39").isOfClass(String.class, false)
                       .column("var40").isOfClass(String.class, false)
                       .column("var41").isOfClass(String.class, false)
                       .column("var42").isOfClass(String.class, false)
                       .column("var43").isOfClass(String.class, false)
                       .column("var44").isOfClass(String.class, false)
                       .column("var45").isOfClass(String.class, false)
                       .column("var46").isOfClass(byte[].class, false)
                       .column("var47").isOfClass(byte[].class, false)
                       .column("var48").isOfClass(byte[].class, false)
                       .column("var49").isOfClass(byte[].class, false)
                       .column("var50").isOfClass(byte[].class, false)
                       .column("var51").isOfClass(byte[].class, false)
                       .column("var52").isOfClass(String.class, false)
                       .column("var53").isOfClass(String.class, false)
                       .column("var54").isOfClass(String.class, false)
                       .column("var55").isOfClass(String.class, false)
                       .column("var56").isOfClass(String.class, false)
                       .column("var57").isOfClass(String.class, false)
                       .column("var58").isOfClass(String.class, false)
                       .column("var59").isOfClass(UUID.class, false)
            .column("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .column("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnEquality_hasValues() {
    Table table = new Table(dataSource, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).column("var1").hasValues(1)
                     .column("var2").hasValues(20)
                     .column("var3").hasValues(3)
                     .column("var4").hasValues(4)
                     .column("var5").hasValues(5)
                     .column("var6").hasValues(6)
                     .column("var7").hasValues(true)
                     .column("var8").hasValues(false)
                     .column("var9").hasValues(true)
                     .column("var10").hasValues(7)
                     .column("var11").hasValues(8)
                     .column("var12").hasValues(9)
                     .column("var13").hasValues(10)
                     .column("var14").hasValues(11)
                     .column("var15").hasValues(12)
                     .column("var16").hasValues(13.13)
                     .column("var17").hasValues(14.14)
                     .column("var18").hasValues(15.15)
                     .column("var19").hasValues(16.16)
                     .column("var20").hasValues(17.17)
                     .column("var21").hasValues(18.18)
                     .column("var22").hasValues(19.19)
                     .column("var23").hasValues(20.20)
                     .column("var24").hasValues(21.21)
                     .column("var25").hasValues(TimeValue.of(9, 1))
                     .column("var26").hasValues(DateValue.of(2007, 12, 23))
                     .column("var27").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                     .column("var28").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                     .column("var29").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                     .column("var30").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var31").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var32").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var33").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var34").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var35").hasValues(Locale.FRENCH)
                     .column("var36").hasValues("22")
                     .column("var37").hasValues("23")
                     .column("var38").hasValues("24")
                     .column("var39").hasValues("25")
                     .column("var40").hasValues("26")
                     .column("var41").hasValues("27")
                     .column("var42").hasValues("28")
                     .column("var43").hasValues("29")
                     .column("var44").hasValues("30")
                     .column("var45").hasValues("31")
                     .column("var46").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var47").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var48").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var49").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var50").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var51").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var52").hasValues("32")
                     .column("var53").hasValues("33")
                     .column("var54").hasValues("34")
                     .column("var55").hasValues("35")
                     .column("var56").hasValues("36")
                     .column("var57").hasValues("37")
                     .column("var58").hasValues("38")
                     .column("var59").hasValues(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))
            .column("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .column("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;

    assertThat(changes).change()
                       .column("var1").hasValues(1)
                       .column("var2").hasValues(2, 20)
                       .column("var3").hasValues(3)
                       .column("var4").hasValues(4)
                       .column("var5").hasValues(5)
                       .column("var6").hasValues(6)
                       .column("var7").hasValues(true)
                       .column("var8").hasValues(false)
                       .column("var9").hasValues(true)
                       .column("var10").hasValues(7)
                       .column("var11").hasValues(8)
                       .column("var12").hasValues(9)
                       .column("var13").hasValues(10)
                       .column("var14").hasValues(11)
                       .column("var15").hasValues(12)
                       .column("var16").hasValues(13.13)
                       .column("var17").hasValues(14.14)
                       .column("var18").hasValues(15.15)
                       .column("var19").hasValues(16.16)
                       .column("var20").hasValues(17.17)
                       .column("var21").hasValues(18.18)
                       .column("var22").hasValues(19.19)
                       .column("var23").hasValues(20.20)
                       .column("var24").hasValues(21.21)
                       .column("var25").hasValues(TimeValue.of(9, 1))
                       .column("var26").hasValues(DateValue.of(2007, 12, 23))
                       .column("var27").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var28").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var29").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var30").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var31").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var32").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var33").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var34").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var35").hasValues(Locale.FRENCH)
                       .column("var36").hasValues("22")
                       .column("var37").hasValues("23")
                       .column("var38").hasValues("24")
                       .column("var39").hasValues("25")
                       .column("var40").hasValues("26")
                       .column("var41").hasValues("27")
                       .column("var42").hasValues("28")
                       .column("var43").hasValues("29")
                       .column("var44").hasValues("30")
                       .column("var45").hasValues("31")
                       .column("var46").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var47").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var48").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var49").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var50").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var51").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var52").hasValues("32")
                       .column("var53").hasValues("33")
                       .column("var54").hasValues("34")
                       .column("var55").hasValues("35")
                       .column("var56").hasValues("36")
                       .column("var57").hasValues("37")
                       .column("var58").hasValues("38")
                       .column("var59").hasValues(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))
            .column("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .column("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnEquality_containsValues() {
    Table table = new Table(dataSource, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).column("var1").containsValues(1)
                     .column("var2").containsValues(20)
                     .column("var3").containsValues(3)
                     .column("var4").containsValues(4)
                     .column("var5").containsValues(5)
                     .column("var6").containsValues(6)
                     .column("var7").containsValues(true)
                     .column("var8").containsValues(false)
                     .column("var9").containsValues(true)
                     .column("var10").containsValues(7)
                     .column("var11").containsValues(8)
                     .column("var12").containsValues(9)
                     .column("var13").containsValues(10)
                     .column("var14").containsValues(11)
                     .column("var15").containsValues(12)
                     .column("var16").containsValues(13.13)
                     .column("var17").containsValues(14.14)
                     .column("var18").containsValues(15.15)
                     .column("var19").containsValues(16.16)
                     .column("var20").containsValues(17.17)
                     .column("var21").containsValues(18.18)
                     .column("var22").containsValues(19.19)
                     .column("var23").containsValues(20.20)
                     .column("var24").containsValues(21.21)
                     .column("var25").containsValues(TimeValue.of(9, 1))
                     .column("var26").containsValues(DateValue.of(2007, 12, 23))
                     .column("var27").containsValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                     .column("var28").containsValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                     .column("var29").containsValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                     .column("var30").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var31").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var32").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var33").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var34").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var35").containsValues(Locale.FRENCH)
                     .column("var36").containsValues("22")
                     .column("var37").containsValues("23")
                     .column("var38").containsValues("24")
                     .column("var39").containsValues("25")
                     .column("var40").containsValues("26")
                     .column("var41").containsValues("27")
                     .column("var42").containsValues("28")
                     .column("var43").containsValues("29")
                     .column("var44").containsValues("30")
                     .column("var45").containsValues("31")
                     .column("var46").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var47").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var48").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var49").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var50").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var51").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .column("var52").containsValues("32")
                     .column("var53").containsValues("33")
                     .column("var54").containsValues("34")
                     .column("var55").containsValues("35")
                     .column("var56").containsValues("36")
                     .column("var57").containsValues("37")
                     .column("var58").containsValues("38")
                     .column("var59").containsValues(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))
            .column("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .column("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnType_isOfType() {
    Table table = new Table(dataSource, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).column("var1").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var2").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var3").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var4").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var5").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var6").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var7").isBoolean(false).isOfType(ValueType.BOOLEAN, false)
                     .column("var8").isBoolean(false).isOfType(ValueType.BOOLEAN, false)
                     .column("var9").isBoolean(false).isOfType(ValueType.BOOLEAN, false)
                     .column("var10").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var11").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var12").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var13").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var14").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var15").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var16").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var17").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var18").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var19").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var20").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var21").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var22").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var23").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var24").isNumber(false).isOfType(ValueType.NUMBER, false)
                     .column("var25").isTime(false).isOfType(ValueType.TIME, false)
                     .column("var26").isDate(false).isOfType(ValueType.DATE, false)
                     .column("var27").isDateTime(false).isOfType(ValueType.DATE_TIME, false)
                     .column("var28").isDateTime(false).isOfType(ValueType.DATE_TIME, false)
                     .column("var29").isDateTime(false).isOfType(ValueType.DATE_TIME, false)
                     .column("var30").isBytes(false).isOfType(ValueType.BYTES, false)
                     .column("var31").isBytes(false).isOfType(ValueType.BYTES, false)
                     .column("var32").isBytes(false).isOfType(ValueType.BYTES, false)
                     .column("var33").isBytes(false).isOfType(ValueType.BYTES, false)
                     .column("var34").isBytes(false).isOfType(ValueType.BYTES, false)
                     .column("var35")
                     .column("var36").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var37").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var38").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var39").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var40").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var41").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var42").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var43").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var44").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var45").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var46").isBytes(false).isOfType(ValueType.BYTES, false)
                     .column("var47").isBytes(false).isOfType(ValueType.BYTES, false)
                     .column("var48").isBytes(false).isOfType(ValueType.BYTES, false)
                     .column("var49").isBytes(false).isOfType(ValueType.BYTES, false)
                     .column("var50").isBytes(false).isOfType(ValueType.BYTES, false)
                     .column("var51").isBytes(false).isOfType(ValueType.BYTES, false)
                     .column("var52").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var53").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var54").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var55").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var56").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var57").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var58").isText(false).isOfType(ValueType.TEXT, false)
                     .column("var59").isUUID(false).isOfType(ValueType.UUID, false)
            .column("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .column("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;

    assertThat(changes).change()
                       .column("var1").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var2").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var3").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var4").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var5").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var6").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var7").isBoolean(false).isOfType(ValueType.BOOLEAN, false)
                       .column("var8").isBoolean(false).isOfType(ValueType.BOOLEAN, false)
                       .column("var9").isBoolean(false).isOfType(ValueType.BOOLEAN, false)
                       .column("var10").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var11").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var12").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var13").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var14").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var15").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var16").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var17").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var18").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var19").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var20").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var21").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var22").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var23").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var24").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var25").isTime(false).isOfType(ValueType.TIME, false)
                       .column("var26").isDate(false).isOfType(ValueType.DATE, false)
                       .column("var27").isDateTime(false).isOfType(ValueType.DATE_TIME, false)
                       .column("var28").isDateTime(false).isOfType(ValueType.DATE_TIME, false)
                       .column("var29").isDateTime(false).isOfType(ValueType.DATE_TIME, false)
                       .column("var30").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var31").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var32").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var33").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var34").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var35")
                       .column("var36").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var37").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var38").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var39").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var40").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var41").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var42").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var43").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var44").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var45").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var46").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var47").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var48").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var49").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var50").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var51").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var52").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var53").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var54").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var55").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var56").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var57").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var58").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var59").isUUID(false).isOfType(ValueType.UUID, false)
            .column("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .column("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnOfChangeEquality_hasValues() {
    Table table = new Table(dataSource, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(changes).change()
                       .column("var1").hasValues(1)
                       .column("var2").hasValues(2, 20)
                       .column("var3").hasValues(3)
                       .column("var4").hasValues(4)
                       .column("var5").hasValues(5)
                       .column("var6").hasValues(6)
                       .column("var7").hasValues(true)
                       .column("var8").hasValues(false)
                       .column("var9").hasValues(true)
                       .column("var10").hasValues(7)
                       .column("var11").hasValues(8)
                       .column("var12").hasValues(9)
                       .column("var13").hasValues(10)
                       .column("var14").hasValues(11)
                       .column("var15").hasValues(12)
                       .column("var16").hasValues(13.13)
                       .column("var17").hasValues(14.14)
                       .column("var18").hasValues(15.15)
                       .column("var19").hasValues(16.16)
                       .column("var20").hasValues(17.17)
                       .column("var21").hasValues(18.18)
                       .column("var22").hasValues(19.19)
                       .column("var23").hasValues(20.20)
                       .column("var24").hasValues(21.21)
                       .column("var25").hasValues(TimeValue.of(9, 1))
                       .column("var26").hasValues(DateValue.of(2007, 12, 23))
                       .column("var27").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var28").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var29").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var30").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var31").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var32").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var33").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var34").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var35").hasValues(Locale.FRENCH)
                       .column("var36").hasValues("22")
                       .column("var37").hasValues("23")
                       .column("var38").hasValues("24")
                       .column("var39").hasValues("25")
                       .column("var40").hasValues("26")
                       .column("var41").hasValues("27")
                       .column("var42").hasValues("28")
                       .column("var43").hasValues("29")
                       .column("var44").hasValues("30")
                       .column("var45").hasValues("31")
                       .column("var46").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var47").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var48").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var49").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var50").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var51").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var52").hasValues("32")
                       .column("var53").hasValues("33")
                       .column("var54").hasValues("34")
                       .column("var55").hasValues("35")
                       .column("var56").hasValues("36")
                       .column("var57").hasValues("37")
                       .column("var58").hasValues("38")
                       .column("var59").hasValues(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))
            .column("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .column("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;

    assertThat(changes).change()
                       .column("var1").hasValues(1, 1)
                       .column("var2").hasValues(2, 20)
                       .column("var3").hasValues(3, 3)
                       .column("var4").hasValues(4, 4)
                       .column("var5").hasValues(5, 5)
                       .column("var6").hasValues(6, 6)
                       .column("var7").hasValues(true, true)
                       .column("var8").hasValues(false, false)
                       .column("var9").hasValues(true, true)
                       .column("var10").hasValues(7, 7)
                       .column("var11").hasValues(8, 8)
                       .column("var12").hasValues(9, 9)
                       .column("var13").hasValues(10, 10)
                       .column("var14").hasValues(11, 11)
                       .column("var15").hasValues(12, 12)
                       .column("var16").hasValues(13.13, 13.13)
                       .column("var17").hasValues(14.14, 14.14)
                       .column("var18").hasValues(15.15, 15.15)
                       .column("var19").hasValues(16.16, 16.16)
                       .column("var20").hasValues(17.17, 17.17)
                       .column("var21").hasValues(18.18, 18.18)
                       .column("var22").hasValues(19.19, 19.19)
                       .column("var23").hasValues(20.20, 20.20)
                       .column("var24").hasValues(21.21, 21.21)
                       .column("var25").hasValues(TimeValue.of(9, 1), TimeValue.of(9, 1))
                       .column("var26").hasValues(DateValue.of(2007, 12, 23), DateValue.of(2007, 12, 23))
                       .column("var27").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                                  DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var28").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                                  DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var29").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                                  DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var30").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var31").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var32").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var33").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var34").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var35").hasValues(Locale.FRENCH, Locale.FRENCH)
                       .column("var36").hasValues("22", "22")
                       .column("var37").hasValues("23", "23")
                       .column("var38").hasValues("24", "24")
                       .column("var39").hasValues("25", "25")
                       .column("var40").hasValues("26", "26")
                       .column("var41").hasValues("27", "27")
                       .column("var42").hasValues("28", "28")
                       .column("var43").hasValues("29", "29")
                       .column("var44").hasValues("30", "30")
                       .column("var45").hasValues("31", "31")
                       .column("var46").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var47").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var48").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var49").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var50").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var51").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var52").hasValues("32", "32")
                       .column("var53").hasValues("33", "33")
                       .column("var54").hasValues("34", "34")
                       .column("var55").hasValues("35", "35")
                       .column("var56").hasValues("36", "36")
                       .column("var57").hasValues("37", "37")
                       .column("var58").hasValues("38", "38")
                       .column("var59").hasValues(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                                  UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))
            .column("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .column("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;
  }

  @Test
  @NeedReload
  public void test_RowEquality_hasValues() {
    Table table = new Table(dataSource, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
                     .hasValues(1,
                                20,
                                3,
                                4,
                                5,
                                6,
                                true,
                                false,
                                true,
                                7,
                                8,
                                9,
                                10,
                                11,
                                12,
                                13.13,
                                14.14,
                                15.15,
                                16.16,
                                17.17,
                                18.18,
                                19.19,
                                20.20,
                                21.21,
                                TimeValue.of(9, 1),
                                DateValue.of(2007, 12, 23),
                                DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                Locale.FRENCH,
                                "22",
                                "23",
                                "24",
                                "25",
                                "26",
                                "27",
                                "28",
                                "29",
                                "30",
                                "31",
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                "32",
                                "33",
                                "34",
                                "35",
                                "36",
                                "37",
                                "38",
                                UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                null,
                                null)
    ;

    assertThat(changes).change().rowAtStartPoint()
                       .hasValues(1,
                                  2,
                                  3,
                                  4,
                                  5,
                                  6,
                                  true,
                                  false,
                                  true,
                                  7,
                                  8,
                                  9,
                                  10,
                                  11,
                                  12,
                                  13.13,
                                  14.14,
                                  15.15,
                                  16.16,
                                  17.17,
                                  18.18,
                                  19.19,
                                  20.20,
                                  21.21,
                                  TimeValue.of(9, 1),
                                  DateValue.of(2007, 12, 23),
                                  DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                  DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                  DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  Locale.FRENCH,
                                  "22",
                                  "23",
                                  "24",
                                  "25",
                                  "26",
                                  "27",
                                  "28",
                                  "29",
                                  "30",
                                  "31",
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  "32",
                                  "33",
                                  "34",
                                  "35",
                                  "36",
                                  "37",
                                  "38",
                                  UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"),
                                  null,
                                  null)
    ;
  }

  @Test
  @NeedReload
  public void test_ValueClass_isOfClass() {
    Table table = new Table(dataSource, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
                     .value("var1").isOfClass(Long.class)
                     .value("var2").isOfClass(Integer.class)
                     .value("var3").isOfClass(Integer.class)
                     .value("var4").isOfClass(Integer.class)
                     .value("var5").isOfClass(Integer.class)
                     .value("var6").isOfClass(Integer.class)
                     .value("var7").isOfClass(Boolean.class)
                     .value("var8").isOfClass(Boolean.class)
                     .value("var9").isOfClass(Boolean.class)
                     .value("var10").isOfClass(Byte.class)
                     .value("var11").isOfClass(Short.class)
                     .value("var12").isOfClass(Short.class)
                     .value("var13").isOfClass(Short.class)
                     .value("var14").isOfClass(Long.class)
                     .value("var15").isOfClass(Long.class)
                     .value("var16").isOfClass(BigDecimal.class)
                     .value("var17").isOfClass(BigDecimal.class)
                     .value("var18").isOfClass(BigDecimal.class)
                     .value("var19").isOfClass(BigDecimal.class)
                     .value("var20").isOfClass(Double.class)
                     .value("var21").isOfClass(Double.class)
                     .value("var22").isOfClass(Double.class)
                     .value("var23").isOfClass(Float.class)
                     .value("var24").isOfClass(Float.class)
                     .value("var25").isOfClass(Time.class)
                     .value("var26").isOfClass(Date.class)
                     .value("var27").isOfClass(Timestamp.class)
                     .value("var28").isOfClass(Timestamp.class)
                     .value("var29").isOfClass(Timestamp.class)
                     .value("var30").isOfClass(byte[].class)
                     .value("var31").isOfClass(byte[].class)
                     .value("var32").isOfClass(byte[].class)
                     .value("var33").isOfClass(byte[].class)
                     .value("var34").isOfClass(byte[].class)
                     .value("var35").isOfClass(Locale.class)
                     .value("var36").isOfClass(String.class)
                     .value("var37").isOfClass(String.class)
                     .value("var38").isOfClass(String.class)
                     .value("var39").isOfClass(String.class)
                     .value("var40").isOfClass(String.class)
                     .value("var41").isOfClass(String.class)
                     .value("var42").isOfClass(String.class)
                     .value("var43").isOfClass(String.class)
                     .value("var44").isOfClass(String.class)
                     .value("var45").isOfClass(String.class)
                     .value("var46").isOfClass(byte[].class)
                     .value("var47").isOfClass(byte[].class)
                     .value("var48").isOfClass(byte[].class)
                     .value("var49").isOfClass(byte[].class)
                     .value("var50").isOfClass(byte[].class)
                     .value("var51").isOfClass(byte[].class)
                     .value("var52").isOfClass(String.class)
                     .value("var53").isOfClass(String.class)
                     .value("var54").isOfClass(String.class)
                     .value("var55").isOfClass(String.class)
                     .value("var56").isOfClass(String.class)
                     .value("var57").isOfClass(String.class)
                     .value("var58").isOfClass(String.class)
                     .value("var59").isOfClass(UUID.class)
            .value("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .value("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;

    assertThat(changes).change().rowAtStartPoint()
                       .value("var1").isOfClass(Long.class)
                       .value("var2").isOfClass(Integer.class)
                       .value("var3").isOfClass(Integer.class)
                       .value("var4").isOfClass(Integer.class)
                       .value("var5").isOfClass(Integer.class)
                       .value("var6").isOfClass(Integer.class)
                       .value("var7").isOfClass(Boolean.class)
                       .value("var8").isOfClass(Boolean.class)
                       .value("var9").isOfClass(Boolean.class)
                       .value("var10").isOfClass(Byte.class)
                       .value("var11").isOfClass(Short.class)
                       .value("var12").isOfClass(Short.class)
                       .value("var13").isOfClass(Short.class)
                       .value("var14").isOfClass(Long.class)
                       .value("var15").isOfClass(Long.class)
                       .value("var16").isOfClass(BigDecimal.class)
                       .value("var17").isOfClass(BigDecimal.class)
                       .value("var18").isOfClass(BigDecimal.class)
                       .value("var19").isOfClass(BigDecimal.class)
                       .value("var20").isOfClass(Double.class)
                       .value("var21").isOfClass(Double.class)
                       .value("var22").isOfClass(Double.class)
                       .value("var23").isOfClass(Float.class)
                       .value("var24").isOfClass(Float.class)
                       .value("var25").isOfClass(Time.class)
                       .value("var26").isOfClass(Date.class)
                       .value("var27").isOfClass(Timestamp.class)
                       .value("var28").isOfClass(Timestamp.class)
                       .value("var29").isOfClass(Timestamp.class)
                       .value("var30").isOfClass(byte[].class)
                       .value("var31").isOfClass(byte[].class)
                       .value("var32").isOfClass(byte[].class)
                       .value("var33").isOfClass(byte[].class)
                       .value("var34").isOfClass(byte[].class)
                       .value("var35").isOfClass(Locale.class)
                       .value("var36").isOfClass(String.class)
                       .value("var37").isOfClass(String.class)
                       .value("var38").isOfClass(String.class)
                       .value("var39").isOfClass(String.class)
                       .value("var40").isOfClass(String.class)
                       .value("var41").isOfClass(String.class)
                       .value("var42").isOfClass(String.class)
                       .value("var43").isOfClass(String.class)
                       .value("var44").isOfClass(String.class)
                       .value("var45").isOfClass(String.class)
                       .value("var46").isOfClass(byte[].class)
                       .value("var47").isOfClass(byte[].class)
                       .value("var48").isOfClass(byte[].class)
                       .value("var49").isOfClass(byte[].class)
                       .value("var50").isOfClass(byte[].class)
                       .value("var51").isOfClass(byte[].class)
                       .value("var52").isOfClass(String.class)
                       .value("var53").isOfClass(String.class)
                       .value("var54").isOfClass(String.class)
                       .value("var55").isOfClass(String.class)
                       .value("var56").isOfClass(String.class)
                       .value("var57").isOfClass(String.class)
                       .value("var58").isOfClass(String.class)
                       .value("var59").isOfClass(UUID.class)
            .value("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .value("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;
  }

  @Test
  @NeedReload
  public void test_ValueEquality_isEqualTo() {
    Table table = new Table(dataSource, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
                     .value("var1").isEqualTo(1)
                     .value("var2").isEqualTo(20)
                     .value("var3").isEqualTo(3)
                     .value("var4").isEqualTo(4)
                     .value("var5").isEqualTo(5)
                     .value("var6").isEqualTo(6)
                     .value("var7").isEqualTo(true)
                     .value("var8").isEqualTo(false)
                     .value("var9").isEqualTo(true)
                     .value("var10").isEqualTo(7)
                     .value("var11").isEqualTo(8)
                     .value("var12").isEqualTo(9)
                     .value("var13").isEqualTo(10)
                     .value("var14").isEqualTo(11)
                     .value("var15").isEqualTo(12)
                     .value("var16").isEqualTo(13.13)
                     .value("var17").isEqualTo(14.14)
                     .value("var18").isEqualTo(15.15)
                     .value("var19").isEqualTo(16.16)
                     .value("var20").isEqualTo(17.17)
                     .value("var21").isEqualTo(18.18)
                     .value("var22").isEqualTo(19.19)
                     .value("var23").isEqualTo(20.20)
                     .value("var24").isEqualTo(21.21)
                     .value("var25").isEqualTo(TimeValue.of(9, 1))
                     .value("var26").isEqualTo(DateValue.of(2007, 12, 23))
                     .value("var27").isEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                     .value("var28").isEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                     .value("var29").isEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                     .value("var30").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var31").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var32").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var33").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var34").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var35").isEqualTo(Locale.FRENCH)
                     .value("var36").isEqualTo("22")
                     .value("var37").isEqualTo("23")
                     .value("var38").isEqualTo("24")
                     .value("var39").isEqualTo("25")
                     .value("var40").isEqualTo("26")
                     .value("var41").isEqualTo("27")
                     .value("var42").isEqualTo("28")
                     .value("var43").isEqualTo("29")
                     .value("var44").isEqualTo("30")
                     .value("var45").isEqualTo("31")
                     .value("var46").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var47").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var48").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var49").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var50").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var51").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var52").isEqualTo("32")
                     .value("var53").isEqualTo("33")
                     .value("var54").isEqualTo("34")
                     .value("var55").isEqualTo("35")
                     .value("var56").isEqualTo("36")
                     .value("var57").isEqualTo("37")
                     .value("var58").isEqualTo("38")
                     .value("var59").isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))
            .value("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .value("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;

    assertThat(changes).change().rowAtStartPoint()
                       .value("var1").isEqualTo(1)
                       .value("var2").isEqualTo(2)
                       .value("var3").isEqualTo(3)
                       .value("var4").isEqualTo(4)
                       .value("var5").isEqualTo(5)
                       .value("var6").isEqualTo(6)
                       .value("var7").isEqualTo(true)
                       .value("var8").isEqualTo(false)
                       .value("var9").isEqualTo(true)
                       .value("var10").isEqualTo(7)
                       .value("var11").isEqualTo(8)
                       .value("var12").isEqualTo(9)
                       .value("var13").isEqualTo(10)
                       .value("var14").isEqualTo(11)
                       .value("var15").isEqualTo(12)
                       .value("var16").isEqualTo(13.13)
                       .value("var17").isEqualTo(14.14)
                       .value("var18").isEqualTo(15.15)
                       .value("var19").isEqualTo(16.16)
                       .value("var20").isEqualTo(17.17)
                       .value("var21").isEqualTo(18.18)
                       .value("var22").isEqualTo(19.19)
                       .value("var23").isEqualTo(20.20)
                       .value("var24").isEqualTo(21.21)
                       .value("var25").isEqualTo(TimeValue.of(9, 1))
                       .value("var26").isEqualTo(DateValue.of(2007, 12, 23))
                       .value("var27").isEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .value("var28").isEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .value("var29").isEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .value("var30").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var31").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var32").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var33").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var34").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var35").isEqualTo(Locale.FRENCH)
                       .value("var36").isEqualTo("22")
                       .value("var37").isEqualTo("23")
                       .value("var38").isEqualTo("24")
                       .value("var39").isEqualTo("25")
                       .value("var40").isEqualTo("26")
                       .value("var41").isEqualTo("27")
                       .value("var42").isEqualTo("28")
                       .value("var43").isEqualTo("29")
                       .value("var44").isEqualTo("30")
                       .value("var45").isEqualTo("31")
                       .value("var46").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var47").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var48").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var49").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var50").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var51").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var52").isEqualTo("32")
                       .value("var53").isEqualTo("33")
                       .value("var54").isEqualTo("34")
                       .value("var55").isEqualTo("35")
                       .value("var56").isEqualTo("36")
                       .value("var57").isEqualTo("37")
                       .value("var58").isEqualTo("38")
                       .value("var59").isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))
            .value("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .value("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;
  }

  @Test
  @NeedReload
  public void test_ValueNonEquality_isNotEqualTo() {
    Table table = new Table(dataSource, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
                     .value("var1").isNotEqualTo(10)
                     .value("var2").isNotEqualTo(200)
                     .value("var3").isNotEqualTo(30)
                     .value("var4").isNotEqualTo(40)
                     .value("var5").isNotEqualTo(50)
                     .value("var6").isNotEqualTo(60)
                     .value("var7").isNotEqualTo(false)
                     .value("var8").isNotEqualTo(true)
                     .value("var9").isNotEqualTo(false)
                     .value("var10").isNotEqualTo(70)
                     .value("var11").isNotEqualTo(80)
                     .value("var12").isNotEqualTo(90)
                     .value("var13").isNotEqualTo(100)
                     .value("var14").isNotEqualTo(110)
                     .value("var15").isNotEqualTo(120)
                     .value("var16").isNotEqualTo(130.13)
                     .value("var17").isNotEqualTo(140.14)
                     .value("var18").isNotEqualTo(150.15)
                     .value("var19").isNotEqualTo(160.16)
                     .value("var20").isNotEqualTo(170.17)
                     .value("var21").isNotEqualTo(180.18)
                     .value("var22").isNotEqualTo(190.19)
                     .value("var23").isNotEqualTo(200.20)
                     .value("var24").isNotEqualTo(210.21)
                     .value("var25").isNotEqualTo(TimeValue.of(9, 10))
                     .value("var26").isNotEqualTo(DateValue.of(2006, 12, 23))
                     .value("var27").isNotEqualTo(DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1)))
                     .value("var28").isNotEqualTo(DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1)))
                     .value("var29").isNotEqualTo(DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1)))
                     .value("var30").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var31").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var32").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var33").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var34").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var35").isNotEqualTo(Locale.ENGLISH)
                     .value("var36").isNotEqualTo("220")
                     .value("var37").isNotEqualTo("230")
                     .value("var38").isNotEqualTo("240")
                     .value("var39").isNotEqualTo("250")
                     .value("var40").isNotEqualTo("260")
                     .value("var41").isNotEqualTo("270")
                     .value("var42").isNotEqualTo("280")
                     .value("var43").isNotEqualTo("290")
                     .value("var44").isNotEqualTo("300")
                     .value("var45").isNotEqualTo("310")
                     .value("var46").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var47").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var48").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var49").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var50").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var51").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var52").isNotEqualTo("320")
                     .value("var53").isNotEqualTo("330")
                     .value("var54").isNotEqualTo("340")
                     .value("var55").isNotEqualTo("350")
                     .value("var56").isNotEqualTo("360")
                     .value("var57").isNotEqualTo("370")
                     .value("var58").isNotEqualTo("380")
                     .value("var59").isNotEqualTo(UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"))
            .value("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .value("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;

    assertThat(changes).change().rowAtStartPoint()
                       .value("var1").isNotEqualTo(10)
                       .value("var2").isNotEqualTo(20)
                       .value("var3").isNotEqualTo(30)
                       .value("var4").isNotEqualTo(40)
                       .value("var5").isNotEqualTo(50)
                       .value("var6").isNotEqualTo(60)
                       .value("var7").isNotEqualTo(false)
                       .value("var8").isNotEqualTo(true)
                       .value("var9").isNotEqualTo(false)
                       .value("var10").isNotEqualTo(70)
                       .value("var11").isNotEqualTo(80)
                       .value("var12").isNotEqualTo(90)
                       .value("var13").isNotEqualTo(100)
                       .value("var14").isNotEqualTo(110)
                       .value("var15").isNotEqualTo(120)
                       .value("var16").isNotEqualTo(130.13)
                       .value("var17").isNotEqualTo(140.14)
                       .value("var18").isNotEqualTo(150.15)
                       .value("var19").isNotEqualTo(160.16)
                       .value("var20").isNotEqualTo(170.17)
                       .value("var21").isNotEqualTo(180.18)
                       .value("var22").isNotEqualTo(190.19)
                       .value("var23").isNotEqualTo(200.20)
                       .value("var24").isNotEqualTo(210.21)
                       .value("var25").isNotEqualTo(TimeValue.of(9, 10))
                       .value("var26").isNotEqualTo(DateValue.of(2006, 12, 23))
                       .value("var27").isNotEqualTo(DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1)))
                       .value("var28").isNotEqualTo(DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1)))
                       .value("var29").isNotEqualTo(DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1)))
                       .value("var30").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var31").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var32").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var33").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var34").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var35").isNotEqualTo(Locale.ENGLISH)
                       .value("var36").isNotEqualTo("220")
                       .value("var37").isNotEqualTo("230")
                       .value("var38").isNotEqualTo("240")
                       .value("var39").isNotEqualTo("250")
                       .value("var40").isNotEqualTo("260")
                       .value("var41").isNotEqualTo("270")
                       .value("var42").isNotEqualTo("280")
                       .value("var43").isNotEqualTo("290")
                       .value("var44").isNotEqualTo("300")
                       .value("var45").isNotEqualTo("310")
                       .value("var46").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var47").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var48").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var49").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var50").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var51").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var52").isNotEqualTo("320")
                       .value("var53").isNotEqualTo("330")
                       .value("var54").isNotEqualTo("340")
                       .value("var55").isNotEqualTo("350")
                       .value("var56").isNotEqualTo("360")
                       .value("var57").isNotEqualTo("370")
                       .value("var58").isNotEqualTo("380")
                       .value("var59").isNotEqualTo(UUID.fromString("16319617-AE95-4087-9264-D3D21BF611B6"))
            .value("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .value("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;
  }

  @Test
  @NeedReload
  public void test_ValueType_isOfType() {
    Table table = new Table(dataSource, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
                     .value("var1").isNumber().isOfType(ValueType.NUMBER)
                     .value("var2").isNumber().isOfType(ValueType.NUMBER)
                     .value("var3").isNumber().isOfType(ValueType.NUMBER)
                     .value("var4").isNumber().isOfType(ValueType.NUMBER)
                     .value("var5").isNumber().isOfType(ValueType.NUMBER)
                     .value("var6").isNumber().isOfType(ValueType.NUMBER)
                     .value("var7").isBoolean().isOfType(ValueType.BOOLEAN)
                     .value("var8").isBoolean().isOfType(ValueType.BOOLEAN)
                     .value("var9").isBoolean().isOfType(ValueType.BOOLEAN)
                     .value("var10").isNumber().isOfType(ValueType.NUMBER)
                     .value("var11").isNumber().isOfType(ValueType.NUMBER)
                     .value("var12").isNumber().isOfType(ValueType.NUMBER)
                     .value("var13").isNumber().isOfType(ValueType.NUMBER)
                     .value("var14").isNumber().isOfType(ValueType.NUMBER)
                     .value("var15").isNumber().isOfType(ValueType.NUMBER)
                     .value("var16").isNumber().isOfType(ValueType.NUMBER)
                     .value("var17").isNumber().isOfType(ValueType.NUMBER)
                     .value("var18").isNumber().isOfType(ValueType.NUMBER)
                     .value("var19").isNumber().isOfType(ValueType.NUMBER)
                     .value("var20").isNumber().isOfType(ValueType.NUMBER)
                     .value("var21").isNumber().isOfType(ValueType.NUMBER)
                     .value("var22").isNumber().isOfType(ValueType.NUMBER)
                     .value("var23").isNumber().isOfType(ValueType.NUMBER)
                     .value("var24").isNumber().isOfType(ValueType.NUMBER)
                     .value("var25").isTime().isOfType(ValueType.TIME)
                     .value("var26").isDate().isOfType(ValueType.DATE)
                     .value("var27").isDateTime().isOfType(ValueType.DATE_TIME)
                     .value("var28").isDateTime().isOfType(ValueType.DATE_TIME)
                     .value("var29").isDateTime().isOfType(ValueType.DATE_TIME)
                     .value("var30").isBytes().isOfType(ValueType.BYTES)
                     .value("var31").isBytes().isOfType(ValueType.BYTES)
                     .value("var32").isBytes().isOfType(ValueType.BYTES)
                     .value("var33").isBytes().isOfType(ValueType.BYTES)
                     .value("var34").isBytes().isOfType(ValueType.BYTES)
                     .value("var35")
                     .value("var36").isText().isOfType(ValueType.TEXT)
                     .value("var37").isText().isOfType(ValueType.TEXT)
                     .value("var38").isText().isOfType(ValueType.TEXT)
                     .value("var39").isText().isOfType(ValueType.TEXT)
                     .value("var40").isText().isOfType(ValueType.TEXT)
                     .value("var41").isText().isOfType(ValueType.TEXT)
                     .value("var42").isText().isOfType(ValueType.TEXT)
                     .value("var43").isText().isOfType(ValueType.TEXT)
                     .value("var44").isText().isOfType(ValueType.TEXT)
                     .value("var45").isText().isOfType(ValueType.TEXT)
                     .value("var46").isBytes().isOfType(ValueType.BYTES)
                     .value("var47").isBytes().isOfType(ValueType.BYTES)
                     .value("var48").isBytes().isOfType(ValueType.BYTES)
                     .value("var49").isBytes().isOfType(ValueType.BYTES)
                     .value("var50").isBytes().isOfType(ValueType.BYTES)
                     .value("var51").isBytes().isOfType(ValueType.BYTES)
                     .value("var52").isText().isOfType(ValueType.TEXT)
                     .value("var53").isText().isOfType(ValueType.TEXT)
                     .value("var54").isText().isOfType(ValueType.TEXT)
                     .value("var55").isText().isOfType(ValueType.TEXT)
                     .value("var56").isText().isOfType(ValueType.TEXT)
                     .value("var57").isText().isOfType(ValueType.TEXT)
                     .value("var58").isText().isOfType(ValueType.TEXT)
                     .value("var59").isUUID().isOfType(ValueType.UUID)
            .value("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .value("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;

    assertThat(changes).change().rowAtStartPoint()
                       .value("var1").isNumber().isOfType(ValueType.NUMBER)
                       .value("var2").isNumber().isOfType(ValueType.NUMBER)
                       .value("var3").isNumber().isOfType(ValueType.NUMBER)
                       .value("var4").isNumber().isOfType(ValueType.NUMBER)
                       .value("var5").isNumber().isOfType(ValueType.NUMBER)
                       .value("var6").isNumber().isOfType(ValueType.NUMBER)
                       .value("var7").isBoolean().isOfType(ValueType.BOOLEAN)
                       .value("var8").isBoolean().isOfType(ValueType.BOOLEAN)
                       .value("var9").isBoolean().isOfType(ValueType.BOOLEAN)
                       .value("var10").isNumber().isOfType(ValueType.NUMBER)
                       .value("var11").isNumber().isOfType(ValueType.NUMBER)
                       .value("var12").isNumber().isOfType(ValueType.NUMBER)
                       .value("var13").isNumber().isOfType(ValueType.NUMBER)
                       .value("var14").isNumber().isOfType(ValueType.NUMBER)
                       .value("var15").isNumber().isOfType(ValueType.NUMBER)
                       .value("var16").isNumber().isOfType(ValueType.NUMBER)
                       .value("var17").isNumber().isOfType(ValueType.NUMBER)
                       .value("var18").isNumber().isOfType(ValueType.NUMBER)
                       .value("var19").isNumber().isOfType(ValueType.NUMBER)
                       .value("var20").isNumber().isOfType(ValueType.NUMBER)
                       .value("var21").isNumber().isOfType(ValueType.NUMBER)
                       .value("var22").isNumber().isOfType(ValueType.NUMBER)
                       .value("var23").isNumber().isOfType(ValueType.NUMBER)
                       .value("var24").isNumber().isOfType(ValueType.NUMBER)
                       .value("var25").isTime().isOfType(ValueType.TIME)
                       .value("var26").isDate().isOfType(ValueType.DATE)
                       .value("var27").isDateTime().isOfType(ValueType.DATE_TIME)
                       .value("var28").isDateTime().isOfType(ValueType.DATE_TIME)
                       .value("var29").isDateTime().isOfType(ValueType.DATE_TIME)
                       .value("var30").isBytes().isOfType(ValueType.BYTES)
                       .value("var31").isBytes().isOfType(ValueType.BYTES)
                       .value("var32").isBytes().isOfType(ValueType.BYTES)
                       .value("var33").isBytes().isOfType(ValueType.BYTES)
                       .value("var34").isBytes().isOfType(ValueType.BYTES)
                       .value("var35")
                       .value("var36").isText().isOfType(ValueType.TEXT)
                       .value("var37").isText().isOfType(ValueType.TEXT)
                       .value("var38").isText().isOfType(ValueType.TEXT)
                       .value("var39").isText().isOfType(ValueType.TEXT)
                       .value("var40").isText().isOfType(ValueType.TEXT)
                       .value("var41").isText().isOfType(ValueType.TEXT)
                       .value("var42").isText().isOfType(ValueType.TEXT)
                       .value("var43").isText().isOfType(ValueType.TEXT)
                       .value("var44").isText().isOfType(ValueType.TEXT)
                       .value("var45").isText().isOfType(ValueType.TEXT)
                       .value("var46").isBytes().isOfType(ValueType.BYTES)
                       .value("var47").isBytes().isOfType(ValueType.BYTES)
                       .value("var48").isBytes().isOfType(ValueType.BYTES)
                       .value("var49").isBytes().isOfType(ValueType.BYTES)
                       .value("var50").isBytes().isOfType(ValueType.BYTES)
                       .value("var51").isBytes().isOfType(ValueType.BYTES)
                       .value("var52").isText().isOfType(ValueType.TEXT)
                       .value("var53").isText().isOfType(ValueType.TEXT)
                       .value("var54").isText().isOfType(ValueType.TEXT)
                       .value("var55").isText().isOfType(ValueType.TEXT)
                       .value("var56").isText().isOfType(ValueType.TEXT)
                       .value("var57").isText().isOfType(ValueType.TEXT)
                       .value("var58").isText().isOfType(ValueType.TEXT)
                       .value("var59").isUUID().isOfType(ValueType.UUID)
            .value("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .value("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;
  }

  @Test
  @NeedReload
  public void test_getTableLetterCase() {
    DataSourceWithLetterCase datasourceWithLetterCase = (DataSourceWithLetterCase) dataSource;

    Table table = new Table(dataSource, "test");

    Request request = new Request(dataSource, "select * from test");

    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();
    Change change = changes.getChangesList().get(0);


    Assertions.assertThat(datasourceWithLetterCase.getTableLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());

    Assertions.assertThat(table.getTableLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());

    Assertions.assertThat(request.getTableLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());

    Assertions.assertThat(changes.getTableLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(change.getTableLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());


    Assertions.assertThat(datasourceWithLetterCase.getTableLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());

    Assertions.assertThat(table.getTableLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());

    Assertions.assertThat(request.getTableLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());

    Assertions.assertThat(changes.getTableLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(change.getTableLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
  }

  @Test
  @NeedReload
  public void test_getColumnLetterCase() {
    DataSourceWithLetterCase datasourceWithLetterCase = (DataSourceWithLetterCase) dataSource;

    Table table = new Table(dataSource, "test");
    Row tableRow = table.getRow(0);
    Column tableColumn = table.getColumn(0);
    Value tableRowValue = tableRow.getColumnValue(0);
    Value tableColumnValue = tableColumn.getRowValue(0);

    Request request = new Request(dataSource, "select * from test");
    Row requestRow = request.getRow(0);
    Column requestColumn = request.getColumn(0);
    Value requestRowValue = requestRow.getColumnValue(0);
    Value requestColumnValue = requestColumn.getRowValue(0);

    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();
    Change change = changes.getChangesList().get(0);
    Row rowAtStartPoint = change.getRowAtStartPoint();
    Row rowAtEndPoint = change.getRowAtEndPoint();
    Value valueAtStartPoint = rowAtStartPoint.getColumnValue(0);
    Value valueAtEndPoint = rowAtEndPoint.getColumnValue(0);


    Assertions.assertThat(datasourceWithLetterCase.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());

    Assertions.assertThat(table.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(tableRow.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(tableColumn.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(tableRowValue.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(tableColumnValue.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());

    Assertions.assertThat(request.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(requestRow.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(requestColumn.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(requestRowValue.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(requestColumnValue.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());

    Assertions.assertThat(changes.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(change.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(rowAtStartPoint.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(rowAtEndPoint.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(valueAtStartPoint.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(valueAtEndPoint.getColumnLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());


    Assertions.assertThat(datasourceWithLetterCase.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());

    Assertions.assertThat(table.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(tableRow.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(tableColumn.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(tableRowValue.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(tableColumnValue.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());

    Assertions.assertThat(request.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(requestRow.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(requestColumn.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(requestRowValue.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(requestColumnValue.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());

    Assertions.assertThat(changes.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(change.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(rowAtStartPoint.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(rowAtEndPoint.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(valueAtStartPoint.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(valueAtEndPoint.getColumnLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
  }

  @Test
  @NeedReload
  public void test_getPrimaryKeyLetterCase() {
    DataSourceWithLetterCase datasourceWithLetterCase = (DataSourceWithLetterCase) dataSource;

    Table table = new Table(dataSource, "test");
    Row tableRow = table.getRow(0);

    Request request = new Request(dataSource, "select * from test");
    Row requestRow = request.getRow(0);

    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();
    Change change = changes.getChangesList().get(0);
    Row rowAtStartPoint = change.getRowAtStartPoint();
    Row rowAtEndPoint = change.getRowAtEndPoint();


    Assertions.assertThat(datasourceWithLetterCase.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());

    Assertions.assertThat(table.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(tableRow.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());

    Assertions.assertThat(request.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(requestRow.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());

    Assertions.assertThat(changes.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(change.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(rowAtStartPoint.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());
    Assertions.assertThat(rowAtEndPoint.getPrimaryKeyLetterCase().getConversionName()).isSameAs(CaseConversions.UPPER.getConversionName());


    Assertions.assertThat(datasourceWithLetterCase.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());

    Assertions.assertThat(table.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(tableRow.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());

    Assertions.assertThat(request.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(requestRow.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());

    Assertions.assertThat(changes.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(change.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(rowAtStartPoint.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
    Assertions.assertThat(rowAtEndPoint.getPrimaryKeyLetterCase().getComparisonName()).isSameAs(CaseComparisons.IGNORE.getComparisonName());
  }
}
