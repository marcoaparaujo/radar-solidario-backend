package properties.user;

import com.radar.solidario.dto.user.UserHRDTO;
import com.radar.solidario.dto.user.UserPDTO;
import com.radar.solidario.entity.User;

import properties.authentication.AuthenticationInstance;

public class UserInstance extends UserProperties {

	public static User instace() {
		return User.builder().id(ID).cpf(CPF).name(NAME).cell(CELL).birth(BIRTH).build();
	}

	public static UserHRDTO instaceUserHRDTO() {
		return UserHRDTO.builder().cpf(CPF).name(NAME).cell(CELL).birth(BIRTH).build();
	}

	public static UserPDTO instaceUserPDTO() {
		return UserPDTO.builder().cpf(CPF).name(NAME).cell(CELL).birth(BIRTH)
				.authentication(AuthenticationInstance.instanceAuthenticationRPDTO()).build();
	}
}
