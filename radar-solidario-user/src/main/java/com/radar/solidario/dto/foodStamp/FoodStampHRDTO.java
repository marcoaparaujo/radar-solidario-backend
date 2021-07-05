package com.radar.solidario.dto.foodStamp;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodStampHRDTO implements Serializable {

	
	private static final long serialVersionUID = 6265094492692448949L;

	@NotNull(message = "O campo 'Peso' é obrigatório")
	private Double weight;

	@NotNull(message = "O campo ' ' é obrigatório")
	private boolean isAble;

	@NotNull(message = "O campo 'Data' é obrigatório")
	private LocalDate date;
}
