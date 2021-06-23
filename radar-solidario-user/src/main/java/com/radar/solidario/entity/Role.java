package com.radar.solidario.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "role")
@Entity(name = "role")
public class Role implements Serializable {

	private static final long serialVersionUID = 6153233421671453502L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", unique = true, nullable = false)
	@Size(max = 255, message = "O campo 'Cargo' deve conter no máximo 255 caracteres")
	private String name;

	@ToString.Exclude
	@ManyToMany(mappedBy = "role")
	private List<Authentication> authentication;
}
