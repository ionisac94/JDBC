package service;

import dao.AddressDao;
import dao.AddressDaoImpl;
import models.Address;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class AddressService {

	private Connection connection;

	public AddressService(Connection connection) {
		this.connection = requireNonNull(connection, "connection is mandatory");
	}

	public void addAddress(Address newAddress) {
		AddressDao addressDao = new AddressDaoImpl(connection);

		addressDao.add(newAddress);
	}

	public List<Address> getAllAddresses() {

		AddressDao addressDao = new AddressDaoImpl(connection);

		List<Address> addressList = addressDao
				.getAll()
				.stream()
				.sorted(Comparator.comparingInt(Address::getId))
				.collect(Collectors.toList());

		return addressList;
	}

	public Optional<Address> getOneAddress(Integer id) {
		AddressDao addressDao = new AddressDaoImpl(connection);

		return addressDao.getById(id);
	}

	public void updateOneAddress(Address address, Integer id) {
		AddressDao addressDao = new AddressDaoImpl(connection);
		addressDao.update(address, id);
	}

	public void removeOneAddress(Integer id) {
		AddressDao addressDao = new AddressDaoImpl(connection);
		addressDao.remove(id);
	}
}
