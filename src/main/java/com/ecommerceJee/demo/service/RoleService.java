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
import com.ecommerceJee.demo.model.Role;
import com.ecommerceJee.demo.repository.RoleRepository;

@Service
public class RoleService 
{
	 @Autowired
	    RoleRepository repository;
	 
	 public List<Role> getAllRoles()
	    {
	        List<Role> roleList = repository.findAll();
	         
	        if(roleList.size() > 0) {
	            return roleList;
	        } else {
	            return new ArrayList<Role>();
	        }
	    }
//	 public Role getRoleById(Integer id) throws RecordNotFoundException
//	    {
//	        Optional<Role> role = repository.findById(id);
//	         
//	        if(role.isPresent()) {
//	            return role.get();
//	        } else {
//	            throw new RecordNotFoundException("No Role record exist for given id");
//	        }
//	    }
	 
	   public Role getRoleById(Integer id) {
			boolean trouve = repository.existsById(id);
			if (!trouve)
				return null;
			return repository.getOne(id);
		}
	 
	 public Role createOrUpdateRole(Role entity) throws RecordNotFoundException
	    {
	        Optional<Role> role = repository.findById(entity.getId());
	         
	        if(role.isPresent())
	        {
	            Role newEntity = role.get();
	            newEntity.setLibelle(entity.getLibelle());
	           

	 
	            newEntity = repository.save(newEntity);
	             
	            return newEntity;
	        } else {
	            entity = repository.save(entity);
	             
	            return entity;
	        }
	    }
	 
	 
	 public List<Role> getAllRoles(int pageId, int size) {
			Page<Role> result = repository.findAll(PageRequest.of(pageId, size, Direction.ASC, "libelle"));
			return result.getContent();
		}
	 
	 public List<Role> findByLibelle(String libelle) {
			List<Role> list = repository.findByLibelle(libelle);
			return list;
		}
	 public List<Role> sortBy(String fieldName) {
		 List<Role>  list =repository.findAll(Sort.by(fieldName));
		return list;
	}
	 
		 
	 @Transactional
	    public void save(Role entity) 
	    {
	       
	            repository.save(entity);     
	        
	    }
	 

	  public void deleteRoleById(Integer id) throws RecordNotFoundException
	    {
	        Optional<Role> role = repository.findById(id);
	         
	        if(role.isPresent())
	        {
	            repository.deleteById(id);
	        } else {
	            throw new RecordNotFoundException("No Role record exist for given id");
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