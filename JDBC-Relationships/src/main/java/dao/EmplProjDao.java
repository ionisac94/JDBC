package dao;

import models.EmplProj;

import java.util.List;
import java.util.Optional;

public interface EmplProjDao {

	void add(EmplProj emplProj);

	List<EmplProj> getAll();

	Optional<EmplProj> getByEmployeeIdAndProjectId(Integer empId, Integer projId);

	void update(EmplProj emplProj);

	void remove(EmplProj emplProj);
}
