package com.example.yummy.service.manager;

import com.example.yummy.dao.ManagerDao;
import com.example.yummy.factory.DaoFactory;

public class ManagerAccountServiceImpl implements ManagerAccountService{

    private ManagerDao managerDao = DaoFactory.getManagerDao();

    @Override
    public boolean login(String managerId, String password) {
        return managerDao.isLoginInfoCorrect(managerId, password);
    }
}
