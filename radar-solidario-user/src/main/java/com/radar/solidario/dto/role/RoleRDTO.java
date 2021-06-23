package com.radar.solidario.dto.role;

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
public class RoleRDTO implements Serializable {

	private static final long serialVersionUID = -3055869561710211569L;

	@NotNull(message = "O campo 'Id' é obrigatório")
	private Long id;

	@NotNull(message = "O campo 'Cargo' é obrigatório")
	@Size(max = 255, message = "O campo 'Cargo' deve conter no máximo 255 caracteres")
	private String name;
}
