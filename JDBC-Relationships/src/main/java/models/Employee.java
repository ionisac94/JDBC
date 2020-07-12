package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	private Integer id;

	private String firstName;

	private String lastName;

	private Date birthday; // YYYY-M-D

	//one address to many employees
	private Integer addressId;
}
