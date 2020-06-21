package dao;

import models.EmplProj;

import java.util.List;
import java.util.Optional;

public interface EmplProjDao {

	void add(EmplProj emplProj);

	List<EmplProj> getAll();

	Optional<EmplProj> getById(Integer id);

	void update(EmplProj emplProj, Integer id);

	void remove(Integer projectId);
}
