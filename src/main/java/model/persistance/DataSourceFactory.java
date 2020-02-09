package model.persistance;

import com.impossibl.postgres.jdbc.PGDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * The type Data source factory.
 */
public class DataSourceFactory {

    /**
     * The constant DB_PROPERTIES.
     */
    public static final String DB_PROPERTIES = "/database/db.properties";
    /**
     * The constant DB_HOST.
     */
    public static final String DB_HOST = "db.host";
    /**
     * The constant DB_NAME.
     */
    public static final String DB_NAME = "db.name";
    /**
     * The constant DB_URL.
     */
    public static final String DB_URL = "db.url";
    /**
     * The constant DB_PORT.
     */
    public static final String DB_PORT = "db.port";
    /**
     * The constant DB_USERNAME.
     */
    public static final String DB_USERNAME = "db.login";
    /**
     * The constant DB_PASSWORD.
     */
    public static final String DB_PASSWORD = "db.password";

    private static final DataSourceFactory INSTANCE = new DataSourceFactory();

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

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Gets prepared statement.
     *
     * @param query the query
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    public static PreparedStatement getPreparedStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }

    /**
     * Gets prepared statement with returning.
     *
     * @param query the query
     * @return the prepared statement with returning
     * @throws SQLException the sql exception
     */
    public static PreparedStatement getPreparedStatementWithReturning(String query) throws SQLException {
        return getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    }
}