package com.ecommerceJee.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerceJee.demo.model.Panier;

public interface PanierRepository
extends JpaRepository<Panier, Integer> {
	Optional<Panier> findById(Integer id);
	//List<EmployeeEntity> findByFirstName(String name);
}