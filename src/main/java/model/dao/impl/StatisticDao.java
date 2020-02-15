package model.dao.impl;

import model.dao.AbstractJDBCDao;
import model.dao.EntityMapper;
import model.domain.entity.Activity;
import model.domain.entity.Statistic;
import model.domain.enums.ActivityStatus;

import java.time.Duration;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticDao extends AbstractJDBCDao<Statistic> {
    private final String ID = "activity_id";
    private final String NAME = "activity_name";
    private final String STATUS = "activity_status";
    private final String TIME = "time";
    private static ResourceBundle bundle = ResourceBundle.getBundle("database/queries");

    @Override
    public EntityMapper<Statistic> getMapper() {
        return resultSet -> {
            return new Statistic(
                    new Activity(
                            resultSet.getLong(ID),
                            resultSet.getString(NAME),
                            ActivityStatus.valueOf(resultSet.getString(STATUS))), Duration.parse(resultSet.getString(TIME)));
        };
    }

    public List<Statistic> getAllByUser(long userId) {
        return getAllWithCondition(bundle.getString("stats.get"), getMapper(), ps -> {
            ps.setLong(1, userId);
        });
    }

    @Override
    @Deprecated
    @SuppressWarnings("unused")
    public Statistic getById(long id) {
        return null;
    }

    @Override
    @Deprecated
    @SuppressWarnings("unused")
    public List<Statistic> getAll() {
        return null;
    }

    @Override
    @Deprecated
    @SuppressWarnings("unused")
    public boolean create(Statistic entity) {
        return false;
    }

    @Override
    @Deprecated
    @SuppressWarnings("unused")
    public boolean update(Statistic entity) {
        return false;
    }

    @Override
    @Deprecated
    @SuppressWarnings("unused")
    public boolean remove(Statistic entity) {
        return false;
    }
}
