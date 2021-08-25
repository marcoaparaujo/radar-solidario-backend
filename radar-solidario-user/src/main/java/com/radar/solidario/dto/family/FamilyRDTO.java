package com.radar.solidario.dto.family;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.radar.solidario.dto.address.AddressPDTO;
import com.radar.solidario.dto.donate.DonatePrevisionDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyRDTO implements Serializable {

	private static final long serialVersionUID = -5286894800335094895L;

	@NotNull(message = "O campo 'Id' é obrigatório")
	private Long id;

	@NotBlank(message = "O campo 'Nome do Responsável' é obrigatório")
	@Size(max = 255, message = "O campo 'Nome do Responsável' deve conter no máximo 255 caracteres")
	private String head;

	@NotBlank(message = "O campo 'NIS' é obrigatório")
	@Size(min = 11, max = 11, message = "O campo 'NIS' deve conter 11 caracteres")
	private String nis;

	@CPF(message = "O campo 'CPF' é inválido")
	@Size(min = 14, max = 14, message = "O campo 'CPF' deve conter 14 caracteres")
	private String cpf;

	@NotNull(message = "O campo 'Endereço' é obrigatório")
	private AddressPDTO address;

	@NotNull(message = "O campo 'Previsão de doação' é obrigatório")
	private DonatePrevisionDTO donatePrevision;
}
