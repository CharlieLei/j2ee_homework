package com.example.yummy.util;

import com.example.yummy.model.member.Member;
import com.example.yummy.model.restaurant.Restaurant;
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
				config.addAnnotatedClass(Restaurant.class);

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
