package com.radar.solidario.dto.address;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.radar.solidario.annotation.CEP;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressPDTO implements Serializable {

	private static final long serialVersionUID = 7928629626907491236L;

	@NotBlank(message = "O campo 'Nome' é obrigatório")
	@Size(min = 1, max = 70, message = "O campo 'Nome' deve conter entre 1 e 70 caracteres")
	private String name;

	@CEP
	@Size(min = 9, max = 9, message = "O campo 'CEP' deve conter 9 caracteres")
	private String cep;

	@NotBlank(message = "O campo 'Rua' é obrigatório")
	@Size(min = 1, max = 255, message = "O campo 'Rua' deve conter entre 1 e 255 caracteres")
	private String street;

	@NotBlank(message = "O campo 'Bairro' é obrigatório")
	@Size(min = 1, max = 255, message = "O campo 'Bairro' deve conter entre 1 e 255 caracteres")
	private String neighborhood;

	@NotNull(message = "O campo 'Número' é obrigatório")
	private Integer number;

	@NotBlank(message = "O campo 'Complemento' é obrigatório")
	@Size(max = 255, message = "O campo 'Complemento' deve conter no máximo 255 caracteres")
	private String complement;

	@NotBlank(message = "O campo 'Nº de Telefone' é obrigatório")
	@Size(min = 13, max = 13, message = "O campo 'Nº de Telefone' deve conter 13 caracteres")
	private String phone;
}
