package com.radar.solidario.dto.donate;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.radar.solidario.dto.family.FamilyRDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonateDTO implements Serializable {

	private static final long serialVersionUID = -7154393671691049942L;

	@NotNull(message = "O campo 'Ultima Doação' é obrigatório")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate lastDonation;
	
	@NotNull(message = "O campo 'Proxima Doação' é obrigatório")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate nextDonation;
	
	@NotNull(message = "O campo 'Liberado' é obrigatório")
	private Boolean released;

	@NotNull(message = "O campo 'Família' é obrigatório")
	private FamilyRDTO family;
	
}
