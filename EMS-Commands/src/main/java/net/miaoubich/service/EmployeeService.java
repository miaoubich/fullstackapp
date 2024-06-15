package net.miaoubich.service;

import net.miaoubich.dao.EmployeeDao;
import net.miaoubich.model.Employee;

public interface EmployeeService {

		Employee registerEmployee(EmployeeDao request);
		
		Employee updateEmployee(Integer id, EmployeeDao request);
		
		void deleteEmployee(Integer id);
}
