package properties.foodStamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.radar.solidario.dto.foodStamp.FoodStampHPDTO;
import com.radar.solidario.dto.foodStamp.FoodStampRDTO;
import com.radar.solidario.entity.FoodStamp;

public class FoodStampInstance extends FoodStampProperties {

	public static FoodStamp instace() {
		return FoodStamp.builder().id(ID).charity(CHARITY).donate(DONATE).isAble(IS_ABLE).length(LENGTH).weight(WEIGHT)
				.date(DATE).build();
	}

	public static FoodStampRDTO instaceRDTO() {
		return FoodStampRDTO.builder().id(ID).isAble(IS_ABLE).length(LENGTH).weight(WEIGHT).date(DATE).build();
	}

	public static FoodStamp instaceMergeAdd() {
		return FoodStamp.builder().id(ID).isAble(IS_ABLE).length(LENGTH + LENGTH).weight(WEIGHT).date(DATE).build();
	}

	public static FoodStamp instaceMergeRemove() {
		return FoodStamp.builder().id(ID).isAble(IS_ABLE).length(LENGTH - LENGTH).weight(WEIGHT).date(DATE).build();
	}

	public static Optional<FoodStamp> instaceOptional() {
		return Optional.of(FoodStamp.builder().id(ID).isAble(IS_ABLE).length(LENGTH).weight(WEIGHT).date(DATE).build());
	}

	public static FoodStampHPDTO instaceHPDTO() {
		return FoodStampHPDTO.builder().length(LENGTH).weight(WEIGHT).build();
	}

	public static List<FoodStampHPDTO> instaceFoodStampsHPDTOs(Integer length) {
		List<FoodStampHPDTO> foodStamps = new ArrayList<>();

		for (Integer i = 0; i < length; i++) {
			foodStamps.add(instaceHPDTO());
		}

		return foodStamps;
	}

	public static List<FoodStamp> instaceFoodStamps(Integer length) {
		List<FoodStamp> foodStamps = new ArrayList<>();

		for (Integer i = 0; i < length; i++) {
			foodStamps.add(instace());
		}

		return foodStamps;
	}

	public static List<FoodStampRDTO> instaceFoodStampRDTOs(Integer length) {
		List<FoodStampRDTO> foodStamps = new ArrayList<>();

		for (Integer i = 0; i < length; i++) {
			foodStamps.add(instaceRDTO());
		}

		return foodStamps;
	}
}
