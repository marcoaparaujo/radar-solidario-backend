package com.radar.solidario.dto.family;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.radar.solidario.dto.address.AddressPDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyPDTO implements Serializable {

	private static final long serialVersionUID = 844760076946075843L;

	@NotBlank(message = "O campo 'Nome do Responsável' é obrigatório")
	@Size(max = 255, message = "O campo 'Nome do Responsável' deve conter no máximo 255 caracteres")
	private String head;

	@NotBlank(message = "O campo 'NIS' é obrigatório")
	@Size(min = 11, max = 11, message = "O campo 'NIS' deve conter 11 caracteres")
	private Long nis;

	@CPF(message = "O campo 'CPF' é inválido")
	@Size(min = 14, max = 14, message = "O campo 'CPF' deve conter 14 caracteres")
	private String cpf;

	@NotNull(message = "O campo 'Endereço' é obrigatório")
	private AddressPDTO address;
}
