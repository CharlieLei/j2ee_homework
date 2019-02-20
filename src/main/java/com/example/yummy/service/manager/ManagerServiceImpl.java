package com.example.yummy.service.manager;

import com.example.yummy.dao.ManagerDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.manager.Manager;

public class ManagerServiceImpl implements ManagerService {

    private ManagerDao managerDao = DaoFactory.getManagerDao();

    @Override
    public Manager get(String managerId) {
        return managerDao.get(managerId);
    }
}
