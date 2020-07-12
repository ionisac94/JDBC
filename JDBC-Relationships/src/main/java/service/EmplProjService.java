package service;

import dao.EmplProjDao;
import dao.EmplProjDaoImpl;
import models.EmplProj;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class EmplProjService {

	private Connection connection;

	public EmplProjService(Connection connection) {
		this.connection = requireNonNull(connection, "connection is mandatory");
	}

	public void addEmplProj(EmplProj emplProj) {
		EmplProjDao emplProjDao = new EmplProjDaoImpl(connection);
		emplProjDao.add(emplProj);
	}

	public void gettAllEmplProj() {
		EmplProjDao emplProjDao = new EmplProjDaoImpl(connection);
		List<EmplProj> all = emplProjDao.getAll();

		List<EmplProj> collect = all.stream().collect(Collectors.toList());

		collect.forEach(System.out::println);
	}

	public Optional<EmplProj> getEmplProjById(Integer empId, Integer projId) {

		EmplProjDao emplProjDao = new EmplProjDaoImpl(connection);

		return emplProjDao.getByEmployeeIdAndProjectId(empId, projId);
	}

	public void updateEmplProj(Connection connection, EmplProj emplProj) {
		EmplProjDao emplProjDao = new EmplProjDaoImpl(connection);
		emplProjDao.update(emplProj);
	}

	public void deleteEmplProj(Connection connection, EmplProj emplProj) {
		EmplProjDao emplProjDao = new EmplProjDaoImpl(connection);
		emplProjDao.remove(emplProj);
	}

}
