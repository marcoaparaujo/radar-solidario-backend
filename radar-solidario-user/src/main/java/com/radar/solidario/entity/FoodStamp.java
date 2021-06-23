package com.radar.solidario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "food-stamp")
@Entity(name = "food-stamp")
public class FoodStamp implements Serializable {

	private static final long serialVersionUID = -8506472921384348180L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date", nullable = false)
	@NotNull(message = "O campo 'Data' é obrigatório")
	private Integer date;

//	@ToString.Exclude
//	@OneToOne(mappedBy = "donate")
//	private User user;
}
