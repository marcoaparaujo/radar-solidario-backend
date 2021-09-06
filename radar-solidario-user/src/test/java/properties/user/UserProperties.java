package properties.user;

import java.time.LocalDate;

import properties.general.GeneralProperties;

public class UserProperties extends GeneralProperties {

	public static final String NAME = "Carl Jhonson";
	public static final String CPF = "993.725.070-67";
	public static final String CELL = "(00) 90000-0000";
	public static final LocalDate BIRTH = LocalDate.now();

	public static final String WRONG_CPF = "992.633.790-25";
}
