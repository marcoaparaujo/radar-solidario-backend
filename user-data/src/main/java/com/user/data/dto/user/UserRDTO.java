package com.user.data.dto.user;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRDTO implements Serializable {

	private static final long serialVersionUID = -5477411815992393125L;

	@NotNull(message = "O campo 'Nome' é obrigatório")
	@Size(min = 1, max = 70, message = "O campo 'Nome' deve conter entre 1 e 70 caracteres")
	private String name;

	@NotBlank(message = "O campo 'NIS' é obrigatório")
	@Size(min = 11, max = 11, message = "O campo 'NIS' deve conter 11 caracteres")
	private String nis;

	@NotNull(message = "O campo 'Data de Nascimento' é obrigatório")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate birth;
}
