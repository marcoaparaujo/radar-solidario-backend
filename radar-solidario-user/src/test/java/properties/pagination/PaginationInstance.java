package properties.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

public class PaginationInstance extends PaginationProperties {

	public static final PageRequest PAGE_REQUEST_ASC = PageRequest.of(PAGE, PAGE_SIZE,
			Direction.valueOf(DIRECTION_ASC), ORDER);

	public static final PageRequest PAGE_REQUEST_DESC = PageRequest.of(PAGE, PAGE_SIZE,
			Direction.valueOf(DIRECTION_DESC), ORDER);
}
