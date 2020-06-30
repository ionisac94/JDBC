package dao;

import models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {

	void add(Employee employee);

	List<Employee> getAll();

	Optional<Employee> getById(Integer id);

	void update(Employee employee);

	void remove(Integer employeeId);
}
