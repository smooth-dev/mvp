package com.ecommerceJee.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.model.Panier;
import com.ecommerceJee.demo.repository.PanierRepository;

@Service
public class PanierService 
{
	 @Autowired
	    PanierRepository repository;
	 
	 public List<Panier> getAllPaniers()
	    {
	        List<Panier> articleList = repository.findAll();
	         
	        if(articleList.size() > 0) {
	            return articleList;
	        } else {
	            return new ArrayList<Panier>();
	        }
	    }
//	 public Panier getPanierById(Integer id) throws RecordNotFoundException
//	    {
//	        Optional<Panier> article = repository.findById(id);
//	         
//	        if(article.isPresent()) {
//	            return article.get();
//	        } else {
//	            throw new RecordNotFoundException("No Panier record exist for given id");
//	        }
//	    }
	 
	   public Panier getPanierById(Integer id) {
			boolean trouve = repository.existsById(id);
			if (!trouve)
				return null;
			return repository.getOne(id);
		}
	 
	 public Panier createOrUpdatePanier(Panier entity) throws RecordNotFoundException
	    {
	        Optional<Panier> article = repository.findById(entity.getId());
	         
	        if(article.isPresent())
	        {
	            Panier newEntity = article.get();
	            newEntity.setUser(entity.getUser());
	            newEntity.setArticles(entity.getArticles());
	         

	 
	            newEntity = repository.save(newEntity);
	             
	            return newEntity;
	        } else {
	            entity = repository.save(entity);
	             
	            return entity;
	        }
	    }
	 
	 
	 public List<Panier> getAllPaniers(int pageId, int size) {
			Page<Panier> result = repository.findAll(PageRequest.of(pageId, size, Direction.ASC, "user_ID"));
			return result.getContent();
		}
	 
	 public Optional<Panier> findById(Integer id_user) {
			Optional<Panier> list = repository.findById(id_user);
			return list;
		}
	 public List<Panier> sortBy(String fieldName) {
		 List<Panier>  list =repository.findAll(Sort.by(fieldName));
		return list;
	}
	 
		 
	 @Transactional
	    public void save(Panier entity) 
	    {
	       
	            repository.save(entity);     
	        
	    }
	 

	  public void deletePanierById(Integer id) throws RecordNotFoundException
	    {
	        Optional<Panier> role = repository.findById(id);
	         
	        if(role.isPresent())
	        {
	            repository.deleteById(id);
	        } else {
	            throw new RecordNotFoundException("No Panier record exist for given id");
	        }
	    }
	  
	  @Transactional
		public void delete(Integer id) {
			repository.deleteById(id);
		}
	  
	 @Transactional
		public void deleteAll() {
			repository.deleteAll();
		}
}