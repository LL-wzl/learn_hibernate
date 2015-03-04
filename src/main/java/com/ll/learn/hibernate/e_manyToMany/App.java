package com.ll.learn.hibernate.e_manyToMany;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


public class App {

	private static SessionFactory sessionFactory = null;
	
	static{
		sessionFactory = new Configuration()
			.configure()
			.addClass(Teacher.class)
			.addClass(Student.class)
			.buildSessionFactory();
	}
	
	/**
	 * 测试set集合
	 */
	@Test
	public void testSave(){
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			
			Teacher teacher1 = new Teacher();
			teacher1.setName("苍老师");
			
			Teacher teacher2 = new Teacher();
			teacher2.setName("陈老师");
			
			Student student1 = new Student();
			student1.setName("张三");
			student1.getTeachers().add(teacher1);
			student1.getTeachers().add(teacher2);
			
			Student student2 = new Student();
			student2.setName("王五");
			student2.getTeachers().add(teacher1);
			student2.getTeachers().add(teacher2);
			
			session.save(teacher1);
			session.save(teacher2);
			session.save(student1);
			session.save(student2);
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session != null){
				session.close();
			}
		}
		
	}
	
	@Test
	public void testFind(){
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			
			
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session != null){
				session.close();
			}
		}
	}
	
	@Test
	public void testRemoveRelation(){
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session != null){
				session.close();
			}
		}
	}
	
	@Test
	public void testDelete(){
		Session session = null;
		
		try{
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			
			
			
			session.getTransaction().commit();
			
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			if(session != null){
				session.close();
			}
		}
	}
	
}
