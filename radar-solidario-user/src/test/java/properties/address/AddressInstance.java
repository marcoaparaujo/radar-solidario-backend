package properties.address;

import java.util.ArrayList;
import java.util.List;

import com.radar.solidario.dto.address.AddressPDTO;
import com.radar.solidario.entity.Address;

public class AddressInstance extends AddressProperties {

	public static Address instace() {
		return Address.builder().id(ID).cep(CEP).phone(PHONE).number(NUMBER).street(STREET).complement(COMPLEMENT).neighborhood(NEIGHBORHOOD).build();
	}
	
	public static AddressPDTO instaceAddressPDTO() {
		return AddressPDTO.builder().cep(CEP).phone(PHONE).number(NUMBER).street(STREET).complement(COMPLEMENT).neighborhood(NEIGHBORHOOD).build();
	}

	public static List<Address> instaceAddresses(Integer length) {
		List<Address> addresses = new ArrayList<>();

		for (Integer i = 0; i < length; i++) {
			addresses.add(instace());
		}

		return addresses;
	}
}
