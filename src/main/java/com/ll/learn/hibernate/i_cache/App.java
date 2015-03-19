package com.ll.learn.hibernate.i_cache;


import java.util.Iterator;
import java.util.List;

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
			session.save(department);			
			session.save(employee1);
			session.save(employee2);
			
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
			/*session.save(employee1);
			session.save(employee2);
			session.save(department);*/
			
			/**
			 * save()        临时状态—>持久化状态  ？？（疑问当ID部位null时会是怎么样的）
			 */
//			Department d = new Department();
//			d.setId(111);
//			d.setName("测试部门");
			
//			session.saveOrUpdate(d);
			
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
	
	/**
	 * 测试 一级缓存不会刷新（session缓存），要手动的session.refresh
	 */
	@Test
	public void testFind1(){
		Session session = null;
		
		try{
			
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			
			Employee employee = (Employee) session.get(Employee.class, 1);
			
			System.out.println(employee);
			
			session.createQuery("update Employee set name=? where id=?")
					.setParameter(0, "王SS")
					.setParameter(1, 1)
					.executeUpdate();

			session.refresh(employee);
			
			System.out.println(employee);
			
			session.getTransaction().commit();
			session.close();
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	@Test
	public void testFind2(){
		Session session = null;
		
		try{
			
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			
//			Employee employee = (Employee) session.get(Employee.class, 1);
			
			 session.createSQLQuery("select * from t_cache_employee where id=?").setParameter(0, 1).list().get(0);
			
//			System.out.println(employee);
			
			Employee employee = (Employee) session.get(Employee.class, 1);
			
			System.out.println(employee);
			
//			session.createQuery("update Employee set name=? where id=?")
//					.setParameter(0, "王SS")
//					.setParameter(1, 1)
//					.executeUpdate();
//
//			session.refresh(employee);
//			
//			System.out.println(employee);
			
			session.getTransaction().commit();
			session.close();
			
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
	
	/**
	 * 二级缓存  类的缓存
	 */
	@Test
	public void testFind3(){
		Session session1 = null;
		
		try{
			
			session1 = sessionFactory.openSession();
			session1.getTransaction().begin();
			
			Employee employee1 = (Employee) session1.get(Employee.class, 1);
			
			System.out.println(employee1);
			
			session1.getTransaction().commit();
			session1.close();
			
			System.out.println("\n\n-----------------------------------------\n\n");
			
			Session session2 = sessionFactory.openSession();
			session2.getTransaction().begin();
			
			Employee employee2 = (Employee) session2.get(Employee.class, 1);
			
			System.out.println(employee2);
			
			session2.getTransaction().commit();
			session2.close();
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 二级缓存  集合缓存
	 */
	@Test
	public void testFind4(){
		Session session1 = null;
		
		try{
			
			session1 = sessionFactory.openSession();
			session1.getTransaction().begin();
			
			Department department = (Department) session1.get(Department.class, 1);
			Iterator<Employee> it = department.getEmployees().iterator();
			while(it.hasNext()){
				Employee employee = it.next();
				System.out.println(employee);
			}
			
			session1.getTransaction().commit();
			session1.close();
			
			System.out.println("\n\n-----------------------------------------\n\n");
			
			Session session2 = sessionFactory.openSession();
			session2.getTransaction().begin();
			
			/*不会发送语句
			Department department2 = (Department) session2.get(Department.class, 1);
			Iterator<Employee> it2 = department2.getEmployees().iterator();
			while(it2.hasNext()){
				Employee employee = it2.next();
				System.out.println(employee);
			}*/ 
			
			/*//当用HQL直接查询 一个类时  
			Department department3 = (Department) session2.createQuery("from Department where id =:id").setParameter("id", 1).uniqueResult();
			System.out.println(department3);
			
			//直接使用HQL   没有缓存的话就会N+1
			Iterator<Employee> it2 = session2.createQuery("select e from Employee e where e.department.id=:did").setParameter("did", 1).iterate();
			while(it2.hasNext()){
				Employee employee = it2.next();
				System.out.println(employee);
			}*/
			
			//直接使用list()   这样的查询语句不会生效哦。。。 只有写HQL才能把
			List<Employee> list = session2.createQuery("select e from Employee e where e.department.id=:did").setParameter("did", 1).list();
			for(Employee e : list){
				System.out.println(e);
			}
			
			session2.getTransaction().commit();
			session2.close();
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 二级缓存  查询条件缓存 只对HQL管用   还要启动使用setCacheable
	 */
	@Test
	public void testFind5(){
		Session session1 = null;
		
		try{
			
			session1 = sessionFactory.openSession();
			session1.getTransaction().begin();
			
			List<Employee> employees = session1.createQuery("from Employee where department.id=:dId").setParameter("dId", 1)
											.setCacheable(true).list();
			for(Employee e : employees){
				System.out.println(e);
			}
			
			session1.getTransaction().commit();
			session1.close();
			
			System.out.println("\n\n-----------------------------------------\n\n");
			
			Session session2 = sessionFactory.openSession();
			session2.getTransaction().begin();
			
			/* 语句写法不一致也可以   应该放的是转换后sql当成key
			List<Employee> employees2 = session2.createQuery("select e from Employee e where e.department.id=:dId")
										.setCacheable(true).setParameter("dId", 1).list();
			for(Employee e : employees2){
				System.out.println(e);
			}*/
			
			Employee employee = (Employee) session2.get(Employee.class, 1);
			System.out.println(employee);
			
			session2.getTransaction().commit();
			session2.close();
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 明天任务   1、集合缓存 iterate 会出现的N+1现象  
	 * 		   2、查询条件缓存（验证sql是否可以） list（查询出来的结果会放在缓存中吗？）  若用条件单个的查询某条数据是怎样的呢？
	 * 		   3、修改下数据二级缓存是否会刷新
	 * 		   4、测试缓存是否会放在硬盘上
	 * 		   5、ThreadLoacl   
	 * 
	 * 		private static final ThreadLocal threadSession = new ThreadLocal();   多个线程会new几次  只会实例化一次
	 * 
	 */
}
