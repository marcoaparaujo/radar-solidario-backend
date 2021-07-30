package com.radar.solidario.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table(name = "donate")
@Entity(name = "donate")
public class Donate implements Serializable {

	private static final long serialVersionUID = -8506472921384348180L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "date", nullable = false)
	@NotNull(message = "O campo 'Data' é obrigatório")
	private LocalDateTime date;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "family_id", referencedColumnName = "id", nullable = false)
	private Family family;
	
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "charity_id", referencedColumnName = "id", nullable = false)
	private Charity charity;
}
