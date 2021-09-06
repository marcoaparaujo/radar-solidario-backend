package com.user.data.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	// Misc.
	INVALID_DATE("A data deve ser igual a data atual"),
	INVALID_REQUEST("A requisição efetuada é inválida"),
	INTERNAL_SERVER_ERROR("Um erro ocorreu ao processar a requisição, tente novamente em alguns instantes"),

	// Not Found
	USER_NOT_FOUND("O usuário solicitado não foi encontrado");
	
	private final String message;
}
