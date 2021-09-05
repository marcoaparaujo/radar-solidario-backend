package properties.foodStamp;

import java.time.LocalDate;

import com.radar.solidario.entity.Charity;
import com.radar.solidario.entity.Donate;

import properties.charity.CharityInstance;
import properties.donate.DonateInstance;
import properties.general.GeneralProperties;

public class FoodStampProperties extends GeneralProperties {

	public static final Double WEIGHT = 5D;
	public static final Integer LENGTH = 10;
	public static final Boolean IS_ABLE = true;
	public static final LocalDate DATE = LocalDate.now();
	public static final Charity CHARITY = CharityInstance.instace();
	public static final Donate DONATE = DonateInstance.instace();
	public static final String NAME = CharityInstance.instace().getName();
}
