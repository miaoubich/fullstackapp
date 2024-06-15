package net.miaoubich.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.miaoubich.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
