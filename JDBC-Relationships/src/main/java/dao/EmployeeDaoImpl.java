package dao;

import models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeDaoImpl implements EmployeeDao {

	private static final Logger LOGGER = Logger.getLogger(EmployeeDaoImpl.class.getName());

	private Connection connection;

	public EmployeeDaoImpl(Connection connection) {
		this.connection = Objects.requireNonNull(connection, "connection is mandatory");
	}

	@Override
	public void add(Employee employee) {

		LOGGER.log(Level.INFO, "About inserting a new record in DB");

		String query = "INSERT INTO employee VALUES(?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, employee.getId());
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setDate(4, employee.getBirthday());
			preparedStatement.setInt(5, employee.getAddressId());
			preparedStatement.executeUpdate();

			LOGGER.log(Level.INFO, "Inserted a new record in DB");
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred during DB call: ==> " + e);
		}
	}

	@Override
	public List<Employee> getAll() {

		List<Employee> employees = new ArrayList<>();

		String query = "SELECT * FROM employee";

		LOGGER.log(Level.INFO, "About fetching data from DB");

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {

					Employee employee = new Employee();

					employee.setId(resultSet.getInt("id"));
					employee.setFirstName(resultSet.getString("firstName"));
					employee.setLastName(resultSet.getString("lastName"));
					employee.setBirthday(resultSet.getDate("birthday"));
					employee.setAddressId(resultSet.getInt("addressId"));

					employees.add(employee);
					LOGGER.log(Level.INFO, "From DB was fetched " + employees.size() + " employees");
				}
			}
		} catch (SQLException ex) {
			LOGGER.log(Level.SEVERE, "An error occurred during DB call: ==> " + ex);
		}
		return employees;
	}

	@Override
	public Optional<Employee> getById(Integer id) {
		Employee employee = new Employee();
		String sql = "SELECT * from employee WHERE id = ?";

		LOGGER.log(Level.INFO, "About fetching one record from DB");

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setLong(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {
					employee.setId(resultSet.getInt("id"));
					employee.setFirstName(resultSet.getString("firstName"));
					employee.setLastName(resultSet.getString("lastName"));
					employee.setBirthday(resultSet.getDate("birthday"));
					employee.setAddressId(resultSet.getInt("addressId"));
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred during DB call: ==> " + e);
		}
		return Optional.of(employee);
	}

	@Override
	public void update(Employee employee) {

		LOGGER.log(Level.INFO, "About updating one record in DB");

		String query = "UPDATE employee SET firstName=?, lastName=?, birthday=?, addressId=? WHERE id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setDate(3, employee.getBirthday());
			preparedStatement.setInt(4, employee.getAddressId());
			preparedStatement.setInt(5, employee.getId());
			int affectedRecords = preparedStatement.executeUpdate();

			if (affectedRecords > 0) {
				LOGGER.log(Level.INFO, "Updated one record in DB");
			} else {
				LOGGER.log(Level.INFO, "Updated zero records in DB");
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred during DB call: ==> " + e);
		}
	}

	@Override
	public void remove(Integer employeeId) {

		LOGGER.log(Level.INFO, "About removing one record from DB");

		String query = "DELETE FROM employee WHERE id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, employeeId);

			int affectedRecords = preparedStatement.executeUpdate();

			if (affectedRecords > 0) {
				LOGGER.log(Level.INFO, "Removed one record from DB");
			} else {
				LOGGER.log(Level.INFO, "Removed zero records from DB");
			}

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred during DB call: ==> " + e);
		}
	}
}
