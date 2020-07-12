package dao;

import models.Address;

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

public class AddressDaoImpl implements AddressDao {

	private static final Logger LOGGER = Logger.getLogger(AddressDaoImpl.class.getName());

	private Connection connection;

	public AddressDaoImpl(Connection connection) {
		this.connection = Objects.requireNonNull(connection, "connection is mandatory");
	}

	@Override
	public void add(Address address) {

		LOGGER.log(Level.INFO, "About inserting a new record in DB");

		String query = "INSERT INTO address VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, address.getId());
			preparedStatement.setString(2, address.getCountry());
			preparedStatement.setString(3, address.getCity());
			preparedStatement.setString(4, address.getStreet());
			preparedStatement.setString(5, address.getPostCode());
			preparedStatement.executeUpdate();

			LOGGER.log(Level.INFO, "Inserted a new record in DB");
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred during DB call: ==> " + e);
		}
	}

	@Override
	public List<Address> getAll() {

		List<Address> addressList = new ArrayList<>();

		String query = "SELECT * FROM address";

		LOGGER.log(Level.INFO, "About fetching data from DB");

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Address address = new Address();
					address.setId(resultSet.getInt("id"));
					address.setCountry(resultSet.getString("country"));
					address.setCity(resultSet.getString("city"));
					address.setStreet(resultSet.getString("street"));
					address.setPostCode(resultSet.getString("postCode"));

					addressList.add(address);
				}
			}
			LOGGER.log(Level.INFO, "From DB was fetched " + addressList.size() + " addresses");
		} catch (SQLException ex) {
			LOGGER.log(Level.SEVERE, "An error occurred during DB call: ==> " + ex);
		}
		return addressList;
	}

	@Override
	public Optional<Address> getById(Integer id) {
		Address address = new Address();

		String query = "SELECT * FROM address WHERE id = ?";

		LOGGER.log(Level.INFO, "About fetching one record from DB");

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, id);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					address.setId(resultSet.getInt("id"));
					address.setCountry(resultSet.getString("country"));
					address.setCity(resultSet.getString("city"));
					address.setStreet(resultSet.getString("street"));
					address.setPostCode(resultSet.getString("postCode"));
				}
			}
		} catch (SQLException ex) {
			LOGGER.log(Level.SEVERE, "An error occurred during DB call: ==> " + ex);
		}

		return Optional.of(address);
	}

	@Override
	public void update(Address address, Integer id) {

		LOGGER.log(Level.INFO, "About updating one record in DB");

		String query = "UPDATE address SET country=?, city=?, street=?, postCode=? WHERE id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setString(1, address.getCountry());
			preparedStatement.setString(2, address.getCity());
			preparedStatement.setString(3, address.getStreet());
			preparedStatement.setString(4, address.getPostCode());
			preparedStatement.setLong(5, id);
			int affectedRecords = preparedStatement.executeUpdate();

			if (affectedRecords > 0) {
				LOGGER.log(Level.INFO, "Updated one record in DB");
			} else {
				LOGGER.log(Level.INFO, "Updated zero records in DB");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred during DB call: ==> " + e);
		}
	}

	@Override
	public void remove(Integer id) {

		LOGGER.log(Level.INFO, "About removing one record from DB");

		String query = "DELETE FROM address WHERE id = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

			preparedStatement.setInt(1, id);

			int affectedRecords = preparedStatement.executeUpdate();

			if (affectedRecords > 0) {
				LOGGER.log(Level.INFO, "Removed one record from DB");
			} else {
				LOGGER.log(Level.INFO, "Removed zero records from DB");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "An error occurred during DB call: ==> " + e);
		}
	}
}
