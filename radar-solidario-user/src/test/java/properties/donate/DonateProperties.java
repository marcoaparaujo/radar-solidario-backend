package properties.donate;

import java.time.LocalDateTime;
import java.util.List;

import com.radar.solidario.dto.IdentificatorDTO;
import com.radar.solidario.dto.family.FamilyHRDTO;
import com.radar.solidario.dto.foodStamp.FoodStampHPDTO;
import com.radar.solidario.dto.user.UserHRDTO;
import com.radar.solidario.entity.Charity;
import com.radar.solidario.entity.Family;
import com.radar.solidario.entity.FoodStamp;
import com.radar.solidario.entity.User;

import properties.charity.CharityInstance;
import properties.family.FamilyInstance;
import properties.foodStamp.FoodStampInstance;
import properties.general.GeneralProperties;
import properties.indetificator.IndetificatorInstance;
import properties.user.UserInstance;

public class DonateProperties extends GeneralProperties {

	public static final LocalDateTime DONATE_DATE = LocalDateTime.now();
	public static final LocalDateTime LAST_DONATE_DATE = LocalDateTime.of(2021, 8, 9, 15, 20);
	public static final User USER = UserInstance.instace();
	public static final UserHRDTO USERHRDTO = UserInstance.instaceUserHRDTO();
	public static final IdentificatorDTO INDETIFICATOR = IndetificatorInstance.instace();
	public static final Family FAMILY = FamilyInstance.instace();
	public static final FamilyHRDTO FAMILYHRDTO = FamilyInstance.instaceFamilyHRDTO();
	public static final Charity CHARITY = CharityInstance.instace();
	public static final List<FoodStamp> FOODSTAMPS = FoodStampInstance.instaceFoodStamps(5);
	public static final List<FoodStampHPDTO> FOODSTAMPSHPDTO = FoodStampInstance.instaceFoodStampsHPDTOs(5);

}
