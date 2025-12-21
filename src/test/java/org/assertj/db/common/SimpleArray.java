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
package org.assertj.db.common;


import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * Helper class to build a java.sql.Array without using a DB connection
 */
public class SimpleArray implements Array {
    private final Object[] value;

    public SimpleArray(Object[] value) {
        this.value = value;
    }

    public Object getArray() throws SQLException  {
        return this.value;
    }

    public Object getArray(Map<String, Class<?>> var1) throws SQLException {
        throw new SQLException("Unsupported operation");
    }

    public Object getArray(long var1, int var3) throws SQLException {
        throw new SQLException("Unsupported operation");
    }

    public Object getArray(long var1, int var3, Map<String, Class<?>> var4) throws SQLException {
        throw new SQLException("Unsupported operation");
    }

    public int getBaseType() {
        return 0;
    }

    public String getBaseTypeName() {
        return "ATYPE";
    }

    public ResultSet getResultSet() throws SQLException {
        throw new SQLException("Unsupported operation");
    }

    public ResultSet getResultSet(Map<String, Class<?>> var1) throws SQLException {
        throw new SQLException("Unsupported operation");
    }

    public ResultSet getResultSet(long var1, int var3) throws SQLException {
        throw new SQLException("Unsupported operation");
    }

    public ResultSet getResultSet(long var1, int var3, Map<String, Class<?>> var4) throws SQLException {
        throw new SQLException("Unsupported operation");
    }

    public void free() {
    }
}
