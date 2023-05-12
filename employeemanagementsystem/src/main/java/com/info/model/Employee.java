package com.info.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotBlank;
@Entity
@javax.persistence.Table(name = "empTbl")
public class Employee {
	@NotNull(message = "Id Can't be Null")
	private Integer employeeId;
	@Size(min = 3,max=5, message = "Name Minimum of 3 chars")
	private String name;
	private float salary;
	@Size(min = 3, message = "designation Minimum of 3 chars")
	@NotNull(message = "designation Can't be Null")
	private String designation;
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Employee(Integer employeeId, String name, float salary, String designation) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.salary = salary;
		this.designation = designation;
	}
	public Employee() {
		super();
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", salary=" + salary + ", designation="
				+ designation + "]";
	}
	
}
