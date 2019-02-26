package com.example.yummy.service.manager;

import com.example.yummy.dao.manager.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerAccountServiceImpl implements ManagerAccountService{

    @Autowired
    private ManagerDao managerDao;

    @Override
    public boolean login(String managerId, String password) {
        return managerDao.isLoginInfoCorrect(managerId, password);
    }
}
