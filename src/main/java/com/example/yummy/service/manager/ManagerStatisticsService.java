package com.example.yummy.service.manager;

import com.example.yummy.model.statistics.ManagerStatistics;

import java.sql.Timestamp;

public interface ManagerStatisticsService {
    public ManagerStatistics get(Timestamp startTime, Timestamp endTime);
}
