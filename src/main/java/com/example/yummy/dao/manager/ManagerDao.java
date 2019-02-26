package com.example.yummy.dao.manager;

import com.example.yummy.model.manager.Manager;

public interface ManagerDao {
    public boolean isLoginInfoCorrect(String managerId, String password);

    public Manager get(String managerId);
}
