package service;

import dao.ProjectDao;
import dao.ProjectDaoImpl;
import models.Project;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class ProjectService {

	private Connection connection;

	public ProjectService(Connection connection) {
		this.connection = requireNonNull(connection, "connection is mandatory");
	}

	public void addProject(Project project) {
		ProjectDao projectDao = new ProjectDaoImpl(connection);
		projectDao.add(project);
	}

	public void getAllProjects() {
		ProjectDao projectDao = new ProjectDaoImpl(connection);
		List<Project> all = projectDao.getAll()
				.stream()
				.sorted(Comparator.comparingInt(Project::getId))
				.collect(Collectors.toList());
		all.forEach(System.out::println);
	}

	public void getProjectById(Integer projId){
		ProjectDao projectDao = new ProjectDaoImpl(connection);
		Optional<Project> byId = projectDao.getById(projId);
		System.out.println(byId);
	}

	public void updateProject(Project project){
		ProjectDao projectDao = new ProjectDaoImpl(connection);
		projectDao.update(project);
	}

	public void deleteProject(Integer id){
		ProjectDao projectDao = new ProjectDaoImpl(connection);
		projectDao.remove(id);
	}
}
