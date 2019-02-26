package com.example.yummy.dao.food;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.model.product.FoodItem;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class FoodItemDaoImpl implements FoodItemDao {

    @Autowired
    private BaseDao baseDao;

    @Override
    public boolean add(FoodItem foodItem) {
        return baseDao.save(foodItem);
    }

    @Override
    public boolean delete(int itemId) {
        return false;
    }

    @Override
    public boolean modify(FoodItem foodItem) {
        return baseDao.update(foodItem);
    }

    @Override
    public FoodItem get(int itemId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        FoodItem foodItem = session.get(FoodItem.class, itemId);
        transaction.commit();
        return foodItem;
    }

    @Override
    public List<FoodItem> getAllByRestaurant(String restaurantId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<FoodItem> query = session.createQuery(
                "from FoodItem f where f.restaurantId = :restaurantId and :endTime <= f.endTime",
                FoodItem.class
        );
        query.setParameter("restaurantId", restaurantId);
        query.setParameter("endTime", new Timestamp(System.currentTimeMillis()));
        List<FoodItem> foodItemList = query.getResultList();
        transaction.commit();
        session.close();
        return foodItemList;
    }

    @Override
    public List<FoodItem> getAllByRestaurant(String restaurantId, Timestamp startTime, Timestamp endTime) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<FoodItem> query = session.createQuery(
                "from FoodItem f where f.restaurantId = :restaurantId " +
                        "and f.startTime >= :startTime and f.endTime <= :endTime",
                FoodItem.class
        );
        query.setParameter("restaurantId", restaurantId);
        List<FoodItem> foodItemList = query.getResultList();
        transaction.commit();
        session.close();
        return foodItemList;
    }

    @Override
    public List<FoodItem> getAllByOrder(int orderId) {
        return null;
    }
}
