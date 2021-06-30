package com.radar.solidario.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@Table(name = "family")
@Entity(name = "family")
public class Family implements Serializable {

	private static final long serialVersionUID = -5952878705537378581L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "head", nullable = false)
	@Size(max = 255, message = "O campo 'Nome do Responsável' deve conter no máximo 255 caracteres")
	private String head;

	@Column(name = "nis", nullable = false)
	@Size(min = 11, max = 11, message = "O campo 'NIS' deve conter 11 caracteres")
	private Long nis;
	
	@Column(name = "cpf", unique = true, nullable = false)
	@Size(min = 14, max = 14, message = "O campo 'CPF' deve conter 14 caracteres")
	private String cpf;

	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "address_family", joinColumns = {
			@JoinColumn(name = "address_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "family_id", referencedColumnName = "id") })
	private Address address;

	@ToString.Exclude
	@OneToMany(mappedBy = "family")
	private List<Donate> donate;
}
