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
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@Entity(name = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = 3259874520308167531L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	@Size(min = 1, max = 70, message = "O campo 'Nome' deve conter entre 1 e 70 caracteres")
	private String name;

	@Column(name = "cep", nullable = false)
	@Size(min = 9, max = 9, message = "O campo 'CEP' deve conter 9 caracteres")
	private String cep;

	@Column(name = "road", nullable = false)
	@Size(min = 1, max = 255, message = "O campo 'Rua' deve conter entre 1 e 255 caracteres")
	private String road;

	@Column(name = "neighborhood", nullable = false)
	@Size(min = 1, max = 255, message = "O campo 'Bairro' deve conter entre 1 e 255 caracteres")
	private String neighborhood;

	@Column(name = "number", nullable = false)
	@NotNull(message = "O campo 'Número' é obrigatório")
	private Integer number;

	@Column(name = "complement", nullable = true)
	@Size(max = 255, message = "O campo 'Complemento' deve conter no máximo 255 caracteres")
	private String complement;

	@Column(name = "phone", nullable = true)
	@Size(min = 13, max = 13, message = "O campo 'Nº de Telefone' deve conter 13 caracteres")
	private String phone;

	@ToString.Exclude
	@OneToOne(mappedBy = "address")
	private Family family;

	@ToString.Exclude
	@OneToOne(mappedBy = "address")
	private Charity charity;
}
