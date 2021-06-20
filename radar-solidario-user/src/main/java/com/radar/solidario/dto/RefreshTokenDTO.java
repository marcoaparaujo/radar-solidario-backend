package com.radar.solidario.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshTokenDTO  implements Serializable{

	private static final long serialVersionUID = -4787988584324791683L;

	@NotNull(message = "O campo 'Token' não pode ser vazio.")
	private String token;

	@Override
	public String toString() {
		return "RefreshTokenDTO [token=" + token + "]";
	}
}
