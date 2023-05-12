package com.info.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

import com.info.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	private static List<Employee> empList = new ArrayList<>();
	static {
		empList.add(new Employee(101, "Jack", 10000, "IT"));
		empList.add(new Employee(102, "Adam", 20000, "COMP"));
		empList.add(new Employee(103, "joe", 30000, "Cleark"));

	}

	@Override
	public List<Employee> findAll() {
		return empList;
	}

	@Override
	public Employee findOne(Integer empId) {
		for (Employee employee : empList) {
			if (employee.getEmployeeId() == empId) {
				return employee;
			}
		}
		return null;
	}

	@Override
	public Employee saveEmp(Employee employee) {
		empList.add(employee);
		return employee;
	}

	@Override
	public void deleteEmp(Integer empId) {
		
		CopyOnWriteArrayList<Employee> list = new CopyOnWriteArrayList<>(empList);
		for (Employee employee : list) {
			if (employee.getEmployeeId() == empId) {
				empList.remove(employee);
			}
		}
	}

}
