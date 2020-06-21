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

public class EmplProjImpl implements EmplProjDao {

	private static final Logger LOGGER = Logger.getLogger(EmplProjImpl.class.getName());

	private Connection connection;

	public EmplProjImpl(Connection connection) {
		this.connection = Objects.requireNonNull(connection, "connection is mandatory!");
	}


	@Override
	public void add(EmplProj emplProj) {
		LOGGER.log(Level.INFO, "About inserting a new record in DB");

		String query = "INSERT INTO empl_proj VALUES (?, ?)";

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

		List<EmplProj> emplProjs = new ArrayList<>();

		String query = "SELECT * FROM empl_proj";


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
	public Optional<EmplProj> getById(Integer id) {
		return Optional.empty();
	}

	@Override
	public void update(EmplProj emplProj, Integer id) {

	}

	@Override
	public void remove(Integer projectId) {

	}
}
