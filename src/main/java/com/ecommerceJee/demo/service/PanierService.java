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
import com.ecommerceJee.demo.domaine.ArticleConverter;
import com.ecommerceJee.demo.domaine.PanierConverter;
import com.ecommerceJee.demo.domaine.PanierVo;
import com.ecommerceJee.demo.domaine.UserConverter;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.model.Panier;
import com.ecommerceJee.demo.repository.PanierRepository;

@Service
public class PanierService 
{
	 @Autowired
	    PanierRepository repository;
	 
	 public List<PanierVo> getAllPaniers()
	    {
	        List<PanierVo> panierList = PanierConverter.toVoList(repository.findAll());
	         
	        if(panierList.size() > 0) {
	            return panierList;
	        } else {
	            return new ArrayList<PanierVo>();
	        }
	    }
//	 public Panier getPanierById(Integer id) throws RecordNotFoundException
//	    {
//	        Optional<Panier> panier = repository.findById(id);
//	         
//	        if(panier.isPresent()) {
//	            return panier.get();
//	        } else {
//	            throw new RecordNotFoundException("No Panier record exist for given id");
//	        }
//	    }
	 
	   public PanierVo getPanierById(Integer id) {
			boolean trouve = repository.existsById(id);
			if (!trouve)
				return null;
			return PanierConverter.toVo(repository.getOne(id));
		}
	 
	 public PanierVo createOrUpdatePanier(PanierVo entity) throws RecordNotFoundException
	    {
	        Optional<Panier> panier = repository.findById(entity.getId());
	         
	        if(panier.isPresent())
	        {
	            Panier newEntity = panier.get();
	            newEntity.setUser(UserConverter.toBo(entity.getUser()));
	            newEntity.setArticles(ArticleConverter.toBoList(entity.getArticles()));

	 
	            newEntity = repository.save(newEntity);
	             
	            return entity;
	        } else {
	            repository.save(PanierConverter.toBo(entity));
	            return entity;
	        }
	    }
	 
	 
	 public List<PanierVo> getAllPaniers(int pageId, int size) {
			Page<Panier> result = repository.findAll(PageRequest.of(pageId, size, Direction.ASC, "libelle"));
			return PanierConverter.toVoList(result.getContent());
		}
	 
	 public List<PanierVo> findByUser(String libelle) {
			List<Panier> list = repository.findByUser(libelle);
			return PanierConverter.toVoList(list);
		}
	 
	 public List<PanierVo> sortBy(String fieldName) {
		 List<Panier>  list =repository.findAll(Sort.by(fieldName));
			return PanierConverter.toVoList(list);
	}
	 
		 
	 @Transactional
	    public Panier save(Panier entity) 
	    {
	       
	            return repository.save(entity);     
	        
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