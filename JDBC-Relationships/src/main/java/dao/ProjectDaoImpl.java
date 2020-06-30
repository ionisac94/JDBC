package dao;

import models.Employee;
import models.Project;

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

public class ProjectDaoImpl implements ProjectDao {

	private static final Logger LOGGER = Logger.getLogger(ProjectDaoImpl.class.getName());

	private Connection connection;

	public ProjectDaoImpl(Connection connection) {
		this.connection = Objects.requireNonNull(connection, "connection is mandatory");
	}

	@Override
	public void add(Project project) {
		String query = "INSERT INTO project VALUES (?, ?)";

		LOGGER.log(Level.INFO, "About inserting a new record in DB");


		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, project.getId());
			preparedStatement.setString(2, project.getTitle());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "An error occurred during DB call: ==> " + e);
		}
		LOGGER.log(Level.INFO, "Inserted a new record in DB");
	}

	@Override
	public List<Project> getAll() {
		String query = "SELECT * FROM project";

		LOGGER.log(Level.INFO, "About fetching data from DB");

		List<Project> projects = new ArrayList<>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Project project = new Project();
					project.setId(resultSet.getInt("id"));
					project.setTitle(resultSet.getString("title"));
					projects.add(project);
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "An error occurred during DB call: ==> " + e);
		}
		LOGGER.log(Level.INFO, "From DB was fetched " + projects.size() + " projects");

		return projects;
	}

	@Override
	public Optional<Project> getById(Integer id) {
		String sql = "SELECT * from project WHERE id = ?";

		Project project = new Project();

		LOGGER.log(Level.INFO, "About fetching one record from DB");

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setLong(1, id);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					project.setId(resultSet.getInt("id"));
					project.setTitle(resultSet.getString("title"));
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "An error occurred during DB call: ==> " + e);
		}

		return Optional.of(project);	}

	@Override
	public void update(Project project) {
		String query = "UPDATE project SET title=? WHERE id=?";

		LOGGER.log(Level.INFO, "About updating one record in DB");

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, project.getTitle());
			preparedStatement.setInt(2, project.getId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "An error occurred during DB call: ==> " + e);
		}
		LOGGER.log(Level.INFO, "Updated one record from DB");
	}

	@Override
	public void remove(Integer prjectId) {

		LOGGER.log(Level.INFO, "About removing one record from DB");

		String query = "DELETE FROM project WHERE id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, prjectId);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.INFO, "An error occurred during DB call: ==> " + e);
		}
		LOGGER.log(Level.INFO, "Removed one record from DB");
	}
}
