package com.example.yummy.util;

import com.example.yummy.model.coupon.Coupon;
import com.example.yummy.model.manager.Manager;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.member.MemberDeliveryAddress;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderItem;
import com.example.yummy.model.product.Product;
import com.example.yummy.model.product.ProductItem;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfoChange;
import com.example.yummy.model.yummyBill.YummyBill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

//	@Autowired
//	public HibernateUtil(SessionFactory sessionFactory) {
//		HibernateUtil.sessionFactory = sessionFactory;
//	}

	private static SessionFactory getSessionFactory(){
		 try {
				Configuration config;
				ServiceRegistry serviceRegistry;

				config = new Configuration().configure();

				config.addAnnotatedClass(Member.class);
				config.addAnnotatedClass(MemberDeliveryAddress.class);

				config.addAnnotatedClass(Restaurant.class);
				config.addAnnotatedClass(RestaurantInfoChange.class);

				config.addAnnotatedClass(Manager.class);

				config.addAnnotatedClass(Order.class);
				config.addAnnotatedClass(OrderItem.class);

				config.addAnnotatedClass(Product.class);
				config.addAnnotatedClass(ProductItem.class);

				config.addAnnotatedClass(YummyBill.class);

				config.addAnnotatedClass(Coupon.class);

				serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
				sessionFactory = config.buildSessionFactory(serviceRegistry);
				return sessionFactory;
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	 }

	 public static Session getSession(){
		 return getSessionFactory().getCurrentSession();
	 }
}
