package com.example.yummy.dao.impl;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.dao.FoodCombinationDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.product.FoodCombination;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class FoodCombinationDaoImpl implements FoodCombinationDao {

    @Autowired
    private BaseDao baseDao = DaoFactory.getBaseDao();

    @Override
    public boolean add(FoodCombination foodCombination) {
        FoodCombination newFoodCombination = new FoodCombination();
        baseDao.save(newFoodCombination);
        foodCombination.setId(newFoodCombination.getId());
        return baseDao.update(foodCombination);
    }

    @Override
    public boolean delete(int combinationId) {
        return false;
    }

    @Override
    public boolean modify(FoodCombination foodCombination) {
        return baseDao.update(foodCombination);
    }

    @Override
    public FoodCombination get(int combinationId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        FoodCombination foodCombination = session.get(FoodCombination.class, combinationId);
        transaction.commit();
        session.close();
        return foodCombination;
    }

    @Override
    public List<FoodCombination> getAllByRestaurant(String restaurantId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<FoodCombination> query = session.createQuery(
                "from FoodCombination f where f.restaurantId = :restaurantId",
                FoodCombination.class
        );
        query.setParameter("restaurantId", restaurantId);
        List<FoodCombination> foodCombinationList = query.getResultList();
        transaction.commit();
        session.close();
        return foodCombinationList;
    }

    @Override
    public List<FoodCombination> getAllByRestaurant(String restaurantId, Timestamp startTime, Timestamp endTime) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<FoodCombination> query = session.createQuery(
                "from FoodCombination f where f.restaurantId = :restaurantId " +
                        "and f.startTime >= :startTime and f.endTime <= :endTime",
                FoodCombination.class
        );
        query.setParameter("restaurantId", restaurantId);
        query.setParameter("startTime", startTime);
        query.setParameter("endTime", endTime);
        List<FoodCombination> foodCombinationList = query.getResultList();
        transaction.commit();
        session.close();
        return foodCombinationList;
    }
}
