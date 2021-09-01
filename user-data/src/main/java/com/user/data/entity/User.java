package com.user.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.user.data.util.StringEncryptor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
@Entity(name = "_user")
public class User implements Serializable {

	private static final long serialVersionUID = 4881957096165205349L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	@Convert(converter = StringEncryptor.class)
	private String name;

	@Column(name = "nis", unique = true, nullable = false)
	@Convert(converter = StringEncryptor.class)
	private String nis;

	@Column(name = "birth", nullable = false)
	@Convert(converter = StringEncryptor.class)
	private String birth;
}
