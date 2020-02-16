package model.service;

import model.domain.entity.Statistic;

import java.util.List;

/**
 * The interface Statistic service.
 */
public interface StatisticService extends Service {
    /**
     * Gets statistic by user.
     *
     * @param userId the user id
     * @return the statistic by user
     */
    List<Statistic> getStatisticByUser(long userId);

    /**
     * Gets pages.
     *
     * @param userId the user id
     * @return the pages
     */
    int getPages(long userId);

    /**
     * Gets statistic by user.
     *
     * @param userId      the user id
     * @param currentPage the current page
     * @return the statistic by user
     */
    List<Statistic> getStatisticByUser(long userId, String currentPage);
}
