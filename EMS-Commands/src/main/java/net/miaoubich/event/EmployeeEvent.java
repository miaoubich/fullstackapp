package net.miaoubich.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.miaoubich.model.Employee;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeEvent {

	private String type;
	private Employee employee;
}
