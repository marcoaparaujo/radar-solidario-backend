package com.radar.solidario.dto.foodStamp;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.radar.solidario.dto.IdentificatorDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodStampPDTO implements Serializable {

	private static final long serialVersionUID = 368742626815121569L;

	@NotNull(message = "O campo 'Peso' é obrigatório")
	private Double weight;

	@NotNull(message = "O campo 'Quantidade' é obrigatório")
	private Integer length;

	@NotNull(message = "O campo 'Disponível' é obrigatório")
	private Boolean isAble;
	
	@NotNull(message = "O campo 'Entidade' é obrigatório")
	private IdentificatorDTO charity;
}
