package service;

import dao.EmplProjDao;
import dao.EmplProjImpl;
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
		EmplProjDao emplProjDao = new EmplProjImpl(connection);
		emplProjDao.add(emplProj);
	}

	public void gettAllEmplProj() {
		EmplProjDao emplProjDao = new EmplProjImpl(connection);
		List<EmplProj> all = emplProjDao.getAll();

		List<EmplProj> collect = all.stream().collect(Collectors.toList());

		collect.forEach(System.out::println);
	}

	public Optional<EmplProj> getEmplProjById(Integer id){

		EmplProjDao emplProjDao = new EmplProjImpl(connection);

		return emplProjDao.getById(id);
	}

}
