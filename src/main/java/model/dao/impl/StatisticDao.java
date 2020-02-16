package model.dao.impl;

import model.dao.AbstractJDBCDao;
import model.dao.EntityMapper;
import model.domain.entity.Activity;
import model.domain.entity.Statistic;
import model.domain.enums.ActivityStatus;

import java.time.Duration;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The type Statistic dao.
 */
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
                    Activity.newBuilder()
                            .setName(resultSet.getString(NAME))
                            .setStatus(ActivityStatus.valueOf(resultSet.getString(STATUS)))
                            .setId(resultSet.getLong(ID))
                            .build(), Duration.parse(resultSet.getString(TIME)));
        };
    }

    /**
     * Gets all by user.
     *
     * @param userId the user id
     * @return the all by user
     */
    public List<Statistic> getAllByUser(long userId) {
        return getAllWithCondition(bundle.getString("stats.get"), getMapper(), ps -> {
            ps.setLong(1, userId);
        });
    }


    /**
     * Gets all by user pages.
     *
     * @param userId the user id
     * @return the all by user pages
     */
    public int getAllByUserPages(long userId) {
        return countPages(bundle.getString("stats.count"), ps -> {
            ps.setLong(1, userId);
        }) / OBJECT_ON_PAGE + 1;
    }

    /**
     * Gets all by user.
     *
     * @param userId      the user id
     * @param currentPage the current page
     * @return the all by user
     */
    public List<Statistic> getAllByUser(long userId, String currentPage) {
        int currentPageInt = currentPage != null ? Integer.parseInt(currentPage) : 1;
        currentPageInt = currentPageInt <= 0 ? 0 : (currentPageInt - 1) * OBJECT_ON_PAGE;
        int offset = currentPageInt;
        return getAllWithCondition(bundle.getString("stats.get.pages"), getMapper(), ps -> {
            ps.setLong(1, userId);
            ps.setInt(2, OBJECT_ON_PAGE);
            ps.setInt(3, offset);

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


