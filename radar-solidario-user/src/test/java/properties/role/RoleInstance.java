package properties.role;

import java.util.ArrayList;
import java.util.List;

import com.radar.solidario.entity.Role;

public class RoleInstance extends RoleProperties {

	public static Role instaceVoluntary() {
		return Role.builder().id(ID).name(NAME_VOLUNTARY).build();
	}

	public static List<Role> instaceVoluntaryAndAdministrator() {
		Role voluntary = Role.builder().id(ID).name(NAME_VOLUNTARY).build();
		Role administrator = Role.builder().id(ID).name(NAME_ADMINISTRATOR).build();

		List<Role> roles = new ArrayList<>();
		roles.add(voluntary);
		roles.add(administrator);

		return roles;
	}
}
