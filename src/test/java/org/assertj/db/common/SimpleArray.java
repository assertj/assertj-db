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
