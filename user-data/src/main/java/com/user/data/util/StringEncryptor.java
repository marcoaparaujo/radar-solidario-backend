package com.user.data.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:security.properties")
public final class StringEncryptor implements AttributeConverter<String, String> {

	private final Key key;
	private final Cipher cipher;

	@Value("${encryptor.string.type}")
	private String type;

	@Value("${encryptor.string.secret}")
	private byte[] secret;

	public StringEncryptor() throws Exception {
		this.key = new SecretKeySpec(this.secret, this.type);
		this.cipher = Cipher.getInstance(this.type);
	}

	@Override
	public String convertToDatabaseColumn(String attribute) {
		try {
			this.cipher.init(Cipher.ENCRYPT_MODE, this.key);
			return Base64.getEncoder().encodeToString(this.cipher.doFinal(attribute.getBytes()));
		} catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public String convertToEntityAttribute(String data) {
		try {
			this.cipher.init(Cipher.DECRYPT_MODE, this.key);
			return new String(this.cipher.doFinal(Base64.getDecoder().decode(data)));
		} catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
			throw new IllegalStateException(e);
		}
	}
}
