package com.home.client;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.home.entities.Address;
import com.home.entities.Employee;
import com.home.util.HibernateUtil;

public class FetchDataUsingHQLClientTest {

	public static void main(String[] args) {
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			//getEmployeeAndAddressByEmployeeId(session);
			//getEmployeeAndAddressByAddressId(session);
			getEmployeeAndAddressFieldsByEmployeeId(session);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void getEmployeeAndAddressByAddressId(Session session) {
		String HQL="from Address addr left join fetch addr.employee where addr.addressId=:addrId";
		Query<Address> query = session.createQuery(HQL, Address.class);
		query.setParameter("addrId", 1);
		Address address = query.getSingleResult();
		System.out.println(address);
		Employee employee = address.getEmployee();
		System.out.println(employee);
		}
	
	private static void getEmployeeAndAddressFieldsByEmployeeId(Session session) {
		int empId=1;
		String HQL="select emp.employeeName,emp.salary,addr.pin from Employee emp left join emp.address addr where emp.employeeId=:empId";
		Query<Object[]> query = session.createQuery(HQL);
		query.setParameter("empId", empId);
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			String employeeName=(String)objects[0];
			Double salary=(Double)objects[1];
			Long pin=(Long)objects[2];
			System.out.println(employeeName+"\t"+salary+"\t"+pin);
		}
		}

	
	  private static void getEmployeeAndAddressByEmployeeId(Session session) { int
	  empId=1; String
	  HQL="from Employee emp left join fetch emp.address where emp.employeeId=:empId"
	  ; Query<Employee> query = session.createQuery(HQL, Employee.class);
	  query.setParameter("empId", empId); Employee employee =
	  query.getSingleResult(); System.out.println(employee); Address address =
	  employee.getAddress(); System.out.println(address); }
	 
}
