package org.example.connections;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionJDBC {
    final private static String URL = "jdbc:postgresql://localhost:5432/postgres";
    final private static String USER = "postgres";
    final private static String PASSWORD= "postgres";
    private static ComboPooledDataSource cpds;

    private ConnectionJDBC(){

    }

    public static Connection getConnection(){

        if(cpds == null){
            createConnection();
        }

        Connection connection=null;
        try{

            connection = cpds.getConnection();
            connection.setAutoCommit(false);

        }
        catch (SQLException e){
            System.err.println("Error at getting a connection: " + e.getMessage());
        }

        return connection;

    }

    private static void createConnection(){
        cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl( URL );
        cpds.setUser(USER);
        cpds.setPassword(PASSWORD);
        cpds.setInitialPoolSize(5);
        cpds.setMinPoolSize(5);
        cpds.setMaxPoolSize(20);
        cpds.setMaxStatements(10);

    }

    public static void closeConnection(){
        if(cpds != null) {
            cpds.close();
        }

    }
}
