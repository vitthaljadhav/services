package com.info.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.info.dao.EmployeeDao;
import com.info.exception.ErrorCode;
import com.info.exception.UserNotFoundException;
import com.info.model.Employee;

@RestController
@RequestMapping("empapi")
public class EmployeeController {
	@Autowired

	private EmployeeDao dao;

//empapi/emps
	@GetMapping(value = "/emps")
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> empls = dao.findAll();
		return new ResponseEntity<List<Employee>>(empls, HttpStatus.OK);
	}

	@GetMapping(value = "/emps/{empId}")
	public ResponseEntity<Employee> findOne(@PathVariable Integer empId) {
		Employee employee = dao.findOne(empId);
		if (employee != null) {
			return new ResponseEntity<Employee>(HttpStatus.OK);
		} else {
			throw new UserNotFoundException("User Not Fount With Provided Id : " + empId);
		}

	}

	@PostMapping(value = "/emps")
	public ResponseEntity<Employee> savedEmp(@Valid @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(dao.saveEmp(employee), HttpStatus.OK);
	}

	@DeleteMapping(value = "/emps/{empId}")
	public String deleteEmp(@PathVariable Integer empId) {
		dao.deleteEmp(empId);
		return "Deleted Successfully";
	}

	/*
	 * @ExceptionHandler(UserNotFoundException.class) public
	 * ResponseEntity<ErrorCode> handleUserNotFOundException(UserNotFoundException
	 * ex) { ErrorCode errorCode = new ErrorCode();
	 * errorCode.setErrorCode(HttpStatus.NOT_FOUND.value());
	 * errorCode.setErrorMessage(ex.getLocalizedMessage()); return new
	 * ResponseEntity<ErrorCode>(errorCode, HttpStatus.OK); }
	 */
}
