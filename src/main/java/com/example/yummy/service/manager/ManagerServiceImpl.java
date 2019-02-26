package com.example.yummy.service.manager;

import com.example.yummy.dao.manager.ManagerDao;
import com.example.yummy.model.manager.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Override
    public Manager get(String managerId) {
        return managerDao.get(managerId);
    }
}
