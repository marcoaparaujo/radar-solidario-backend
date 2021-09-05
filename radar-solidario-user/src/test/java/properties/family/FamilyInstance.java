package properties.family;

import com.radar.solidario.dto.family.FamilyHRDTO;
import com.radar.solidario.dto.family.FamilyPDTO;
import com.radar.solidario.dto.family.FamilyRDTO;
import com.radar.solidario.entity.Family;

import properties.address.AddressInstance;
import properties.donate.DonateInstance;

public class FamilyInstance extends FamilyProperties {

	public static Family instace() {
		return Family.builder().id(ID).cpf(CPF).nis(NIS).head(HEAD).donate(DonateInstance.instaceDonates(1))
				.address(AddressInstance.instace()).build();
	}

	public static FamilyPDTO instanceFamilyPDTO() {
		return FamilyPDTO.builder().cpf(CPF).nis(NIS).head(HEAD).address(AddressInstance.instaceAddressPDTO()).build();
	}

	public static FamilyRDTO instaceFamilyRDTO() {
		return FamilyRDTO.builder().id(ID).cpf(CPF).nis(NIS).head(HEAD).address(AddressInstance.instaceAddressPDTO())
				.donatePrevision(DonateInstance.instaceDonatePrevisionDTO()).build();
	}

	public static FamilyHRDTO instaceFamilyHRDTO() {
		return FamilyHRDTO.builder().cpf(CPF).nis(NIS).head(HEAD).build();
	}

}
