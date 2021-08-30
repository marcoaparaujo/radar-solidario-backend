package com.radar.solidario.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

	@Column(name = "length", nullable = false)
	@NotNull(message = "O campo 'Comprimento' é obrigatório")
	private Integer length;

	@Column(name = "weight", nullable = false, unique = true)
	@NotNull(message = "O campo 'Peso' é obrigatório")
	private Double weight;

	@Column(name = "able", nullable = false)
	@NotNull(message = "O campo 'Disponível' é obrigatório")
	private Boolean isAble;

	@Column(name = "date", nullable = false, columnDefinition = "DATE")
	@NotNull(message = "O campo 'Data' é obrigatório")
	private LocalDate date;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "donate_id", referencedColumnName = "id")
	private Donate donate;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "charity_id", referencedColumnName = "id", nullable = false)
	private Charity charity;
}
