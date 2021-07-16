package com.radar.solidario.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	// Misc.
	VALIDATION_FAILED("A validação falhou"),
	INVALID_REQUEST("A requisição efetuada é inválida"),
	INTERNAL_SERVER_ERROR("Um erro ocorreu ao processar a requisição, tente novamente em alguns instantes"),
	ACCESS_DENIED("Acesso negado. Você deve estar autenticado no sistema para acessar o serviço solicitado"),

	// Token
	TOKEN_EMPTY("O token está vazio"),
	TOKEN_INVALID("O token enviado é inválido"),
	TOKEN_TYPE_INVALID("O tipo do token é inválido"),
	
	// Account
	LOCKED_ACCOUNT("A conta está indisponível, contate um administrador para maiores informações"),
	WRONG_PASSWORD("O email e/ou senha estão incorretos"),

	// Not Found
	USER_NOT_FOUND("O usuário solicitado não foi encontrado"),
	FAMILY_NOT_FOUND("A família solicitada não foi encontrada"),
	FOOD_STAMP_NOT_FOUND("A cesta solicitada não foi encontrada"),
	AUTHENTICATION_NOT_FOUND("A autenticação solicitada não foi encontrada"),
	DONATE_NOT_FOUND("Doação solicitada não foi encontrada"),
	CHARITY_NOT_FOUND("Caridade solicitada não foi encontrada"),

	
	// Not Changed
	AUTHENTICATION_NOT_CHANGED("Os dados da autenticação não foram alterados"),

	// Already Exists
	USER_ALREADY_EXISTS("O usuário solicitado já se encontra na nossa base de dados"),
	FAMILY_ALREADY_EXISTS("A família solicitada já se encontra na nossa base de dados"),
	AUTHENTICATION_ALREADY_EXISTS("A autenticação solicitado já se encontra na nossa base de dados"),
	CHARITY_ALREADY_EXISTS("A caridade solicitado já se encontra na nossa base de dados");
	
	private final String message;
}
