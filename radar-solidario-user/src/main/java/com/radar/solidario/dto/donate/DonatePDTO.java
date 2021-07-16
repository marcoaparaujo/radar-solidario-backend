package com.radar.solidario.dto.donate;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.radar.solidario.entity.Family;
import com.radar.solidario.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonatePDTO implements Serializable {

	private static final long serialVersionUID = 8445644966121259417L;

	@NotNull(message = "O campo 'Data' é obrigatório")
	private LocalDateTime date;

	@NotNull(message = "O campo 'User' é obrigatório")
	private User user;

	@NotNull(message = "O campo 'Família' é obrigatório")
	private Family family;
}
