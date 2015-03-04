package com.ll.learn.hibernate.d_oneToMany;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;


public class App {

	private static SessionFactory sessionFactory = null;
	
	static{
		sessionFactory = new Configuration()
			.configure()
			.addClass(Department.class)
			.addClass(Employee.class)
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
			
			Department department = new Department();
			department.setName("开发部");
			
			Employee employee1 = new Employee();
			employee1.setName("账上");
			employee1.setDepartment(department);

			Employee employee2 = new Employee();
			employee2.setName("考考");
			employee2.setDepartment(department);
			
			//第一种
//			session.save(department);			
//			session.save(employee1);
//			session.save(employee2);
			
			//第二种
//			department.getEmployees().add(employee1);
//			department.getEmployees().add(employee2);
//			session.save(department);
//			session.save(employee1);
//			session.save(employee2);
//			Hibernate: insert into t_department (name) values (?)
//			Hibernate: insert into t_employee (name, department_id) values (?, ?)
//			Hibernate: insert into t_employee (name, department_id) values (?, ?)
//			Hibernate: update t_employee set department_id=? where id=?
//			Hibernate: update t_employee set department_id=? where id=?
			
			//第三种  inverse true  单项维护
//			department.getEmployees().add(employee1);
//			department.getEmployees().add(employee2);
//			session.save(department);
//			Hibernate: insert into t_department (name) values (?)
			
			//第四种
			session.save(employee1);
			session.save(employee2);
			session.save(department);
			
			
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
			
			Department department = (Department) session.get(Department.class, 1);
			
			System.out.println(department.getName());
			System.out.println(department.getEmployees());
			
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
			
			
			//解除部门方
			Department department = (Department) session.get(Department.class, 1);
			
			//这个就与inverse有关 ，只有为false时 这种操作才有效
			department.getEmployees().clear();
			
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
			
			//删除员工    可以直接删除
//			Employee employee = (Employee) session.get(Employee.class, 8);
//			session.delete(employee);
			
			
			//删除部门
			Department department = (Department) session.get(Department.class, 3);
			session.delete(department);
			
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
