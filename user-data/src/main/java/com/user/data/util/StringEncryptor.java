package com.user.data.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.user.data.constant.EncryptorContants;

public final class StringEncryptor extends EncryptorContants implements AttributeConverter<String, String> {

	private Key key;
	private Cipher cipher;
	private Provider provider;

	public StringEncryptor() throws Exception {
		this.provider = new BouncyCastleProvider();
		this.key = this.buildKey(PASSWORD.toCharArray());

		this.cipher = Cipher.getInstance(ALGORITHM, this.provider);
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

	private Key buildKey(char[] password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest digester = MessageDigest.getInstance(PASSWORD_HASH_ALGORITHM, this.provider);
		digester.update(String.valueOf(password).getBytes(StandardCharsets.UTF_8));

		byte[] byteKey = digester.digest();
		return new SecretKeySpec(byteKey, ALGORITHM);
	}
}
