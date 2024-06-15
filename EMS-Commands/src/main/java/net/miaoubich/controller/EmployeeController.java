package net.miaoubich.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.miaoubich.dao.EmployeeRequest;
import net.miaoubich.dao.EmployeeResponse;
import net.miaoubich.service.EmployeeServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeServiceImpl employeeService;
	
	@PostMapping
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employeeRequest){
		EmployeeResponse newEmployee = employeeService.registerEmployee(employeeRequest);
		return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
	}
	
	@PutMapping("/id")
	public ResponseEntity<?> updateEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeRequest employeeRequest){
		EmployeeResponse editedEmployee = employeeService.updateEmployee(id, employeeRequest);
		return new ResponseEntity<>(editedEmployee, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/id")
	public ResponseEntity<Void> deleteEmployee(Integer id){
		employeeService.deleteEmployee(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
