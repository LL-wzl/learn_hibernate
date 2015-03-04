package com.ll.learn.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.ll.learn.hibernate.a_xml.User;
import com.ll.learn.hibernate.b_annotction.Role;

public class AppTest {

	private static SessionFactory sessionFactory = null;
	
	static{
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	/**
	 * 
	 */
	@Test
	public void saveUser(){
		Session session = null;
		Transaction transaction = null;
		
		try{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			
			User user = new User();
			user.setName("张三");
			
			session.save(user);
			
			transaction.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			if(transaction != null){
				transaction.rollback();
			}
			
			if(session != null){
				session.close();
			}
		}
		
		
	}

	@Test
	public void findUser(){
		Session session = null;
		try{
			session = sessionFactory.openSession();
			User user = (User) session.get(User.class, 1);
			System.out.println(user);
		}catch(Exception e){
			if(session != null){
				session.close();
			}
		}
		
	}
	
	/**
	 * 
	 */
	@Test
	public void saveRole(){
		Session session = null;
		Transaction transaction = null;
		
		try{
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
		
			Role role = new Role();
			role.setName("管理员");
			
			session.save(role);
			transaction.commit();
			
		}catch(Exception e){
			e.printStackTrace();
			if(transaction != null){
				transaction.rollback();
			}
			
		}finally{
			if(session != null){
				session.close();
			}
		}
		
		
	}
	
}
