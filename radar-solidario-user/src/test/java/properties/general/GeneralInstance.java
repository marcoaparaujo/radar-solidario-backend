package properties.general;

import com.radar.solidario.dto.IdentificatorDTO;

public class GeneralInstance extends GeneralProperties {

	public static IdentificatorDTO instaceIdentificatorDTO() {
		return IdentificatorDTO.builder().id(ID).build();
	}
}
