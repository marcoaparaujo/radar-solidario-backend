package properties.foodStamp;

import com.radar.solidario.entity.FoodStamp;

public class FoodStampInstance extends FoodStampProperties {

	public static FoodStamp instace() {
		return FoodStamp.builder().id(ID).isAble(IS_ABLE).length(LENGTH).weight(WEIGHT).date(DATE).build();
	}
}
