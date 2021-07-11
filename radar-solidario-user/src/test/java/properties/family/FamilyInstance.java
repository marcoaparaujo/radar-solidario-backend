package properties.family;

import com.radar.solidario.entity.Family;

import properties.address.AddressInstance;

public class FamilyInstance extends FamilyProperties {

	public static Family instace() {
		return Family.builder().id(ID).cpf(CPF).nis(NIS).head(HEAD).address(AddressInstance.instace()).build();
	}
}
