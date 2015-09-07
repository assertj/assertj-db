/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.database.h2;

import org.assertj.db.common.NeedReload;
import org.assertj.db.type.DateTimeValue;
import org.assertj.db.type.DateValue;
import org.assertj.db.type.Table;
import org.assertj.db.type.TimeValue;
import org.junit.Test;

import java.util.Locale;
import java.util.UUID;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.bytesContentFromClassPathOf;

/**
 * Test on the H2 database.
 *
 * @author Régis Pouiller
 */
public class H2Database_Test extends AbstractH2Test {

  // TODO The idea is to create test methods for each assertion methods
  // which it is necessary to implement when support a new SQL type :
  // - isEqualTo() for :
  //    * value of table, request and change
  // - isOfType() for :
  //    * value from table, request and change
  //    * column from table, request and change
  // - hasValues() for :
  //    * column from table, request and change
  // - ...

  // From Régis to Otoniel : note that org.h2.jdbc.JdbcClob extends java.sql.Clob
  // and org.h2.jdbc.JdbcBlob extends java.sql.Blob

  @Test
  @NeedReload
  public void test_is_equal_to() {
    Table table = new Table(source, "test");

    assertThat(table).row()
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
                     .value("var46")//.isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))  // BLOB (org.h2.jdbc.JdbcBlob) is not implemented
                     .value("var47")//.isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))  // BLOB (org.h2.jdbc.JdbcBlob) is not implemented
                     .value("var48")//.isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))  // BLOB (org.h2.jdbc.JdbcBlob) is not implemented
                     .value("var49")//.isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))  // BLOB (org.h2.jdbc.JdbcBlob) is not implemented
                     .value("var50")//.isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))  // BLOB (org.h2.jdbc.JdbcBlob) is not implemented
                     .value("var51")//.isEqualTo(bytesContentFromClassPathOf("h2-logo-2.png"))  // BLOB (org.h2.jdbc.JdbcBlob) is not implemented
                     .value("var52")//.isEqualTo("32")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
                     .value("var53")//.isEqualTo("33")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
                     .value("var54")//.isEqualTo("34")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
                     .value("var55")//.isEqualTo("35")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
                     .value("var56")//.isEqualTo("36")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
                     .value("var57")//.isEqualTo("37")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
                     .value("var58")//.isEqualTo("38")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
                     .value("var59").isEqualTo(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))
                     .value("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
                     .value("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;
  }

  @Test
  @NeedReload
  public void test_column_has_values() {
    Table table = new Table(source, "test");

    assertThat(table).column("var1").hasValues(1)
                     .column("var2").hasValues(2)
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
            .column("var46")//.hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))  // BLOB (org.h2.jdbc.JdbcBlob) is not implemented
            .column("var47")//.hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))  // BLOB (org.h2.jdbc.JdbcBlob) is not implemented
            .column("var48")//.hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))  // BLOB (org.h2.jdbc.JdbcBlob) is not implemented
            .column("var49")//.hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))  // BLOB (org.h2.jdbc.JdbcBlob) is not implemented
            .column("var50")//.hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))  // BLOB (org.h2.jdbc.JdbcBlob) is not implemented
            .column("var51")//.hasValues(bytesContentFromClassPathOf("h2-logo-2.png"))  // BLOB (org.h2.jdbc.JdbcBlob) is not implemented
            .column("var52")//.hasValues("32")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
            .column("var53")//.hasValues("33")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
            .column("var54")//.hasValues("34")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
            .column("var55")//.hasValues("35")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
            .column("var56")//.hasValues("36")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
            .column("var57")//.hasValues("37")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
            .column("var58")//.hasValues("38")  // CLOB (org.h2.jdbc.JdbcClob) is not implemented
            .column("var59").hasValues(UUID.fromString("30B443AE-C0C9-4790-9BEC-CE1380808435"))
            .column("var60")  // ARRAY is not implemented (no idea of the goal so wait a issue from user)
            .column("var61")  // GEOMETRY is not implemented (no idea of the goal so wait a issue from user)
    ;
  }
}
