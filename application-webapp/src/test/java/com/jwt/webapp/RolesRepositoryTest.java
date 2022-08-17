package com.jwt.webapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.jwt.dto.RolesDTO;
import com.jwt.service.controller.RolesService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RolesRepositoryTest {
	@Autowired 
	private RolesService service;
    
    @Test
    public void testCreateRoles() {
        RolesDTO admin = new RolesDTO();
        admin.setName("ROLE_ADMIN");
        RolesDTO editor = new RolesDTO();
        editor.setName("ROLE_EDITOR");
        RolesDTO customer = new RolesDTO();
        customer.setName("ROLE_CUSTOMER");
         
        service.save(admin);
        //service.save(customer);
        //service.save(editor);
    }}
