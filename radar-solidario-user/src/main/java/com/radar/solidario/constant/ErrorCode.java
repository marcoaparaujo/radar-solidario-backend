package com.radar.solidario.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	// Misc.
	INVALID_REQUEST("Requisição inválida"),
	VALIDATION_FAILED("A validação falhou"),
	INTERNAL_SERVER_ERROR("Erro interno do servidor"),
	ACCESS_DENIED("Acesso negado. Você deve estar autenticado no sistema para acessar o serviço solicitado"),

	// Token
	TOKEN_EMPTY("O token está vazio"),
	TOKEN_INVALID("O token enviado é inválido"),
	TOKEN_TYPE_INVALID("O tipo do token é inválido"),
	
	// Account
	LOCKED_ACCOUNT("A conta está banida"),
	WRONG_PASSWORD("A senha está incorreta"),

	// Not Found
	AUTHENTICATION_NOT_FOUND("A autenticação não foi encontrada"),
	
	// Not Changed
	AUTHENTICATION_NOT_CHANGED("Os dados da autenticação não foram alterados"),

	// Already Exists
	AUTHENTICATION_ALREADY_EXISTS("A autenticação já foi cadastrada");

	private final String message;
}
