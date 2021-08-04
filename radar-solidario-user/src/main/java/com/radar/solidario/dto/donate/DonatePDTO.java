package com.radar.solidario.dto.donate;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.radar.solidario.dto.IdentificatorDTO;
import com.radar.solidario.dto.foodStamp.FoodStampHPDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonatePDTO implements Serializable {

	private static final long serialVersionUID = -5154874352122727603L;

	@NotNull(message = "O campo 'Usuário' é obrigatório")
	private IdentificatorDTO user;

	@NotNull(message = "O campo 'Família' é obrigatório")
	private IdentificatorDTO family;

	@NotNull(message = "O campo 'Entidade' é obrigatório")
	private IdentificatorDTO charity;

	@NotEmpty(message = "O campo 'Cestas' é obrigatório")
	private List<FoodStampHPDTO> foodStamps;
}
