package properties.donate;

import java.time.LocalDateTime;

import properties.general.GeneralProperties;

public class DonateProperties extends GeneralProperties {
	
	public static final LocalDateTime DONATE_DATE = LocalDateTime.now();
	public static final LocalDateTime LAST_DONATE_DATE = LocalDateTime.of(2021, 8, 9, 15, 20);
}
