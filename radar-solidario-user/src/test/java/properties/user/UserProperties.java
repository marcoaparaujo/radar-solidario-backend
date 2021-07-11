package properties.user;

import java.time.LocalDate;

import com.radar.solidario.constant.Gender;

import properties.GeneralProperties;

public class UserProperties extends GeneralProperties {

	protected static final Gender GENDER = Gender.MALE;
	protected static final String NAME = "Carl Jhonson";
	protected static final String CPF = "993.725.070-67";
	protected static final String CELL = "(00) 90000-0000";
	protected static final LocalDate BIRTH = LocalDate.now();

	protected static final String WRONG_CPF = "992.633.790-25";
}
