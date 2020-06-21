package dao;

import models.Employee;
import models.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectDao {

	void add(Project project);

	List<Project> getAll();

	Optional<Project> getById(Integer id);

	void update(Project project, Integer id);

	void remove(Integer projectId);
}
