package dao;

import models.EmplProj;

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

public class EmplProjDaoImpl implements EmplProjDao {

	private static final Logger LOGGER = Logger.getLogger(EmplProjDaoImpl.class.getName());

	private Connection connection;

	public EmplProjDaoImpl(Connection connection) {
		this.connection = Objects.requireNonNull(connection, "connection is mandatory!");
	}


	@Override
	public void add(EmplProj emplProj) {
		String query = "INSERT INTO empl_proj VALUES (?, ?)";

		LOGGER.log(Level.INFO, "About inserting a new record in DB");

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, emplProj.getEmployeeId());
			preparedStatement.setInt(2, emplProj.getProjectId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "An error occurred during DB call: ==> " + e);
		}
		LOGGER.log(Level.INFO, "Inserted a new record in DB");
	}

	@Override
	public List<EmplProj> getAll() {
		String query = "SELECT * FROM empl_proj";

		List<EmplProj> emplProjs = new ArrayList<>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				EmplProj emplProj = new EmplProj();
				while (resultSet.next()) {
					emplProj.setEmployeeId(resultSet.getInt(1));
					emplProj.setProjectId(resultSet.getInt(2));
				}
				emplProjs.add(emplProj);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "An error occurred during DB call: ==> " + e);
		}
		LOGGER.log(Level.INFO, "From DB was fetched " + emplProjs.size() + " emplProjs");

		return emplProjs;
	}

	@Override
	public Optional<EmplProj> getByEmployeeIdAndProjectId(Integer empId, Integer projId) {
		String sql = "SELECT * from empl_proj WHERE employeeId = ? AND projectId = ?";
		EmplProj emplProj = new EmplProj();

		LOGGER.log(Level.INFO, "About fetching one record from DB");

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, empId);
			preparedStatement.setInt(2, projId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {
					emplProj.setProjectId(resultSet.getInt("employeeId"));
					emplProj.setEmployeeId(resultSet.getInt("projectId"));
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "An error occurred during DB call: ==> " + e);
		}

		return Optional.of(emplProj);
	}

	@Override
	public void update(EmplProj emplProj) {
		String query = "UPDATE empl_proj SET employeeId=? AND projectId=?";

		LOGGER.log(Level.INFO, "About updating one record in DB");

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, emplProj.getEmployeeId());
			preparedStatement.setInt(2, emplProj.getProjectId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "An error occurred during DB call: ==> " + e);
		}
		LOGGER.log(Level.INFO, "Updated one record in DB");
	}

	@Override
	public void remove(EmplProj emplProj) {
		String query = "DELETE FROM  empl_proj WHERE employeeId=? AND projectId=?";

		LOGGER.log(Level.INFO, "About deleting one record in DB");

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, emplProj.getEmployeeId());
			preparedStatement.setInt(2, emplProj.getProjectId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "An error occurred during DB call: ==> " + e);
		}
		LOGGER.log(Level.INFO, "Updated one record in DB");
	}
}
