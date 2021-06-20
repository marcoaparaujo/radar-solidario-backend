package com.radar.solidario.converter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {

	private String text;
	private String title;

	public static Error convert(final String title, final String text) {
		return new Error(title, text);
	}
}
