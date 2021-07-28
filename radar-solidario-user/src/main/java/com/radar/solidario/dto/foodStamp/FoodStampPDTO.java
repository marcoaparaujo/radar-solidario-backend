package com.radar.solidario.dto.foodStamp;

import java.io.Serializable;
import java.time.LocalDate;

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
public class FoodStampPDTO implements Serializable {

	private static final long serialVersionUID = 368742626815121569L;

	@NotNull(message = "O campo 'Peso' é obrigatório")
	private Double weight;

	@NotNull(message = "O campo 'Quantidade' é obrigatório")
	private Integer lenght;

	@NotNull(message = "O campo 'Disponível' é obrigatório")
	private Boolean isAble;

	@NotNull(message = "O campo 'Data' é obrigatório")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3", shape = JsonFormat.Shape.STRING)
	private LocalDate date;
}
