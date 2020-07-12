package service;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import models.Employee;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class EmployeeService {

	private Connection connection;

	public EmployeeService(Connection connection) {
		this.connection = requireNonNull(connection, "connection is mandatory");
	}

	public void addEmployee(Employee employee) {
		EmployeeDao employeeDao = new EmployeeDaoImpl(connection);

		employeeDao.add(employee);
	}

	public List<Employee> getAllEmployees() {

		EmployeeDao employeeDao = new EmployeeDaoImpl(connection);

		List<Employee> employees = employeeDao
				.getAll()
				.stream()
				.sorted(Comparator.comparingInt(Employee::getId))
				.collect(Collectors.toList());

		return employees;
	}

	public Employee getOneEmployee(Integer id) {
		EmployeeDao employeeDao = new EmployeeDaoImpl(connection);

		Optional<Employee> optionalEmployee = employeeDao.getById(id);

		if (optionalEmployee.get().getId() != null) {
			return optionalEmployee.get();
		} else {
			throw new NullPointerException("Employee with id " + id + " is missing in DB");
		}
	}

	public void updateOneEmployee(Employee employee) {
		EmployeeDao employeeDaoDao = new EmployeeDaoImpl(connection);
		employeeDaoDao.update(employee);
	}

	public void removeOneEmployee(Integer id) {
		EmployeeDao employeeDao = new EmployeeDaoImpl(connection);
		employeeDao.remove(id);
	}
}
