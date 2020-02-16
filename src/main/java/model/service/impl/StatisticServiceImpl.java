package model.service.impl;

import model.dao.impl.StatisticDao;
import model.domain.entity.Statistic;
import model.service.StatisticService;

import java.util.List;

public class StatisticServiceImpl implements StatisticService {
    private StatisticDao statisticDao;

    public StatisticServiceImpl() {
        statisticDao = new StatisticDao();
    }

    @Override
    public List<Statistic> getStatisticByUser(long userId) {
        return statisticDao.getAllByUser(userId);
    }

    @Override
    public int getPages(long userId) {
        return statisticDao.getAllByUserPages(userId);
    }

    @Override
    public List<Statistic> getStatisticByUser(long userId, String currentPage) {
        return statisticDao.getAllByUser(userId, currentPage);
    }
}
