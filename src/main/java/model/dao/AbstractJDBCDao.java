package model.dao;


import model.persistance.DataSourceFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJDBCDao<T> implements EntityDao<T> {

    public T getById(String query, StatementMapper<T> statementMapper, EntityMapper<T> mapper) {
        return getT(query, statementMapper, mapper);
    }


    public T getByName(String query, StatementMapper<T> statementMapper, EntityMapper<T> mapper) {
        return getT(query, statementMapper, mapper);
    }

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

    public abstract EntityMapper<T> getMapper();


}