package com.ll.learn.hibernate.f_session_isolation;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.ll.learn.hibernate.a_xml.User;

/**
 * 事物隔离级别
 * @author Administrator
 *
 */
public class App {

	private static SessionFactory sessionFactory = null;
	
	static{
		sessionFactory = new Configuration()
			.configure()
			.addClass(User.class)
			.buildSessionFactory();
	}
	
	@Test
	public void test1(){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			
			User user = (User) session.get(User.class, 1);
			System.out.println("1----->" + user.getName());
			
			session.refresh(user);
			
//			user = (User) session.get(User.class, 1);
			System.out.println("2----->" + user.getName());
			
		}finally{
			if(session != null){
				session.close();
			}
		}
	}
	
}
