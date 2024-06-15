package net.miaoubich.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.miaoubich.dao.EmployeeRequest;
import net.miaoubich.dao.EmployeeResponse;
import net.miaoubich.event.EmployeeEvent;
import net.miaoubich.exception.EmployeeCustomException;
import net.miaoubich.model.Employee;
import net.miaoubich.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Value("${spring.kafka.template.default-topic}")
	private String topic;
	private final EmployeeRepository employeeRepository;
	private final KafkaTemplate<String, EmployeeEvent> kafkaTemplate;

	@Override
	public EmployeeResponse registerEmployee(EmployeeRequest request) {
		log.info("Before registering a new employee !");

		Employee employee = new Employee();
		BeanUtils.copyProperties(request, employee);
		employeeRepository.save(employee);

		EmployeeEvent event = new EmployeeEvent("Create Employee", employee);
		kafkaTemplate.send(topic, event);

		log.info("Employee registered successfully !");
		
		EmployeeResponse response = new EmployeeResponse();
		BeanUtils.copyProperties(response, employee);
		
		log.info("employee: " + employee);
		log.info("response: " + response);

		return response;
	}

	@Override
	public EmployeeResponse updateEmployee(Integer id, EmployeeRequest request) {
		log.info("Check wehter Employee with empId = " + id + " exist or not");
		Employee empExists = employeeRepository.findById(id).orElseThrow(
				() -> new EmployeeCustomException("Employee Not Found !", "Employee_NOT_FOUND", HttpStatus.NOT_FOUND));

		log.info("The Employee with empId = " + id + " exist, and update process started.");
		empExists.setName(request.getName());
		empExists.setEmail(request.getEmail());
		empExists.setPhoneNumber(request.getPhoneNumber());
		employeeRepository.save(empExists);

		EmployeeEvent event = new EmployeeEvent("Update Employee", empExists);
		kafkaTemplate.send(topic, event);

		log.info("Employee with empId = " + id + " successfully updated !");
		
		EmployeeResponse response = new EmployeeResponse();
		BeanUtils.copyProperties(response, empExists);

		return response;
	}

	@Override
	public void deleteEmployee(Integer id) {
		log.info("Check wehter Employee with empId = " + id + " exist or not");
		Employee empExists = employeeRepository.findById(id).orElseThrow(
				() -> new EmployeeCustomException("Employee Not Found !", "Employee_NOT_FOUND", HttpStatus.NOT_FOUND));

		if (empExists != null) {
			log.info("Delete Employee with empId = " + id + " process started.");
			employeeRepository.deleteById(id);
			log.info("Employee with empId = " + id + " successfully deleted.");

			EmployeeEvent event = new EmployeeEvent("Delete Employee", empExists);
			kafkaTemplate.send(topic, event);
		}
	}
}
