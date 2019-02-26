package com.example.yummy.controller;

import com.example.yummy.model.manager.Manager;
import com.example.yummy.model.statistics.ManagerStatistics;
import com.example.yummy.service.manager.ManagerService;
import com.example.yummy.service.manager.ManagerStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@CrossOrigin
@RestController
@RequestMapping(value = "/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private ManagerStatisticsService managerStatisticsService;

    @RequestMapping(value = "/getManager", method = RequestMethod.GET)
    public Manager getManager(@RequestParam(value = "managerId") String managerId) {
        return managerService.get(managerId);
    }

    @RequestMapping(value = "/getStatistics", method = RequestMethod.GET)
    public ManagerStatistics getStatistics(@RequestParam(value = "startTime") Timestamp startTime,
                                           @RequestParam(value = "endTime") Timestamp endTime) {
        return managerStatisticsService.get(startTime, endTime);
    }
}
