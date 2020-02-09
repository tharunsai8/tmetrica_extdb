package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The interface Entity mapper.
 *
 * @param <T> the type parameter
 */
@FunctionalInterface
public interface EntityMapper<T>{
    /**
     * Map t.
     *
     * @param resultSet the result set
     * @return the t
     * @throws SQLException the sql exception
     */
    T map(ResultSet resultSet) throws SQLException;
}
