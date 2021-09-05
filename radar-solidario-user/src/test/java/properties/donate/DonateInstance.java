package properties.donate;

import java.util.ArrayList;
import java.util.List;

import com.radar.solidario.dto.donate.DonatePrevisionDTO;
import com.radar.solidario.entity.Donate;

public class DonateInstance extends DonateProperties {

	public static Donate instace() {
		return Donate.builder().id(ID).date(DONATE_DATE).build();
	}

	public static DonatePrevisionDTO instaceDonatePrevisionDTO() {
		return DonatePrevisionDTO.builder().lastDonateDate(LAST_DONATE_DATE).nextDonateDate(DONATE_DATE).build();
	}

	public static List<Donate> instaceDonates(Integer length) {
		List<Donate> donates = new ArrayList<>();

		for (Integer i = 0; i < length; i++) {
			donates.add(instace());
		}

		return donates;
	}
}
