package model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The interface Statement mapper.
 *
 * @param <T> the type parameter
 */
@FunctionalInterface
public interface StatementMapper<T>{
    /**
     * Map.
     *
     * @param ps the ps
     * @throws SQLException the sql exception
     */
    void map(PreparedStatement ps) throws SQLException;
}
