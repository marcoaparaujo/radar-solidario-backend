package properties.authentication;

import java.util.Arrays;
import java.util.List;

import com.radar.solidario.constant.AuthenticationRole;
import com.radar.solidario.dto.token.TokenRDTO;
import com.radar.solidario.entity.Authentication;

import properties.user.UserInstance;

public class AuthenticationInstance extends AuthenticationProperties {

	public static Authentication instace() {
		List<AuthenticationRole> roles = Arrays.asList(AuthenticationRole.VOLUNTARY);
		return Authentication.builder().id(ID).email(EMAIL).password(PASSWORD).isLocked(false).role(roles)
				.user(UserInstance.instace()).build();
	}

	public static TokenRDTO tokenInstance() {
		TokenRDTO tokenRDTO = new TokenRDTO();
		tokenRDTO.setToken(TOKEN);

		return tokenRDTO;
	}
}
