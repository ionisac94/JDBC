package dao;

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

		LOGGER.log(Level.INFO, "About inserting a new record in DB");

		Project newProject = new Project();

		String query = "INSERT INTO project VALUES (?, ?)";

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

		LOGGER.log(Level.INFO, "About fetching data from DB");

		List<Project> projects = new ArrayList<>();

		String query = "SELECT * FROM project";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				Project project = new Project();
				while (resultSet.next()) {
					project.setId(resultSet.getInt(1));
					project.setTitle(resultSet.getString(2));

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
		return Optional.empty();
	}

	@Override
	public void update(Project project, Integer id) {

	}

	@Override
	public void remove(Integer prjectId) {

	}
}
