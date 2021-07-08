package properties.authentication;

import java.util.Arrays;
import java.util.List;

import com.radar.solidario.constant.AuthenticationRole;
import com.radar.solidario.dto.token.TokenRDTO;
import com.radar.solidario.entity.Authentication;

public class AuthenticationInstance extends AuthenticationProperties {

	public static Authentication instace() {
		List<AuthenticationRole> roles = Arrays.asList(AuthenticationRole.VOLUNTARY);

		Authentication authentication = new Authentication();
		authentication.setId(ID);
		authentication.setRole(roles);
		authentication.setEmail(EMAIL);
		authentication.setPassword(PASSWORD);

		return authentication;
	}

	public static TokenRDTO tokenRDTOInstance() {
		TokenRDTO tokenRDTO = new TokenRDTO();
		tokenRDTO.setToken(TOKEN);

		return tokenRDTO;
	}
}
