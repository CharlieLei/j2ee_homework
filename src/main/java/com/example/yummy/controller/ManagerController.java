package com.example.yummy.controller;

import com.example.yummy.factory.ServiceFactory;
import com.example.yummy.model.statistics.ManagerStatistics;
import com.example.yummy.service.manager.ManagerStatisticsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/manager")
public class ManagerController {

    private ManagerStatisticsService managerStatisticsService = ServiceFactory.getManagerStatisticsService();

    @RequestMapping(value = "/getStatistics", method = RequestMethod.GET)
    public ManagerStatistics getStatistics() {
        return managerStatisticsService.get();
    }
}
