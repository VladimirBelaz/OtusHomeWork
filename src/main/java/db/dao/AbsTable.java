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
        List<String> columnDefinitions = new ArrayList<>();
        for (Map.Entry<String, String> entry : columns.entrySet()) {
            columnDefinitions.add(entry.getKey() + " " + entry.getValue());
        }

        String columnsString = String.join(", ", columnDefinitions);
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", tableName, columnsString);

        if(connectionManager.executeUpdate(sql) > 0) {
            System.out.println("Создание таблицы: " + sql);
        }
    }
}