package properties.indetificator;

import com.radar.solidario.dto.IdentificatorDTO;

public class IndetificatorInstance extends IndetificatorProperties {

	public static IdentificatorDTO instace() {
		return IdentificatorDTO.builder().id(ID).build();
	}
}
