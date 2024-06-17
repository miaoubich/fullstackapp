package net.miaoubich.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.miaoubich.dao.EmployeeResponse;
import net.miaoubich.event.EmployeeEvent;
import net.miaoubich.exception.EmployeeCustomException;
import net.miaoubich.model.Employee;
import net.miaoubich.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeResponse> getEmployees() {
		log.info("Reading the list of employees!");
		List<Employee> employees = employeeRepository.findAll();
		List<EmployeeResponse> responseEmpList = employees.stream()
				                                          .map(emp -> EmployeeResponse.builder()
																					.name(emp.getName())
																					.email(emp.getEmail())
																					.phoneNumber(emp.getPhoneNumber())
																					.build()).toList();
		return responseEmpList;
	}

	@Override
	public EmployeeResponse getEmployeeById(Integer id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(
				() -> new EmployeeCustomException("Employee Not Found !", "Employee_NOT_FOUND", HttpStatus.NOT_FOUND));
		EmployeeResponse responseEmp = EmployeeResponse.builder()
													   .name(employee.getName())
													   .email(employee.getEmail())
													   .phoneNumber(employee.getPhoneNumber())
													   .build();
		
		return responseEmp;
	}
	
	
	@KafkaListener(topics = "employee-topic", groupId = "employee-group1")
	public void processEmployeeEvents(EmployeeEvent event) {
		Employee employee = event.getEmployee();
		String type = event.getType();
		
		switch(type) {
			case "Create Employee":
				employeeRepository.save(employee);
				break;
			case "Update Employee":
				Employee empExist = employeeRepository.findById(employee.getId()).get();
				empExist.setName(employee.getName());
				empExist.setEmail(employee.getEmail());
				empExist.setPhoneNumber(employee.getPhoneNumber());
				employeeRepository.save(empExist);
				break;
			case "Delete Employee":
				employeeRepository.deleteById(employee.getId());
				break;
		}
	}
}
