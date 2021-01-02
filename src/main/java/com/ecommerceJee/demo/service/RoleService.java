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
import com.ecommerceJee.demo.domaine.RoleConverter;
import com.ecommerceJee.demo.domaine.RoleVo;
import com.ecommerceJee.demo.domaine.UserConverter;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.model.Role;
import com.ecommerceJee.demo.repository.RoleRepository;

@Service
public class RoleService 
{
	 @Autowired
	    RoleRepository repository;
	 
	 public List<RoleVo> getAllRoles()
	    {
	        List<RoleVo> roleList = RoleConverter.toVoList(repository.findAll());
	         
	        if(roleList.size() > 0) {
	            return roleList;
	        } else {
	            return new ArrayList<RoleVo>();
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
	 
	   public RoleVo getRoleById(Integer id) {
			boolean trouve = repository.existsById(id);
			if (!trouve)
				return null;
			return RoleConverter.toVo(repository.getOne(id));
		}
	 
	 public RoleVo createOrUpdateRole(RoleVo entity) throws RecordNotFoundException
	    {
	        Optional<Role> role = repository.findById(entity.getId());
	         
	        if(role.isPresent())
	        {
	            Role newEntity = role.get();
	            newEntity.setLibelle(entity.getLibelle());
	            newEntity.setUsers(UserConverter.toBoList(entity.getUsers()));

	 
	            newEntity = repository.save(newEntity);
	             
	            return entity;
	        } else {
	            repository.save(RoleConverter.toBo(entity));
	            return entity;
	        }
	    }
	 
	 
	 public List<RoleVo> getAllRoles(int pageId, int size) {
			Page<Role> result = repository.findAll(PageRequest.of(pageId, size, Direction.ASC, "libelle"));
			return RoleConverter.toVoList(result.getContent());
		}
	 
	 public List<RoleVo> findByLibelle(String libelle) {
			List<Role> list = repository.findByLibelle(libelle);
			return RoleConverter.toVoList(list);
		}
	 
	 public List<RoleVo> sortBy(String fieldName) {
		 List<Role>  list =repository.findAll(Sort.by(fieldName));
			return RoleConverter.toVoList(list);
	}
	 
		 
	 @Transactional
	    public Role save(Role entity) 
	    {
	       
	            return repository.save(entity);     
	        
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