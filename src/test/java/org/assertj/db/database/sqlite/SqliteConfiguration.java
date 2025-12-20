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
 * Copyright 2015-2025 the original author or authors.
 */
package org.assertj.db.database.sqlite;

import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Provides the configuration for the tests.
 *
 * @author Régis Pouiller
 */
@Configuration
public class SqliteConfiguration {

  @Bean
  public DataSource dataSource() {
    BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName("org.sqlite.JDBC");
    dataSource.setUrl("jdbc:sqlite:target/testDerby.db");
    dataSource.setUsername("");
    dataSource.setPassword("");


    try {
      try (Connection connection = dataSource.getConnection();) {
        try (Statement statement = connection.createStatement()) {
          statement.executeUpdate("drop table if exists teSt;");
          statement.executeUpdate("create table teSt ("
            + " Var1 BIGINT PRIMARY KEY,\n"
            + " vAr2 BLOB,\n"
            + " vaR3 CHAR,\n"
            + " var4 CHAR FOR BIT DATA,\n"
            + " var5 CLOB,\n"
            + " var6 DATE,\n"
            + " var7 DECIMAL,\n"
            + " var8 DOUBLE,\n"
            + " var9 DOUBLE PRECISION,\n"
            + " var10 FLOAT,\n"
            + " var11 INTEGER,\n"
            + " var12 LONG VARCHAR,\n"
            + " var13 LONG VARCHAR FOR BIT DATA,\n"
            + " var14 NUMERIC,\n"
            + " var15 REAL,\n"
            + " var16 SMALLINT,\n"
            + " var17 TIME,\n"
            + " var18 TIMESTAMP,\n"
            + " var19 VARCHAR,\n"
            + " var20 VARCHAR FOR BIT DATA"
            + " )");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }


    return dataSource;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }
}
