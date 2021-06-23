package com.radar.solidario.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.radar.solidario.dto.role.RoleFPDTO;
import com.radar.solidario.dto.role.RoleFRDTO;
import com.radar.solidario.dto.role.RoleHRDTO;
import com.radar.solidario.dto.role.RolePDTO;
import com.radar.solidario.dto.role.RoleRDTO;
import com.radar.solidario.service.RoleService;
import com.radar.solidario.util.Response;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@NoArgsConstructor
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	@Cacheable("role")
	public ResponseEntity<Response<Page<RoleHRDTO>>> findAll(Pageable pageable) {
		log.info("Start - RoleController.findAll - Pageable: {}", pageable);
		Response<Page<RoleHRDTO>> response = new Response<>();

		Page<RoleHRDTO> roles = this.roleService.findAll(pageable);
		response.setData(roles);

		log.info("End - RoleController.findAll - Page<RoleHRDTO>: {}", roles);
		return ResponseEntity.ok(response);
	}

	@Cacheable("role")
	@GetMapping(params = "id")
	public ResponseEntity<Response<RoleFRDTO>> findById(@RequestParam Long id) {
		log.info("Start - RoleController.findById - Id: {}", id);
		Response<RoleFRDTO> response = new Response<>();

		RoleFRDTO role = this.roleService.findById(id);
		response.setData(role);

		log.info("End - RoleController.findById - RoleFRDTO: {}", role);
		return ResponseEntity.ok(response);
	}

	@Cacheable("role")
	@GetMapping(params = "name")
	public ResponseEntity<Response<RoleFRDTO>> findByName(@RequestParam String name) {
		log.info("Start - RoleController.findByName - Name: {}", name);
		Response<RoleFRDTO> response = new Response<>();

		RoleFRDTO role = this.roleService.findByName(name);
		response.setData(role);

		log.info("End - RoleController.findByName - RoleFRDTO: {}", role);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<RoleRDTO>> include(@RequestBody @Valid RolePDTO rolePDTO) {
		log.info("Start - RoleController.include - RolePDTO: {}", rolePDTO);
		Response<RoleRDTO> response = new Response<>();

		RoleRDTO role = this.roleService.include(rolePDTO);
		response.setData(role);

		log.info("End - RoleController.include - RoleRDTO: {}", role);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping
	public ResponseEntity<Response<RoleFPDTO>> edit(@RequestBody @Valid RoleFPDTO roleFPDTO) {
		log.info("Start - RoleController.edit - RoleFPDTO: {}", roleFPDTO);
		Response<RoleFPDTO> response = new Response<>();

		RoleFPDTO city = this.roleService.edit(roleFPDTO);
		response.setData(city);

		log.info("End - RoleController.edit - RoleFPDTO: {}", city);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(params = "id")
	public ResponseEntity<Response<RoleHRDTO>> remove(@RequestParam Long id) {
		log.info("Start - RoleController.remove - Id: {}", id);
		Response<RoleHRDTO> response = new Response<>();

		RoleHRDTO role = this.roleService.remove(id);
		response.setData(role);

		log.info("End - RoleController.remove - RoleHRDTO: {}", role);
		return ResponseEntity.ok(response);
	}
}
