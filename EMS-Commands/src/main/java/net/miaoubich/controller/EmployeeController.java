package net.miaoubich.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import net.miaoubich.service.EmployeeServiceImpl;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeServiceImpl employeeService;
}
