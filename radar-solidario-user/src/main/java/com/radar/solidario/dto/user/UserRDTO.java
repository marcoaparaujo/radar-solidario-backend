package com.radar.solidario.dto.user;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.radar.solidario.constant.Gender;
import com.radar.solidario.dto.authentication.AuthenticationRPDTO;
import com.radar.solidario.dto.charity.CharityRDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRDTO implements Serializable {

	private static final long serialVersionUID = 473950750193819445L;

	@NotNull(message = "O campo 'Nome' é obrigatório")
	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres")
	private String name;

	@NotNull(message = "O campo 'Data de Nascimento' é obrigatório")
	private LocalDate birth;

	@NotNull(message = "O campo 'CPF' é obrigatório")
	@Size(min = 14, max = 14, message = "O campo 'CPF' deve conter 14 caracteres")
	private String cpf;

	@NotNull(message = "O campo 'Nº de Celular' é obrigatório")
	@Size(min = 16, max = 16, message = "O campo 'Nº de Celular' deve conter 16 caracteres")
	private String cell;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "O campo 'Gênero' é obrigatório")
	private Gender gender;
	
	@NotNull(message = "O campo 'Entidade' é obrigatório")
	private CharityRDTO charity;
	
	@NotNull(message = "O campo 'Autenticação' é obrigatório")
	private AuthenticationRPDTO authentication;
}
