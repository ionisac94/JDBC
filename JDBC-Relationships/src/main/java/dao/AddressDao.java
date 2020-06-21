package dao;

import models.Address;

import java.util.List;
import java.util.Optional;

public interface AddressDao {

	void add(Address address);

	List<Address> getAll();

	Optional<Address> getById(Integer id);

	void update(Address address, Integer id);

	void remove(Integer id);
}
