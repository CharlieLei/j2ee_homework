package com.example.yummy.util;

import com.example.yummy.model.manager.Manager;
import com.example.yummy.model.member.Member;
import com.example.yummy.model.member.MemberDeliveryAddress;
import com.example.yummy.model.order.Order;
import com.example.yummy.model.order.OrderFoodCombination;
import com.example.yummy.model.order.OrderFoodItem;
import com.example.yummy.model.product.*;
import com.example.yummy.model.restaurant.Restaurant;
import com.example.yummy.model.restaurant.RestaurantInfoChange;
import com.example.yummy.model.yummyBill.YummyBill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sessionFactory = null;

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
				config.addAnnotatedClass(OrderFoodItem.class);
				config.addAnnotatedClass(OrderFoodCombination.class);

				config.addAnnotatedClass(FoodItem.class);
				config.addAnnotatedClass(FoodCombination.class);
				config.addAnnotatedClass(CombinationItem.class);

				config.addAnnotatedClass(YummyBill.class);

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
