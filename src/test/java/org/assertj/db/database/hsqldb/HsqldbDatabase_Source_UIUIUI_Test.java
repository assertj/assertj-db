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
 * Copyright 2015-2024 the original author or authors.
 */
package org.assertj.db.database.hsqldb;

import org.assertj.db.common.NeedReload;
import org.assertj.db.type.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Locale;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;

/**
 * Test on the HSQLDB database.
 *
 * @author Régis Pouiller
 */
public class HsqldbDatabase_Source_UIUIUI_Test extends AbstractHsqldbTest {

  private Source source = sourceUIUIUI;

  @Before
  public void init() {
    source = sourceUIUIUI;
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
            .column().hasColumnName("var21")
            .column().hasColumnName("var22")
            .column().hasColumnName("var23")
            .column().hasColumnName("var24")
            .column().hasColumnName("var25")
            .column().hasColumnName("var26")
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
            .column("var2").isOfClass(Integer.class, false)
            .column("var3").isOfClass(Double.class, false)
            .column("var4").isOfClass(Double.class, false)
            .column("var5").isOfClass(String.class, false)
            .column("var6").isOfClass(String.class, false)
            .column("var7").isOfClass(String.class, false)
            .column("var8").isOfClass(String.class, false)
            .column("var9").isOfClass(String.class, false)
            .column("var10").isOfClass(Date.class, false)
            .column("var11").isOfClass(Time.class, false)
            .column("var12").isOfClass(Timestamp.class, false)
            .column("var13").isOfClass(Timestamp.class, false)
            .column("var14").isOfClass(BigDecimal.class, false)
            .column("var15").isOfClass(BigDecimal.class, false)
            .column("var16").isOfClass(Boolean.class, false)
            .column("var17").isOfClass(Boolean.class, false)
            .column("var18").isOfClass(Integer.class, false)
            .column("var19").isOfClass(Integer.class, false)
            .column("var20").isOfClass(Long.class, false)
            .column("var21").isOfClass(Double.class, false)
            .column("var22").isOfClass(byte[].class, false)
            .column("var23").isOfClass(byte[].class, false)
            .column("var24").isOfClass(byte[].class, false)
            .column("var25").isOfClass(Locale.class, false)
            .column("var26").isOfClass(Locale.class, false)
    ;

    assertThat(changes).change()
                       .column("var1").isOfClass(Integer.class, false)
                       .column("var2").isOfClass(Integer.class, false)
                       .column("var3").isOfClass(Double.class, false)
                       .column("var4").isOfClass(Double.class, false)
                       .column("var5").isOfClass(String.class, false)
                       .column("var6").isOfClass(String.class, false)
                       .column("var7").isOfClass(String.class, false)
                       .column("var8").isOfClass(String.class, false)
                       .column("var9").isOfClass(String.class, false)
                       .column("var10").isOfClass(Date.class, false)
                       .column("var11").isOfClass(Time.class, false)
                       .column("var12").isOfClass(Timestamp.class, false)
                       .column("var13").isOfClass(Timestamp.class, false)
                       .column("var14").isOfClass(BigDecimal.class, false)
                       .column("var15").isOfClass(BigDecimal.class, false)
                       .column("var16").isOfClass(Boolean.class, false)
                       .column("var17").isOfClass(Boolean.class, false)
                       .column("var18").isOfClass(Integer.class, false)
                       .column("var19").isOfClass(Integer.class, false)
                       .column("var20").isOfClass(Long.class, false)
                       .column("var21").isOfClass(Double.class, false)
                       .column("var22").isOfClass(byte[].class, false)
                       .column("var23").isOfClass(byte[].class, false)
                       .column("var24").isOfClass(byte[].class, false)
                       .column("var25").isOfClass(Locale.class, false)
                       .column("var26").isOfClass(Locale.class, false)
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnEquality_hasValues() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column("var1").hasValues(1)
            .column("var2").hasValues(20)
            .column("var3").hasValues(3.3)
            .column("var4").hasValues(4.4)
            .column("var5").hasValues("5")
            .column("var6").hasValues("6")
            .column("var7").hasValues("7")
            .column("var8").hasValues("8")
            .column("var9").hasValues("9")
            .column("var10").hasValues(DateValue.of(2007, 12, 23))
            .column("var11").hasValues(TimeValue.of(9, 1))
            .column("var12").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
            .column("var13").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
            .column("var14").hasValues(10)
            .column("var15").hasValues(11)
            .column("var16").hasValues(true)
            .column("var17").hasValues(false)
            .column("var18").hasValues(12)
            .column("var19").hasValues(13)
            .column("var20").hasValues(14)
            .column("var21").hasValues(15)
            .column("var22").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
            .column("var23").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
            .column("var24").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
            .column("var25").hasValues(Locale.FRENCH)
            .column("var26").hasValues(Locale.FRENCH)
    ;

    assertThat(changes).change()
                       .column("var1").hasValues(1)
                       .column("var2").hasValues(2, 20)
                       .column("var3").hasValues(3.3)
                       .column("var4").hasValues(4.4)
                       .column("var5").hasValues("5")
                       .column("var6").hasValues("6")
                       .column("var7").hasValues("7")
                       .column("var8").hasValues("8")
                       .column("var9").hasValues("9")
                       .column("var10").hasValues(DateValue.of(2007, 12, 23))
                       .column("var11").hasValues(TimeValue.of(9, 1))
                       .column("var12").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var13").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var14").hasValues(10)
                       .column("var15").hasValues(11)
                       .column("var16").hasValues(true)
                       .column("var17").hasValues(false)
                       .column("var18").hasValues(12)
                       .column("var19").hasValues(13)
                       .column("var20").hasValues(14)
                       .column("var21").hasValues(15)
                       .column("var22").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var23").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var24").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var25").hasValues(Locale.FRENCH)
                       .column("var26").hasValues(Locale.FRENCH)
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnEquality_containsValues() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column("var1").containsValues(1)
            .column("var2").containsValues(20)
            .column("var3").containsValues(3.3)
            .column("var4").containsValues(4.4)
            .column("var5").containsValues("5")
            .column("var6").containsValues("6")
            .column("var7").containsValues("7")
            .column("var8").containsValues("8")
            .column("var9").containsValues("9")
            .column("var10").containsValues(DateValue.of(2007, 12, 23))
            .column("var11").containsValues(TimeValue.of(9, 1))
            .column("var12").containsValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
            .column("var13").containsValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
            .column("var14").containsValues(10)
            .column("var15").containsValues(11)
            .column("var16").containsValues(true)
            .column("var17").containsValues(false)
            .column("var18").containsValues(12)
            .column("var19").containsValues(13)
            .column("var20").containsValues(14)
            .column("var21").containsValues(15)
            .column("var22").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
            .column("var23").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
            .column("var24").containsValues(bytesContentFromClassPathOf("h2-logo-2.png"))
            .column("var25").containsValues(Locale.FRENCH)
            .column("var26").containsValues(Locale.FRENCH)
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnType_isOfType() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table)
            .column("var1").isNumber(false).isOfType(ValueType.NUMBER, false)
            .column("var2").isNumber(false).isOfType(ValueType.NUMBER, false)
            .column("var3").isNumber(false).isOfType(ValueType.NUMBER, false)
            .column("var4").isNumber(false).isOfType(ValueType.NUMBER, false)
            .column("var5").isText(false).isOfType(ValueType.TEXT, false)
            .column("var6").isText(false).isOfType(ValueType.TEXT, false)
            .column("var7").isText(false).isOfType(ValueType.TEXT, false)
            .column("var8").isText(false).isOfType(ValueType.TEXT, false)
            .column("var9").isText(false).isOfType(ValueType.TEXT, false)
            .column("var10").isDate(false).isOfType(ValueType.DATE, false)
            .column("var11").isTime(false).isOfType(ValueType.TIME, false)
            .column("var12").isDateTime(false).isOfType(ValueType.DATE_TIME, false)
            .column("var13").isDateTime(false).isOfType(ValueType.DATE_TIME, false)
            .column("var14").isNumber(false).isOfType(ValueType.NUMBER, false)
            .column("var15").isNumber(false).isOfType(ValueType.NUMBER, false)
            .column("var16").isBoolean(false).isOfType(ValueType.BOOLEAN, false)
            .column("var17").isBoolean(false).isOfType(ValueType.BOOLEAN, false)
            .column("var18").isNumber(false).isOfType(ValueType.NUMBER, false)
            .column("var19").isNumber(false).isOfType(ValueType.NUMBER, false)
            .column("var20").isNumber(false).isOfType(ValueType.NUMBER, false)
            .column("var21").isNumber(false).isOfType(ValueType.NUMBER, false)
            .column("var22").isBytes(false).isOfType(ValueType.BYTES, false)
            .column("var23").isBytes(false).isOfType(ValueType.BYTES, false)
            .column("var24").isBytes(false).isOfType(ValueType.BYTES, false)
            .column("var25")
            .column("var26")
    ;

    assertThat(changes).change()
                       .column("var1").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var2").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var3").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var4").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var5").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var6").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var7").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var8").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var9").isText(false).isOfType(ValueType.TEXT, false)
                       .column("var10").isDate(false).isOfType(ValueType.DATE, false)
                       .column("var11").isTime(false).isOfType(ValueType.TIME, false)
                       .column("var12").isDateTime(false).isOfType(ValueType.DATE_TIME, false)
                       .column("var13").isDateTime(false).isOfType(ValueType.DATE_TIME, false)
                       .column("var14").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var15").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var16").isBoolean(false).isOfType(ValueType.BOOLEAN, false)
                       .column("var17").isBoolean(false).isOfType(ValueType.BOOLEAN, false)
                       .column("var18").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var19").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var20").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var21").isNumber(false).isOfType(ValueType.NUMBER, false)
                       .column("var22").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var23").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var24").isBytes(false).isOfType(ValueType.BYTES, false)
                       .column("var25")
                       .column("var26")
    ;
  }

  @Test
  @NeedReload
  public void test_ColumnOfChangeEquality_hasValues() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(changes).change()
                       .column("var1").hasValues(1)
                       .column("var2").hasValues(2, 20)
                       .column("var3").hasValues(3.3)
                       .column("var4").hasValues(4.4)
                       .column("var5").hasValues("5")
                       .column("var6").hasValues("6")
                       .column("var7").hasValues("7")
                       .column("var8").hasValues("8")
                       .column("var9").hasValues("9")
                       .column("var10").hasValues(DateValue.of(2007, 12, 23))
                       .column("var11").hasValues(TimeValue.of(9, 1))
                       .column("var12").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var13").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var14").hasValues(10)
                       .column("var15").hasValues(11)
                       .column("var16").hasValues(true)
                       .column("var17").hasValues(false)
                       .column("var18").hasValues(12)
                       .column("var19").hasValues(13)
                       .column("var20").hasValues(14)
                       .column("var21").hasValues(15)
                       .column("var22").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var23").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var24").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var25").hasValues(Locale.FRENCH)
                       .column("var26").hasValues(Locale.FRENCH)
    ;

    assertThat(changes).change()
                       .column("var1").hasValues(1, 1)
                       .column("var2").hasValues(2, 20)
                       .column("var3").hasValues(3.3, 3.3)
                       .column("var4").hasValues(4.4, 4.4)
                       .column("var5").hasValues("5", "5")
                       .column("var6").hasValues("6", "6")
                       .column("var7").hasValues("7", "7")
                       .column("var8").hasValues("8", "8")
                       .column("var9").hasValues("9", "9")
                       .column("var10").hasValues(DateValue.of(2007, 12, 23), DateValue.of(2007, 12, 23))
                       .column("var11").hasValues(TimeValue.of(9, 1), TimeValue.of(9, 1))
                       .column("var12").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                                  DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var13").hasValues(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                                  DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .column("var14").hasValues(10, 10)
                       .column("var15").hasValues(11, 11)
                       .column("var16").hasValues(true, true)
                       .column("var17").hasValues(false, false)
                       .column("var18").hasValues(12, 12)
                       .column("var19").hasValues(13, 13)
                       .column("var20").hasValues(14, 14)
                       .column("var21").hasValues(15, 15)
                       .column("var22").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var23").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var24").hasValues(bytesContentFromClassPathOf("h2-logo-2.png"),
                                                  bytesContentFromClassPathOf("h2-logo-2.png"))
                       .column("var25").hasValues(Locale.FRENCH,
                                                  Locale.FRENCH)
                       .column("var26").hasValues(Locale.FRENCH,
                                                  Locale.FRENCH)
    ;
  }

  @Test
  @NeedReload
  public void test_RowEquality_hasValues() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
                     .hasValues(1,
                                20,
                                3.3,
                                4.4,
                                "5",
                                "6",
                                "7",
                                "8",
                                "9",
                                DateValue.of(2007, 12, 23),
                                TimeValue.of(9, 1),
                                DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                10,
                                11,
                                true,
                                false,
                                12,
                                13,
                                14,
                                15,
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                bytesContentFromClassPathOf("h2-logo-2.png"),
                                Locale.FRENCH,
                                Locale.FRENCH)
    ;

    assertThat(changes).change().rowAtStartPoint()
                       .hasValues(1,
                                  2,
                                  3.3,
                                  4.4,
                                  "5",
                                  "6",
                                  "7",
                                  "8",
                                  "9",
                                  DateValue.of(2007, 12, 23),
                                  TimeValue.of(9, 1),
                                  DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                  DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)),
                                  10,
                                  11,
                                  true,
                                  false,
                                  12,
                                  13,
                                  14,
                                  15,
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  bytesContentFromClassPathOf("h2-logo-2.png"),
                                  Locale.FRENCH,
                                  Locale.FRENCH)
    ;
  }

  @Test
  @NeedReload
  public void test_ValueClass_isOfClass() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
                     .value("var1").isOfClass(Integer.class)
                     .value("var2").isOfClass(Integer.class)
                     .value("var3").isOfClass(Double.class)
                     .value("var4").isOfClass(Double.class)
                     .value("var5").isOfClass(String.class)
                     .value("var6").isOfClass(String.class)
                     .value("var7").isOfClass(String.class)
                     .value("var8").isOfClass(String.class)
                     .value("var9").isOfClass(String.class)
                     .value("var10").isOfClass(Date.class)
                     .value("var11").isOfClass(Time.class)
                     .value("var12").isOfClass(Timestamp.class)
                     .value("var13").isOfClass(Timestamp.class)
                     .value("var14").isOfClass(BigDecimal.class)
                     .value("var15").isOfClass(BigDecimal.class)
                     .value("var16").isOfClass(Boolean.class)
                     .value("var17").isOfClass(Boolean.class)
                     .value("var18").isOfClass(Integer.class)
                     .value("var19").isOfClass(Integer.class)
                     .value("var20").isOfClass(Long.class)
                     .value("var21").isOfClass(Double.class)
                     .value("var22").isOfClass(byte[].class)
                     .value("var23").isOfClass(byte[].class)
                     .value("var24").isOfClass(byte[].class)
                     .value("var25").isOfClass(Locale.class)
                     .value("var26").isOfClass(Locale.class)
    ;

    assertThat(changes).change().rowAtStartPoint()
                       .value("var1").isOfClass(Integer.class)
                       .value("var2").isOfClass(Integer.class)
                       .value("var3").isOfClass(Double.class)
                       .value("var4").isOfClass(Double.class)
                       .value("var5").isOfClass(String.class)
                       .value("var6").isOfClass(String.class)
                       .value("var7").isOfClass(String.class)
                       .value("var8").isOfClass(String.class)
                       .value("var9").isOfClass(String.class)
                       .value("var10").isOfClass(Date.class)
                       .value("var11").isOfClass(Time.class)
                       .value("var12").isOfClass(Timestamp.class)
                       .value("var13").isOfClass(Timestamp.class)
                       .value("var14").isOfClass(BigDecimal.class)
                       .value("var15").isOfClass(BigDecimal.class)
                       .value("var16").isOfClass(Boolean.class)
                       .value("var17").isOfClass(Boolean.class)
                       .value("var18").isOfClass(Integer.class)
                       .value("var19").isOfClass(Integer.class)
                       .value("var20").isOfClass(Long.class)
                       .value("var21").isOfClass(Double.class)
                       .value("var22").isOfClass(byte[].class)
                       .value("var23").isOfClass(byte[].class)
                       .value("var24").isOfClass(byte[].class)
                       .value("var25").isOfClass(Locale.class)
                       .value("var26").isOfClass(Locale.class)
    ;
  }

  @Test
  @NeedReload
  public void test_ValueEquality_isEqualTo() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
                     .value("var1").isEqualTo(1)
                     .value("var2").isEqualTo(20)
                     .value("var3").isEqualTo(3.3)
                     .value("var4").isEqualTo(4.4)
                     .value("var5").isEqualTo("5")
                     .value("var6").isEqualTo("6")
                     .value("var7").isEqualTo("7")
                     .value("var8").isEqualTo("8")
                     .value("var9").isEqualTo("9")
                     .value("var10").isEqualTo(DateValue.of(2007, 12, 23))
                     .value("var11").isEqualTo(TimeValue.of(9, 1))
                     .value("var12").isEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                     .value("var13").isEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                     .value("var14").isEqualTo(10)
                     .value("var15").isEqualTo(11)
                     .value("var16").isEqualTo(true)
                     .value("var17").isEqualTo(false)
                     .value("var18").isEqualTo(12)
                     .value("var19").isEqualTo(13)
                     .value("var20").isEqualTo(14)
                     .value("var21").isEqualTo(15)
                     .value("var22").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var23").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var24").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                     .value("var25").isEqualTo(Locale.FRENCH)
                     .value("var26").isEqualTo(Locale.FRENCH)
    ;

    assertThat(changes).change().rowAtStartPoint()
                       .value("var1").isEqualTo(1)
                       .value("var2").isEqualTo(2)
                       .value("var3").isEqualTo(3.3)
                       .value("var4").isEqualTo(4.4)
                       .value("var5").isEqualTo("5")
                       .value("var6").isEqualTo("6")
                       .value("var7").isEqualTo("7")
                       .value("var8").isEqualTo("8")
                       .value("var9").isEqualTo("9")
                       .value("var10").isEqualTo(DateValue.of(2007, 12, 23))
                       .value("var11").isEqualTo(TimeValue.of(9, 1))
                       .value("var12").isEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .value("var13").isEqualTo(DateTimeValue.of(DateValue.of(2007, 12, 23), TimeValue.of(9, 1)))
                       .value("var14").isEqualTo(10)
                       .value("var15").isEqualTo(11)
                       .value("var16").isEqualTo(true)
                       .value("var17").isEqualTo(false)
                       .value("var18").isEqualTo(12)
                       .value("var19").isEqualTo(13)
                       .value("var20").isEqualTo(14)
                       .value("var21").isEqualTo(15)
                       .value("var22").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var23").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var24").isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))
                       .value("var25").isEqualTo(Locale.FRENCH)
                       .value("var26").isEqualTo(Locale.FRENCH)
    ;
  }

  @Test
  @NeedReload
  public void test_ValueNonEquality_isNotEqualTo() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
                     .value("var1").isNotEqualTo(10)
                     .value("var2").isNotEqualTo(200)
                     .value("var3").isNotEqualTo(30.3)
                     .value("var4").isNotEqualTo(40.4)
                     .value("var5").isNotEqualTo("50")
                     .value("var6").isNotEqualTo("60")
                     .value("var7").isNotEqualTo("70")
                     .value("var8").isNotEqualTo("80")
                     .value("var9").isNotEqualTo("90")
                     .value("var10").isNotEqualTo(DateValue.of(2006, 12, 23))
                     .value("var11").isNotEqualTo(TimeValue.of(9, 10))
                     .value("var12").isNotEqualTo(DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1)))
                     .value("var13").isNotEqualTo(DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1)))
                     .value("var14").isNotEqualTo(100)
                     .value("var15").isNotEqualTo(110)
                     .value("var16").isNotEqualTo(false)
                     .value("var17").isNotEqualTo(true)
                     .value("var18").isNotEqualTo(120)
                     .value("var19").isNotEqualTo(130)
                     .value("var20").isNotEqualTo(140)
                     .value("var21").isNotEqualTo(150)
                     .value("var22").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var23").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var24").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                     .value("var25").isNotEqualTo(Locale.ENGLISH)
                     .value("var26").isNotEqualTo(Locale.ENGLISH)
    ;

    assertThat(changes).change().rowAtStartPoint()
                       .value("var1").isNotEqualTo(10)
                       .value("var2").isNotEqualTo(200)
                       .value("var3").isNotEqualTo(30.3)
                       .value("var4").isNotEqualTo(40.4)
                       .value("var5").isNotEqualTo("50")
                       .value("var6").isNotEqualTo("60")
                       .value("var7").isNotEqualTo("70")
                       .value("var8").isNotEqualTo("80")
                       .value("var9").isNotEqualTo("90")
                       .value("var10").isNotEqualTo(DateValue.of(2006, 12, 23))
                       .value("var11").isNotEqualTo(TimeValue.of(9, 10))
                       .value("var12").isNotEqualTo(DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1)))
                       .value("var13").isNotEqualTo(DateTimeValue.of(DateValue.of(2006, 12, 23), TimeValue.of(9, 1)))
                       .value("var14").isNotEqualTo(100)
                       .value("var15").isNotEqualTo(110)
                       .value("var16").isNotEqualTo(false)
                       .value("var17").isNotEqualTo(true)
                       .value("var18").isNotEqualTo(120)
                       .value("var19").isNotEqualTo(130)
                       .value("var20").isNotEqualTo(140)
                       .value("var21").isNotEqualTo(150)
                       .value("var22").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var23").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var24").isNotEqualTo(bytesContentFromClassPathOf("logo-dev.jpg"))
                       .value("var25").isNotEqualTo(Locale.ENGLISH)
                       .value("var26").isNotEqualTo(Locale.ENGLISH)
    ;
  }

  @Test
  @NeedReload
  public void test_ValueType_isOfType() {
    Table table = new Table(source, "test");
    Changes changes = new Changes(table).setStartPointNow();
    update();
    changes.setEndPointNow();

    assertThat(table).row()
                     .value("var1").isNumber().isOfType(ValueType.NUMBER)
                     .value("var2").isNumber().isOfType(ValueType.NUMBER)
                     .value("var3").isNumber().isOfType(ValueType.NUMBER)
                     .value("var4").isNumber().isOfType(ValueType.NUMBER)
                     .value("var5").isText().isOfType(ValueType.TEXT)
                     .value("var6").isText().isOfType(ValueType.TEXT)
                     .value("var7").isText().isOfType(ValueType.TEXT)
                     .value("var8").isText().isOfType(ValueType.TEXT)
                     .value("var9").isText().isOfType(ValueType.TEXT)
                     .value("var10").isDate().isOfType(ValueType.DATE)
                     .value("var11").isTime().isOfType(ValueType.TIME)
                     .value("var12").isDateTime().isOfType(ValueType.DATE_TIME)
                     .value("var13").isDateTime().isOfType(ValueType.DATE_TIME)
                     .value("var14").isNumber().isOfType(ValueType.NUMBER)
                     .value("var15").isNumber().isOfType(ValueType.NUMBER)
                     .value("var16").isBoolean().isOfType(ValueType.BOOLEAN)
                     .value("var17").isBoolean().isOfType(ValueType.BOOLEAN)
                     .value("var18").isNumber().isOfType(ValueType.NUMBER)
                     .value("var19").isNumber().isOfType(ValueType.NUMBER)
                     .value("var20").isNumber().isOfType(ValueType.NUMBER)
                     .value("var21").isNumber().isOfType(ValueType.NUMBER)
                     .value("var22").isBytes().isOfType(ValueType.BYTES)
                     .value("var23").isBytes().isOfType(ValueType.BYTES)
                     .value("var24").isBytes().isOfType(ValueType.BYTES)
                     .value("var25")
                     .value("var26")
    ;

    assertThat(changes).change().rowAtStartPoint()
                       .value("var1").isNumber().isOfType(ValueType.NUMBER)
                       .value("var2").isNumber().isOfType(ValueType.NUMBER)
                       .value("var3").isNumber().isOfType(ValueType.NUMBER)
                       .value("var4").isNumber().isOfType(ValueType.NUMBER)
                       .value("var5").isText().isOfType(ValueType.TEXT)
                       .value("var6").isText().isOfType(ValueType.TEXT)
                       .value("var7").isText().isOfType(ValueType.TEXT)
                       .value("var8").isText().isOfType(ValueType.TEXT)
                       .value("var9").isText().isOfType(ValueType.TEXT)
                       .value("var10").isDate().isOfType(ValueType.DATE)
                       .value("var11").isTime().isOfType(ValueType.TIME)
                       .value("var12").isDateTime().isOfType(ValueType.DATE_TIME)
                       .value("var13").isDateTime().isOfType(ValueType.DATE_TIME)
                       .value("var14").isNumber().isOfType(ValueType.NUMBER)
                       .value("var15").isNumber().isOfType(ValueType.NUMBER)
                       .value("var16").isBoolean().isOfType(ValueType.BOOLEAN)
                       .value("var17").isBoolean().isOfType(ValueType.BOOLEAN)
                       .value("var18").isNumber().isOfType(ValueType.NUMBER)
                       .value("var19").isNumber().isOfType(ValueType.NUMBER)
                       .value("var20").isNumber().isOfType(ValueType.NUMBER)
                       .value("var21").isNumber().isOfType(ValueType.NUMBER)
                       .value("var22").isBytes().isOfType(ValueType.BYTES)
                       .value("var23").isBytes().isOfType(ValueType.BYTES)
                       .value("var24").isBytes().isOfType(ValueType.BYTES)
                       .value("var25")
                       .value("var26")
    ;
  }
}
