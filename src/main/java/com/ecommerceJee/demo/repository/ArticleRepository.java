package com.ecommerceJee.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ecommerceJee.demo.model.Article;

@Repository
public interface ArticleRepository
extends JpaRepository<Article, Integer> {
List<Article> findByLibelle(String libelle);
//List<EmployeeEntity> findByFirstName(String name);
}
