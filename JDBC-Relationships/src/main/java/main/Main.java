package main;

import helper.ConnectionUtil;
import models.Address;
import models.EmplProj;
import models.Employee;
import models.Project;
import service.AddressService;
import service.EmplProjService;
import service.EmployeeService;
import service.ProjectService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {


		try (Connection connection = ConnectionUtil.getConnection()) {

//			address
//			Address address = new Address(4, "IR", "Dublin", "Str.Oliver", "a223-1e1");
//			Integer idAddress = 4;

//			addAddress(connection, address);
//			getAllAddresses(connection);
//			getOneAddress(connection, idAddress);
//			updateOneAddress(connection, address, address.getId());
//			removeOneAddress(connection, idAddress);

			//employees
//			Integer id = 1;
//			Employee employee = new Employee(4, "Daniela", "Ciuperca", new Date(1l), 3);

//			addEmployee(connection, employee);
//			getAllEmployees(connection);
//			getOneEmployee(connection, id);
//			updateOneEmployee(connection, employee);
//			removeOneEmployee(connection, employee.getId());

//			emplproj
//			EmplProj emplProj = new EmplProj(2, 2);

//			addEmplProj(connection, emplProj);
//			getAllEmplProj(connection);
//			getEmplProjById(connection);
//			updateEmplProj(connection, emplProj);
//			removeEmplProj(connection, emplProj);

			//project
//			Project project = new Project(3, "Moldcell");

//			addProject(connection, project);
//			getAllProjects(connection);
//			getProjectById(connection, project.getId());
//			updateOneProject(connection, project);
//			removeOneProject(connection, project.getId());

		} catch (SQLException e) {
			System.out.println("Ups :)");
		}

//		----------

//		DatabaseMetaData metaData = connection.getMetaData();
//
//		String databaseProductVersion = metaData.getDatabaseProductVersion();
//		System.out.println("databaseProductVersion: " + databaseProductVersion);
//
//		String driverName = metaData.getDriverName();
//		System.out.println("driverName: " + driverName);
//
//		String driverVersion = metaData.getDriverVersion();
//
//		System.out.println("driverVersion: " + driverVersion);

	}

	//	EmplProj
	private static void removeEmplProj(Connection connection, EmplProj emplProj) {
		EmplProjService emplProjService = new EmplProjService(connection);
		emplProjService.deleteEmplProj(connection, emplProj);
	}

	private static void updateEmplProj(Connection connection, EmplProj emplProj) {
		EmplProjService emplProjService = new EmplProjService(connection);
		emplProjService.updateEmplProj(connection, emplProj);
	}

	private static void getEmplProjById(Connection connection) {
		EmplProjService emplProjService = new EmplProjService(connection);
		Optional<EmplProj> emplProjById = emplProjService.getEmplProjById(1, 1);

		System.out.println(emplProjById);
	}

	private static void addEmplProj(Connection connection, EmplProj emplProj) {

		LOGGER.log(Level.INFO, "Setting up a new emplproj");

		EmplProjService emplProjService = new EmplProjService(connection);

		emplProjService.addEmplProj(emplProj);
	}

	private static void getAllEmplProj(Connection connection) {

		EmplProjService emplProjService = new EmplProjService(connection);
		emplProjService.gettAllEmplProj();
	}

//	Project
	private static void addProject(Connection connection, Project project) {
		LOGGER.log(Level.INFO, "Setting up a new project");
		ProjectService projectService = new ProjectService(connection);
		projectService.addProject(project);
	}

	private static void getAllProjects(Connection connection) {
		LOGGER.log(Level.INFO, "Getting all projects");
		ProjectService projectService = new ProjectService(connection);
		projectService.getAllProjects();
	}

	public static void getProjectById(Connection connection, Integer projId){
		ProjectService projectService = new ProjectService(connection);
		projectService.getProjectById(projId);
	}

	private static void updateOneProject(Connection connection, Project project) {
		ProjectService projectService = new ProjectService(connection);
		projectService.updateProject(project);
	}

	private static void removeOneProject(Connection connection, Integer id) {
		ProjectService projectService = new ProjectService(connection);
		projectService.deleteProject(id);
	}

//	Employee
	private static void addEmployee(Connection connection, Employee employee) {
		EmployeeService employeeService = new EmployeeService(connection);
		employeeService.addEmployee(employee);
	}

	private static void getAllEmployees(Connection connection) {
		EmployeeService employeeService = new EmployeeService(connection);
		List<Employee> allEmployees = employeeService.getAllEmployees();
		allEmployees.forEach(System.out::println);
	}

	private static void getOneEmployee(Connection connection, Integer id) {
		EmployeeService employeeService = new EmployeeService(connection);
		Optional<Employee> oneEmployee = employeeService.getOneEmployee(id);
		System.out.println(oneEmployee);
	}

	private static void updateOneEmployee(Connection connection, Employee employee) {
		EmployeeService employeeService = new EmployeeService(connection);
		employeeService.updateOneEmployee(employee);
	}

	private static void removeOneEmployee(Connection connection, Integer id) {
		EmployeeService employeeService = new EmployeeService(connection);
		employeeService.removeOneEmployee(id);
	}

//	Address
	private static void updateOneAddress(Connection connection, Address address, Integer id) {
		AddressService addressService = new AddressService(connection);
		addressService.updateOneAddress(address, address.getId());
	}

	private static void getOneAddress(Connection connection, Integer idAddress) {
		AddressService addressService = new AddressService(connection);
		Optional<Address> oneAddress = addressService.getOneAddress(idAddress);
		System.out.println(oneAddress);
	}

	private static void getAllAddresses(Connection connection) {
		AddressService addressService = new AddressService(connection);
		List<Address> allAddresses = addressService.getAllAddresses();
		allAddresses.forEach(System.out::println);
	}

	private static void addAddress(Connection connection, Address newAddress) {
		AddressService addressService = new AddressService(connection);
		addressService.addAddress(newAddress);
	}

	private static void removeOneAddress(Connection connection, Integer idAddress) {
		AddressService addressService = new AddressService(connection);
		addressService.removeOneAddress(idAddress);
	}
}