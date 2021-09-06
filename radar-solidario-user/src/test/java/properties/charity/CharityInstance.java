package properties.charity;

import com.radar.solidario.dto.charity.CharityFRPDTO;
import com.radar.solidario.dto.charity.CharityHRDTO;
import com.radar.solidario.entity.Charity;

import properties.address.AddressInstance;

public class CharityInstance extends CharityProperties {

	public static Charity instace() {
		return Charity.builder().id(ID).name(NAME).address(AddressInstance.instace()).build();
	}

	public static CharityHRDTO instaceCharityHRDTO() {
		return CharityHRDTO.builder().id(ID).name(NAME).build();
	}

	public static CharityFRPDTO instaceCharityFRPDTO() {
		return CharityFRPDTO.builder().id(ID).name(NAME).address(AddressInstance.instaceAddressPDTO()).build();
	}
}
