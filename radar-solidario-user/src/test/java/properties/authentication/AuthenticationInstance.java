package properties.authentication;

import com.radar.solidario.dto.authentication.AuthenticationFPDTO;
import com.radar.solidario.dto.authentication.AuthenticationFRDTO;
import com.radar.solidario.dto.authentication.AuthenticationRPDTO;
import com.radar.solidario.dto.token.TokenRDTO;
import com.radar.solidario.entity.Authentication;

import properties.security.SecurityInstance;
import properties.user.UserInstance;

public class AuthenticationInstance extends AuthenticationProperties {

	public static Authentication instace() {
		return Authentication.builder().id(ID).email(EMAIL).password(PASSWORD).isLocked(false)
				.role(SecurityInstance.instaceVoluntaryRoles()).user(UserInstance.instace()).build();
	}

	public static TokenRDTO instanceToken() {
		return TokenRDTO.builder().token(TOKEN).build();
	}

	public static AuthenticationRPDTO instanceAuthenticationRPDTO() {
		return AuthenticationRPDTO.builder().email(EMAIL).password(PASSWORD).build();
	}

	public static AuthenticationFRDTO instaceAuthenticationFRDTO() {
		return AuthenticationFRDTO.builder().id(ID).email(EMAIL).password(PASSWORD).isLocked(false)
				.user(UserInstance.instaceUserHRDTO()).role(SecurityInstance.instaceVoluntaryRoles()).build();
	}

	public static AuthenticationFPDTO instanceAuthenticationFPDTO() {
		return AuthenticationFPDTO.builder().id(ID).email(EMAIL).password(PASSWORD).build();
	}
}
