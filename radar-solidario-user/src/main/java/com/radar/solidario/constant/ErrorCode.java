package com.radar.solidario.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	// Misc.
	INVALID_REQUEST("A requisição efetuada é inválida"),
	VALIDATION_FAILED("A validação falhou"),
	INTERNAL_SERVER_ERROR("Ocorreu um erro interno nos serviçoes, tente novamente em alguns instantes"),
	ACCESS_DENIED("Acesso negado. Você deve estar autenticado no sistema para acessar o serviço solicitado"),

	// Token
	TOKEN_EMPTY("O token está vazio"),
	TOKEN_INVALID("O token enviado é inválido"),
	TOKEN_TYPE_INVALID("O tipo do token é inválido"),
	
	// Account
	LOCKED_ACCOUNT("A conta está disponível, contate um administrador para maiores informações"),
	WRONG_PASSWORD("O email e/ou senha estão incorretos"),

	// Not Found
	ROLE_NOT_FOUND("O cargo solicitado não foi encontrado"),
	USER_NOT_FOUND("O usuário solicitado não foi encontrado"),
	FAMILY_NOT_FOUND("A família solicitada não foi encontrada"),
	FOODSTAMP_NOT_FOUND("A cesta solicitada não foi encontrada"),
	AUTHENTICATION_NOT_FOUND("A autenticação solicitada não foi encontrada"),
	
	// Not Changed
	ROLE_NOT_CHANGED("Os dados do cargo não foram alterados"),
	AUTHENTICATION_NOT_CHANGED("Os dados da autenticação não foram alterados"),

	// Already Exists
	ROLE_ALREADY_EXISTS("O cargo solicitado já se encontra na nossa base de dados"),
	USER_ALREADY_EXISTS("O usuário solicitado já se encontra na nossa base de dados"),
	AUTHENTICATION_ALREADY_EXISTS("A autenticação solicitado já se encontra na nossa base de dados");

	private final String message;
}
