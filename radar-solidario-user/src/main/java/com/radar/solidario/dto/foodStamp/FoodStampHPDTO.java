package com.radar.solidario.dto.foodStamp;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodStampHPDTO implements Serializable {

	private static final long serialVersionUID = 6265094492692448949L;

	@NotNull(message = "O campo 'Peso' é obrigatório")
	private Double weight;

	@NotNull(message = "O campo 'Quantidade' é obrigatório")
	private Integer length;
}
