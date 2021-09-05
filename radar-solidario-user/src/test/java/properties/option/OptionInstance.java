package properties.option;

import java.util.ArrayList;
import java.util.List;

import com.radar.solidario.dto.OptionDTO;

public class OptionInstance extends OptionProperties {

	@SuppressWarnings("unchecked")
	public static <T> OptionDTO<T> instace() {
		return (OptionDTO<T>) OptionDTO.builder().text(TEXT).value(VALUE).build();
	}
	
	public static List<OptionDTO<Long>> instaceOptions(Integer length) {
		 List<OptionDTO<Long>> options = new ArrayList<>();

		for (Integer i = 0; i < length; i++) {
			options.add(instace());
		}

		return options;
	}
}
