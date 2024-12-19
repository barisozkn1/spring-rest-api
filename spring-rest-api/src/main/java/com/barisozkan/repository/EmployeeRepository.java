package com.barisozkan.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.barisozkan.model.Employee;
import com.barisozkan.model.UpdateEmployeeRequest;

@Repository
public class EmployeeRepository {
	
	@Autowired
	private List<Employee> employeeList;
	
	public List<Employee> getAllEmployeeList(){
		
		return employeeList;
	}
	public Employee getEmployeeById(String id) {
		Employee findEmployee=null;
		for (Employee employee : employeeList) {
			if (id.equals(employee.getId())) {
				findEmployee=employee;
				break;
			}
		}
		return findEmployee;
	}
	public List<Employee> getEmployeeWithParams(String firstName, String lastName){
		List<Employee> employeeWithParams = new ArrayList<>();
		if (firstName==null && lastName==null) {
			return employeeList;
		}
		for (Employee employee : employeeList) {
			if (firstName!=null && lastName!=null) {
				if (employee.getFirstName().equalsIgnoreCase(firstName) && employee.getLastName().equalsIgnoreCase(lastName)) {
					employeeWithParams.add(employee);
				}
			}
			if (firstName!=null && lastName==null) {
				if (employee.getFirstName().equalsIgnoreCase(firstName)) {
					employeeWithParams.add(employee);
				}
			}
			if (lastName!=null && firstName==null) {
				if (employee.getLastName().equalsIgnoreCase(lastName)) {
					employeeWithParams.add(employee);
				}
			}
		}
		return employeeWithParams;
	}
	
	public Employee saveEmployee(Employee newEmployee) {
		employeeList.add(newEmployee);
		return newEmployee;
	}
	
	public boolean deleteEmployee(String id) {
		//delete from Employee WHERE id = id diyebilirdik veritabanı kullansaydık
		Employee deleteEmp=null;
		/*
		 * bir listeyi for ile donerken o listede
		 * modifikasyon yaparsan conquerer exception hatası fırlatır
		 * listeyi dönerken for da islem yaptırtmaz. Ekleme, cıkarma vs.
		 */
		for (Employee employee : employeeList) { //eger for içinde düzenleme yaparsan silme,ekleme vs hata verir. For içinde mudahale  ettirtmiyor.
			if (id.equals(employee.getId())) {
				deleteEmp=employee;
				break;
			}
		}
		if (deleteEmp==null) {
			return false;
		}
		employeeList.remove(deleteEmp);
		return true;
	}
	private Employee findEmpId(String id) {
		Employee findEmployee=null;
		for (Employee employee : employeeList) {
			if (employee.getId().equals(id)) {
				findEmployee=employee;
				break;
			}
		}
		return findEmployee;
	}
	public Employee updateEmployee(String id, UpdateEmployeeRequest request) {
		Employee findEmployee = findEmpId(id);
		if (findEmployee!=null) {
			deleteEmployee(id);
			
			Employee updatedEmployee= new Employee();
			updatedEmployee.setId(id);
			updatedEmployee.setFirstName(request.getFirstName());
			updatedEmployee.setLastName(request.getLastName());
			
			employeeList.add(updatedEmployee);
			
			return updatedEmployee;
		}
		return null;
	}
}
