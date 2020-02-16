package model.dao;


import model.persistance.DataSourceFactory;
import org.apache.log4j.Logger;

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
    private static final Logger LOG = Logger.getLogger(AbstractJDBCDao.class);
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
            preparedStatement.getConnection().setAutoCommit(false);
            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long id = generatedKeys.getLong(1);
                        preparedStatement.getConnection().commit();
                        closeConnection(preparedStatement);
                        return id;
                    }
                }
            }
            preparedStatement.getConnection().commit();
            closeConnection(preparedStatement);
        } catch (SQLException e) {
            LOG.error("Exception while creating model with returning model id", e);
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

            LOG.error("Exception while getting all models", e);
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
            LOG.error("Exception while getting models by condition", e);
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
            LOG.error("Exception while getting model", e);
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
            LOG.error("Exception while creating or updating model", e);
        }

        return false;
    }

    /**
     * Count int.
     *
     * @param query           the query
     * @param statementMapper the statement mapper
     * @return the int
     */
    public int count(String query, StatementMapper<T> statementMapper) {
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
            LOG.error("Exception while trying to count models by condition", e);
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