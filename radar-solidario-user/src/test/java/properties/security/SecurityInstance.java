package properties.security;

import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.radar.solidario.constant.AuthenticationRole;
import com.radar.solidario.security.entity.JwtUser;

import properties.authentication.AuthenticationProperties;

public class SecurityInstance extends AuthenticationProperties {

	public static UsernamePasswordAuthenticationToken usernamePasswordAuthenticationTokenInstance() {
		return new UsernamePasswordAuthenticationToken(EMAIL, PASSWORD);
	}

	public static JwtUser jwtUserInstance() {
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(AuthenticationRole.VOLUNTARY.name());
		return JwtUser.builder().id(ID).email(EMAIL).password(PASSWORD).authorities(Arrays.asList(grantedAuthority))
				.build();
	}
}
