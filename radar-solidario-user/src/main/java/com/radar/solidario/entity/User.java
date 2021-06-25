package com.radar.solidario.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.radar.solidario.constant.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"user\"")
@Entity(name = "\"user\"")
public class User implements Serializable {

	private static final long serialVersionUID = -4520661629094456421L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres")
	private String name;

	@Column(name = "birth", nullable = false)
	@NotNull(message = "O campo 'Data de Nascimento' é obrigatório")
	private LocalDate birth;

	@Column(name = "cpf", unique = true, nullable = false)
	@Size(min = 14, max = 14, message = "O campo 'CPF' deve conter 14 caracteres")
	private String cpf;

	@Column(name = "cell", nullable = true)
	@Size(min = 16, max = 16, message = "O campo 'Nº de Celular' deve conter 16 caracteres")
	private String cell;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	private Gender gender;

	@ToString.Exclude
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Authentication authentication;

//	@ToString.Exclude
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinTable(name = "donate_user", joinColumns = {
//			@JoinColumn(name = "donate_id", referencedColumnName = "id") }, inverseJoinColumns = {
//					@JoinColumn(name = "user_id", referencedColumnName = "id") })
//	private Donate donate;

//	@ToString.Exclude
//	@ManyToOne
//	@JoinColumn(name = "charity_id", referencedColumnName = "id", nullable = false)
//	private Charity charity;
}
