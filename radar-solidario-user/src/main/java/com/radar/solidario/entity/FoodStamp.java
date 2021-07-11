package com.radar.solidario.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food_stamp")
@Entity(name = "food_stamp")
public class FoodStamp implements Serializable {

	private static final long serialVersionUID = -8506472921384348180L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "lenght", nullable = false)
	@NotNull(message = "O campo 'Comprimento' é obrigatório")
	private Integer lenght;

	@Column(name = "weight", nullable = false)
	@NotNull(message = "O campo 'Peso' é obrigatório")
	private Double weight;

	@Column(name = "isAlble", nullable = false)
	@NotNull(message = "O campo ' ' é obrigatório")
	private Boolean isAble;

	@Column(name = "date", nullable = false, columnDefinition = "DATE")
	@NotNull(message = "O campo 'Data' é obrigatório")
	private LocalDate date;

//	@ToString.Exclude
//	@OneToOne(mappedBy = "donate")
//	private User user;
}
