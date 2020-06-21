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

	public Optional<Employee> getOneEmployee(Integer id) {
		EmployeeDao employeeDao = new EmployeeDaoImpl(connection);

		return employeeDao.getById(id);
	}

	public void updateOneEmployee(Employee employee, Integer id) {
		EmployeeDao employeeDaoDao = new EmployeeDaoImpl(connection);
		employeeDaoDao.update(employee, id);
	}

	public void removeOneEmployee(Integer id) {
		EmployeeDao employeeDao = new EmployeeDaoImpl(connection);
		employeeDao.remove(id);
	}
}
