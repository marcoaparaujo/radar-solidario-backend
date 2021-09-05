package properties.user;

import com.radar.solidario.dto.user.UserHRDTO;
import com.radar.solidario.dto.user.UserPDTO;
import com.radar.solidario.dto.user.UserTokenHRDTO;
import com.radar.solidario.entity.User;

import properties.authentication.AuthenticationInstance;
import properties.charity.CharityInstance;
import properties.general.GeneralInstance;

public class UserInstance extends UserProperties {

	public static User instace() {
		return User.builder().id(ID).cpf(CPF).name(NAME).cell(CELL).birth(BIRTH).charity(CharityInstance.instace())
				.build();
	}

	public static UserHRDTO instaceUserHRDTO() {
		return UserHRDTO.builder().cpf(CPF).name(NAME).cell(CELL).birth(BIRTH)
				.charity(CharityInstance.instaceCharityFRPDTO()).build();
	}

	public static UserPDTO instaceUserPDTO() {
		return UserPDTO.builder().cpf(CPF).name(NAME).cell(CELL).birth(BIRTH)
				.charity(GeneralInstance.instaceIdentificatorDTO())
				.authentication(AuthenticationInstance.instanceAuthenticationRPDTO()).build();
	}

	public static UserTokenHRDTO instaceUserTokenHRDTO() {
		return UserTokenHRDTO.builder().id(ID).name(NAME).build();
	}
}
