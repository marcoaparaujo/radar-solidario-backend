package com.radar.solidario.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

import com.radar.solidario.constant.AuthenticationRole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTokenDTO extends RefreshTokenDTO implements Serializable {

	private static final long serialVersionUID = -4787988584324791683L;

	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres.")
	private String name;

	@Size(min = 6, max = 80, message = "O campo 'Email' deve conter entre 6 e 80 caracteres.")
	private String email;

	@Enumerated(EnumType.STRING)
	private AuthenticationRole role;

	@Override
	public String toString() {
		return "CreateTokenDTO [email=" + email + ", role=" + role + ", token=" + getToken() + "]";
	}

}
