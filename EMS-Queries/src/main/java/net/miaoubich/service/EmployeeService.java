package net.miaoubich.service;

import java.util.List;

import net.miaoubich.dao.EmployeeResponse;

public interface EmployeeService {

	List<EmployeeResponse> getEmployees();
	
	EmployeeResponse getEmployeeById(Integer id);
}
