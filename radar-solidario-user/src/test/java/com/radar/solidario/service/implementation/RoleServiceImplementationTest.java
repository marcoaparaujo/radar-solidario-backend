package com.radar.solidario.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;

import com.radar.solidario.dto.role.RoleHRDTO;
import com.radar.solidario.entity.Role;
import com.radar.solidario.repository.RoleRepository;
import com.radar.solidario.service.RoleService;

import properties.pagination.PaginationInstance;
import properties.role.RoleInstance;
import properties.role.RoleProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("ServiceImplementation - Role")
public class RoleServiceImplementationTest extends RoleProperties {

	@MockBean
	private RoleRepository roleRepository;

	@Autowired
	private RoleService roleService;

	@Mock
	private Role role;

	@BeforeEach
	public void init() {
		this.role = RoleInstance.instaceVoluntary();
	}

	@Test
	@DisplayName("Find all roles")
	void findAll() {
		List<Role> roles = new ArrayList<>();
		roles.add(this.role);

		Page<Role> pageRoles = new PageImpl<Role>(roles);
		when(this.roleRepository.findAll(PaginationInstance.PAGE_REQUEST_DESC)).thenReturn(pageRoles);

		Page<RoleHRDTO> returnedRoles = this.roleService.findAll(PaginationInstance.PAGE_REQUEST_DESC);
		Optional<RoleHRDTO> optRoles = returnedRoles.stream().findFirst();

		assertEquals(NAME_VOLUNTARY, optRoles.get().getName());
	}
}
