package model.dao;


import model.persistance.DataSourceFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for all JDBC CRUD DAO classes
 *
 * @param <T> the type parameter
 * @author Vladimir Petrenko
 */
public abstract class AbstractJDBCDao<T> implements EntityDao<T> {

    /**
     * Gets by id.
     *
     * @param query           the query
     * @param statementMapper the statement mapper
     * @param mapper          the mapper
     * @return the by id
     */
    public T getById(String query, StatementMapper<T> statementMapper, EntityMapper<T> mapper) {
        return getT(query, statementMapper, mapper);
    }


    /**
     * Gets by name.
     *
     * @param query           the query
     * @param statementMapper the statement mapper
     * @param mapper          the mapper
     * @return the by name
     */
    public T getByName(String query, StatementMapper<T> statementMapper, EntityMapper<T> mapper) {
        return getT(query, statementMapper, mapper);
    }

    /**
     * Create update with return long.
     *
     * @param query           the query
     * @param statementMapper the statement mapper
     * @return the long
     */
    public long createUpdateWithReturn(String query, StatementMapper<T> statementMapper) {
        try (PreparedStatement preparedStatement = DataSourceFactory.getPreparedStatementWithReturning(query)) {
            statementMapper.map(preparedStatement);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return (generatedKeys.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Gets all.
     *
     * @param query  the query
     * @param mapper the mapper
     * @return the all
     */
    public List<T> getAll(String query, EntityMapper<T> mapper) {
        List<T> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = DataSourceFactory.getPreparedStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                T entity = mapper.map(resultSet);

                result.add(entity);
            }

            closeConnection(preparedStatement);

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return result;
    }

    private T getT(String query, StatementMapper<T> statementMapper, EntityMapper<T> mapper) {
        T result = null;

        try (PreparedStatement preparedStatement = DataSourceFactory.getPreparedStatement(query)) {
            statementMapper.map(preparedStatement);
            ;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result = mapper.map(resultSet);
                }
            }

            closeConnection(preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Create update boolean.
     *
     * @param query           the query
     * @param statementMapper the statement mapper
     * @return the boolean
     */
    public boolean createUpdate(String query, StatementMapper<T> statementMapper) {
        try (PreparedStatement preparedStatement = DataSourceFactory.getPreparedStatement(query)) {
            statementMapper.map(preparedStatement);

            int result = preparedStatement.executeUpdate();
            closeConnection(preparedStatement);

            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void closeConnection(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.getConnection().close();
    }

    /**
     * Gets mapper.
     *
     * @return the mapper
     */
    public abstract EntityMapper<T> getMapper();
}