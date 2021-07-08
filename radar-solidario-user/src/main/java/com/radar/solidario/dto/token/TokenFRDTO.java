package com.radar.solidario.dto.token;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.radar.solidario.constant.AuthenticationRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenFRDTO implements Serializable {

	private static final long serialVersionUID = -9191364978079948376L;

	@NotNull(message = "O campo 'Nome do Usuário' é obrigatório")
	@Size(min = 1, max = 200, message = "O campo 'Nome do Usuário' deve conter entre 1 e 200 caracteres")
	private String name;

	@NotEmpty(message = "O campo 'Token' é obrigatório")
	private String token;

	@NotNull(message = "O campo 'Cargos' é obrigatório")
	private List<AuthenticationRole> roles;
}
