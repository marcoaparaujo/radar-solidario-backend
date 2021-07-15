package properties.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.radar.solidario.constant.AuthenticationRole;
import com.radar.solidario.dto.LoginDTO;
import com.radar.solidario.dto.token.TokenFRDTO;
import com.radar.solidario.dto.token.TokenRDTO;
import com.radar.solidario.security.entity.JwtUser;

import properties.authentication.AuthenticationProperties;
import properties.user.UserProperties;

public class SecurityInstance extends AuthenticationProperties {

	public static UsernamePasswordAuthenticationToken intanceUsernamePasswordAuthenticationToken() {
		return new UsernamePasswordAuthenticationToken(EMAIL, PASSWORD);
	}

	public static JwtUser jwtUserInstance() {
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(AuthenticationRole.VOLUNTARY.name());
		return JwtUser.builder().id(ID).email(EMAIL).password(PASSWORD).authorities(Arrays.asList(grantedAuthority))
				.build();
	}

	public static LoginDTO instanceLoginDTO() {
		return LoginDTO.builder().email(EMAIL).password(PASSWORD).build();
	}

	public static TokenRDTO instanceTokenRDTO() {
		return TokenRDTO.builder().token(TOKEN).build();
	}

	public static TokenFRDTO instanceTokenFRDTO() {
		List<AuthenticationRole> roles = Arrays.asList(AuthenticationRole.VOLUNTARY);
		return TokenFRDTO.builder().name(UserProperties.NAME).token(TOKEN).roles(roles).build();
	}
}
