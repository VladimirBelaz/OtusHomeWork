package db.dao;

import db.utils.ConnectionManager;
import java.util.*;


public abstract class AbsTable {
    protected String tableName;
    protected Map<String, String> columns = new HashMap<>();
    protected ConnectionManager connectionManager;

    public AbsTable(String tableName) {
        this.tableName = tableName;
        this.connectionManager = ConnectionManager.getInstance();
    }

    public void create() {
        StringBuilder sqlBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sqlBuilder.append(tableName).append(" (");

        for (Map.Entry<String, String> entry : columns.entrySet()) {
            sqlBuilder.append(entry.getKey()).append(" ").append(entry.getValue()).append(", ");
        }

        // Удаляем последнюю запятую и пробел
        sqlBuilder.setLength(sqlBuilder.length() - 2);
        sqlBuilder.append(")");

        String sql = sqlBuilder.toString();
        System.out.println("Создание таблицы: " + sql);
        connectionManager.executeUpdate(sql);
    }
}