package properties.user;

import com.radar.solidario.entity.User;

public class UserInstance extends UserProperties {

	public static User instace() {
		return User.builder().id(ID).cpf(CPF).name(NAME).cell(CELL).birth(BIRTH).gender(GENDER).build();
	}
}
