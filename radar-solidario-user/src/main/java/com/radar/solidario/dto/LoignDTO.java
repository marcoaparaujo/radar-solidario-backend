package com.radar.solidario.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.radar.solidario.annotation.Password;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoignDTO implements Serializable {

	private static final long serialVersionUID = 4998140007987644213L;

	@NotNull(message = "O campo 'Email' é obrigatório")
	@Size(min = 6, max = 80, message = "O campo 'Email' deve conter entre 6 e 80 caracteres")
	@Email(message = "O campo 'Email' é inválido")
	private String email;

	@Size(min = 8, max = 40, message = "O campo 'Senha' deve conter entre 8 a 40 caracteres")
	@Password
	private String password;

	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}

}
