package utils;

import utils.ConfigReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    public static void main(String[] args) {
        fetch("Select * from person;");
    }

    public static List<Map<String, String>> fetch (String query){

        String dbURL = ConfigReader.read("dbURL");
        String userName = ConfigReader.read("dbUserName");
        String password = ConfigReader.read("dbPassword");


        List<Map<String, String>> tableData = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection(dbURL, userName, password);){
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            ResultSetMetaData rsm = rs.getMetaData();
            while (rs.next()){

                Map<String, String> rowMap = new LinkedHashMap<>();
                for (int i = 1; i <= rsm.getColumnCount() ; i++) {
                    String key = rsm.getColumnLabel(i);
                    String value = rs.getString(i);
                    rowMap.put(key,value);
                }
                tableData.add(rowMap);

            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();

        }
        return tableData;
    }
}
