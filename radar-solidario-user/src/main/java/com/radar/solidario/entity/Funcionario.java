package com.radar.solidario.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable{
	
	private static final long serialVersionUID = -5354811086819683248L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres.")
	private String name;

	@Column(name = "birth", nullable = false)
	@NotNull(message = "O campo 'Data de Nascimento' é obrigatório.")
	@Temporal(TemporalType.DATE)
	private LocalDate aniversario;
	
	@OneToOne(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Authentication authentication;

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name +  ", aniversário=" + aniversario + "]";
	}
	
}
