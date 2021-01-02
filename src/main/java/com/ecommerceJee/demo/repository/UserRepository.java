package com.ecommerceJee.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerceJee.demo.model.User;

@Repository
public interface UserRepository
extends JpaRepository<User, Integer> {
List<User> findByNom(String nom);
User findByUsername(String userName);

//List<EmployeeEntity> findByFirstName(String name);
}
