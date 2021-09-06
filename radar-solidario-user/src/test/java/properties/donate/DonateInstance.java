package properties.donate;

import java.util.ArrayList;
import java.util.List;

import com.radar.solidario.dto.donate.DonatePDTO;
import com.radar.solidario.dto.donate.DonatePrevisionDTO;
import com.radar.solidario.dto.donate.DonateRDTO;
import com.radar.solidario.entity.Donate;

public class DonateInstance extends DonateProperties {

	public static Donate instace() {
		return Donate.builder().id(ID).user(USER).date(DONATE_DATE).family(FAMILY)
				.charity(CHARITY).foodStamp(FOODSTAMPS).build();
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

	public static DonatePDTO instacePDTO() {
		return DonatePDTO.builder().user(INDETIFICATOR).family(INDETIFICATOR)
				.charity(INDETIFICATOR).foodStamps(FOODSTAMPSHPDTO).build();
	}

	public static DonateRDTO instaceRDTO() {
		return DonateRDTO.builder().date(DONATE_DATE).user(USERHRDTO)
				.family(FAMILYHRDTO).build();
	}
}
