package net.miaoubich.service;

import net.miaoubich.dao.EmployeeRequest;
import net.miaoubich.dao.EmployeeResponse;

public interface EmployeeService {

	EmployeeResponse registerEmployee(EmployeeRequest request);
		
	EmployeeResponse updateEmployee(Integer id, EmployeeRequest request);
		
		void deleteEmployee(Integer id);
}
