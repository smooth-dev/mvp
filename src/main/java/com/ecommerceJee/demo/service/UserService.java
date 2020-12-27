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
import com.ecommerceJee.demo.model.User;
import com.ecommerceJee.demo.repository.UserRepository;

@Service
public class UserService 
{
	 @Autowired
	    UserRepository repository;
	 
	 public List<User> getAllUsers()
	    {
	        List<User> UserList = repository.findAll();
	         
	        if(UserList.size() > 0) {
	            return UserList;
	        } else {
	            return new ArrayList<User>();
	        }
	    }
//	 public User getUserById(Integer id) throws RecordNotFoundException
//	    {
//	        Optional<User> User = repository.findById(id);
//	         
//	        if(User.isPresent()) {
//	            return User.get();
//	        } else {
//	            throw new RecordNotFoundException("No User record exist for given id");
//	        }
//	    }
	 
	   public User getUserById(Integer id) {
			boolean trouve = repository.existsById(id);
			if (!trouve)
				return null;
			return repository.getOne(id);
		}
	 
	 public User createOrUpdateUser(User entity) throws RecordNotFoundException
	    {
	        Optional<User> User = repository.findById(entity.getId());
	         
	        if(User.isPresent())
	        {
	            User newEntity = User.get();
	            newEntity.setNom(entity.getNom());
	            newEntity.setPrenom(entity.getPrenom());
	            newEntity.setPseudo(entity.getPseudo());

	 
	            newEntity = repository.save(newEntity);
	             
	            return newEntity;
	        } else {
	            entity = repository.save(entity);
	             
	            return entity;
	        }
	    }
	 
	 
	 public List<User> getAllUsers(int pageId, int size) {
			Page<User> result = repository.findAll(PageRequest.of(pageId, size, Direction.ASC, "nom"));
			return result.getContent();
		}
	 
	 public List<User> findByNom(String nom) {
			List<User> list = repository.findByNom(nom);
			return list;
		}
	 public List<User> sortBy(String fieldName) {
		 List<User>  list =repository.findAll(Sort.by(fieldName));
		return list;
	}
	 
		 
	 @Transactional
	    public void save(User entity) 
	    {
	       
	            repository.save(entity);     
	        
	    }
	 

	  public void deleteUserById(Integer id) throws RecordNotFoundException
	    {
	        Optional<User> role = repository.findById(id);
	         
	        if(role.isPresent())
	        {
	            repository.deleteById(id);
	        } else {
	            throw new RecordNotFoundException("No User record exist for given id");
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