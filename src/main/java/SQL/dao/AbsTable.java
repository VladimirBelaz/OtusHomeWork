package SQL.dao;

import SQL.utils.ConnectionManager;

import java.util.HashMap;
import java.util.Map;


public abstract class AbsTable {
    protected String tableName;
    protected Map<String, String> columns = new HashMap<>();

    protected ConnectionManager connectionManager;

    public AbsTable(String tableName) {
        this.tableName = tableName;
        this.connectionManager = ConnectionManager.getInstance();
    }

    public void create() {
        String sqlRequest = String.format("CREATE TABLE IF NOT EXISTS %s (%s)",
                this.tableName, convertMapColumnsToString());
        System.out.println(sqlRequest);
        ConnectionManager.getInstance().executeUpdate(sqlRequest);
    }

    private String convertMapColumnsToString(){
        String result = "";
        for(Map.Entry<String, String> el : columns.entrySet()) {
            result += el.getKey() + ' ' + el.getValue() + ',';
        }
        result = result.substring(0, result.length() - 1);
        return  result;
    }
}
