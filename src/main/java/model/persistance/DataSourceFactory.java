package model.persistance;

import com.impossibl.postgres.jdbc.PGDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public final class DataSourceFactory {

    public static final String DB_PROPERTIES = "/database/db.properties";
    public static final String DB_HOST = "db.host";
    public static final String DB_NAME = "db.name";
    public static final String DB_URL = "db.url";
    public static final String DB_PORT = "db.port";
    public static final String DB_USERNAME = "db.login";
    public static final String DB_PASSWORD = "db.password";


    private static PGDataSource dataSource;

    private DataSourceFactory() {
    }

    static {
        Properties properties = new Properties();
        try {
            properties.load(DataSourceFactory.class.getResourceAsStream(DB_PROPERTIES));
            PGDataSource source = new PGDataSource();

            source.setDatabaseName(properties.getProperty(DB_NAME));
            source.setHost(properties.getProperty(DB_HOST));
            source.setPort(Integer.parseInt(properties.getProperty(DB_PORT)));
            source.setUser(properties.getProperty(DB_USERNAME));
            source.setPassword(properties.getProperty(DB_PASSWORD));
            dataSource = source;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    public static PreparedStatement getPreparedStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }

    public static PreparedStatement getPreparedStatementWithReturning(String query) throws SQLException {
        return getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    }
}