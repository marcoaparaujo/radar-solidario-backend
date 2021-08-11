package com.radar.solidario.dto.donate;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.radar.solidario.dto.family.FamilyHRDTO;
import com.radar.solidario.dto.user.UserHRDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonateRDTO implements Serializable {

	private static final long serialVersionUID = 6162579132928914296L;

	@NotNull(message = "O campo 'Data' é obrigatório")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDateTime date;

	@NotNull(message = "O campo 'User' é obrigatório")
	private UserHRDTO user;

	@NotNull(message = "O campo 'Família' é obrigatório")
	private FamilyHRDTO family;

}
