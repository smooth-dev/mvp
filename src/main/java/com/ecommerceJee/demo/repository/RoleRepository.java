package com.ecommerceJee.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerceJee.demo.model.Role;

public interface RoleRepository
extends JpaRepository<Role, Integer> {
	List<Role> findByLibelle(String libelle);
	//List<EmployeeEntity> findByFirstName(String name);
	}

