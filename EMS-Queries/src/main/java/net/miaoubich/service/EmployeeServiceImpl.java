package net.miaoubich.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.miaoubich.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private final EmployeeRepository employeeRepository;
}
