package com.radar.solidario.dto.donate;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonatePrevisionDTO implements Serializable {

	private static final long serialVersionUID = -1823873286536074218L;

	@NotNull(message = "O campo 'Última data de doação' é obrigatório")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDateTime lastDonateDate;

	@NotNull(message = "O campo 'Próxima data de doação' é obrigatório")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDateTime nextDonateDate;

}
