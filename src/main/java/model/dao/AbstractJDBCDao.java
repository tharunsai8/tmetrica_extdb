package model.dao;


import model.persistance.DataSourceFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Abstract jdbc dao.
 *
 * @param <T> the type parameter
 */
public abstract class AbstractJDBCDao<T> implements EntityDao<T> {
    /**
     * The constant OBJECT_ON_PAGE.
     */
    protected static final int OBJECT_ON_PAGE = 5;

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
            closeConnection(preparedStatement);
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

    /**
     * Gets all with condition.
     *
     * @param query           the query
     * @param mapper          the mapper
     * @param statementMapper the statement mapper
     * @return the all with condition
     */
    public List<T> getAllWithCondition(String query, EntityMapper<T> mapper, StatementMapper<T> statementMapper) {
        List<T> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = DataSourceFactory.getPreparedStatement(query)) {
            statementMapper.map(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
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

    /**
     * Count pages int.
     *
     * @param query           the query
     * @param statementMapper the statement mapper
     * @return the int
     */
    public int countPages(String query, StatementMapper<T> statementMapper) {
        try (PreparedStatement preparedStatement = DataSourceFactory.getPreparedStatement(query)) {
            statementMapper.map(preparedStatement);
            int result = -1;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result = resultSet.getInt("count");
                }
            }
            closeConnection(preparedStatement);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
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