package com.example.yummy.dao.impl;

import com.example.yummy.dao.BaseDao;
import com.example.yummy.dao.ProductDao;
import com.example.yummy.factory.DaoFactory;
import com.example.yummy.model.product.Product;
import com.example.yummy.model.product.ProductItem;
import com.example.yummy.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private BaseDao baseDao = DaoFactory.getBaseDao();

    @Override
    public boolean add(Product product) {
        Product newProduct = new Product();
        baseDao.save(newProduct);
        product.setId(newProduct.getId());
        return baseDao.update(product);
    }

    @Override
    public boolean delete(int productId) {
        return false;
    }

    @Override
    public boolean modify(Product product) {
        return baseDao.update(product);
    }

    @Override
    public Product get(int productId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Product product = session.get(Product.class, productId);
        transaction.commit();
        return product;
    }

    @Override
    public List<Product> getAllByRestaurant(String restaurantId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<Product> query = session.createQuery(
                "select p from Product p where p.restaurantId = ?1",
                Product.class
        );
        query.setParameter(1, restaurantId);
        List<Product> list = query.getResultList();
        transaction.commit();

        return list;
    }
}
