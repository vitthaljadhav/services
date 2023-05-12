package com.info.dao;

import java.util.List;

import com.info.model.Employee;

public interface EmployeeDao {

	public List<Employee> findAll();
	
	public Employee findOne(Integer empId);
	
	public Employee saveEmp(Employee employee);
	
	public void deleteEmp(Integer empId);
}
