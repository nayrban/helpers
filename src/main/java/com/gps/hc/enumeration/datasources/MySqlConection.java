package com.gps.hc.enumeration.datasources;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

class MySqlConection {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private static final String CONNECTION_STRING = "jdbc:mysql://localhost/";

    private static final String DEFAULT_DATABASE = "hc_drools_poc";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASS = "admin1234";

    private void loadClass() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }

    public List<String> executeQuery(String query) {
        return executeQuery(DEFAULT_DATABASE, DEFAULT_USER, DEFAULT_PASS, query);
    }

    public List<String> executeQuery(String dataBase, String username, String password, String query) {
        List<String> values = new ArrayList<>();
        try {
            loadClass();
            connect = DriverManager
                    .getConnection(CONNECTION_STRING + dataBase + "?user=" + username + "&password=" + password);
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            values = getResut(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return values;
    }


    private List<String> getResut(ResultSet resultSet) throws SQLException {
        List<String> values = new ArrayList<>();
        String pair = "%s=%s";
        while (resultSet.next()) {
            String value = String.format(Locale.getDefault(), pair, UUID.randomUUID(), resultSet.getString(1));
            values.add(value);
        }
        return values;
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
            //TODO LOG
        }
    }
}
