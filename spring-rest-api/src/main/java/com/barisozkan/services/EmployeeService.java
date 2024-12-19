package com.barisozkan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barisozkan.model.Employee;
import com.barisozkan.model.UpdateEmployeeRequest;
import com.barisozkan.repository.EmployeeRepository;

//bu sınıfta yüzlerce kontrol yapılır.
@Service
public class EmployeeService {
	
	@Autowired //bu anatasyon sayesinde katmanlı mimari gecisi saglanıyor.
	private EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployeeList(){
		return employeeRepository.getAllEmployeeList();
		/*employeeRepository.getAllEmployeeList();
		return null;
		*/
	}
	public Employee getEmployeeById(String id) {
		return employeeRepository.getEmployeeById(id);
	}
	
	public List<Employee> getEmployeeWithParams(String firstName, String lastName){
		return employeeRepository.getEmployeeWithParams(firstName, lastName);
	}
	public Employee saveEmployee(Employee newEmployee) {
		return employeeRepository.saveEmployee(newEmployee);
	}
	public boolean deleteEmployee(String id) {
		return employeeRepository.deleteEmployee(id);
	}
	public Employee updateEmployee(String id, UpdateEmployeeRequest request) {
		return employeeRepository.updateEmployee(id, request);
	}
}
