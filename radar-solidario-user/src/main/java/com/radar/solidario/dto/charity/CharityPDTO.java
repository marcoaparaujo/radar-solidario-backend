package com.radar.solidario.dto.charity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharityPDTO implements Serializable {

	private static final long serialVersionUID = 2623477206517762693L;
	
	@NotNull(message = "O campo 'Entidade' é obrigatório")
	@Size(max = 255, message = "O campo 'Entidade' deve conter no máximo 255 caracteres")
	private String name;
}
