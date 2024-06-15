package net.miaoubich.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.miaoubich.dao.EmployeeResponse;
import net.miaoubich.service.EmployeeServiceImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeServiceImpl employeeService;
	
	@GetMapping
	public ResponseEntity<?> getAllEmployees(){
		List<EmployeeResponse> employees = employeeService.getEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<?> getEmployeeById(@PathVariable(name = "id") Integer id){
		EmployeeResponse employee = employeeService.getEmployeeById(id);
		return new ResponseEntity<>(employee, HttpStatus.FOUND);
	}
}
