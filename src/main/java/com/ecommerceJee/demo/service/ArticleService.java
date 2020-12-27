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
import com.ecommerceJee.demo.model.Article;
import com.ecommerceJee.demo.repository.ArticleRepository;

@Service
public class ArticleService 
{
	 @Autowired
	    ArticleRepository repository;
	 
	 public List<Article> getAllArticles()
	    {
	        List<Article> articleList = repository.findAll();
	         
	        if(articleList.size() > 0) {
	            return articleList;
	        } else {
	            return new ArrayList<Article>();
	        }
	    }
//	 public Article getArticleById(Integer id) throws RecordNotFoundException
//	    {
//	        Optional<Article> article = repository.findById(id);
//	         
//	        if(article.isPresent()) {
//	            return article.get();
//	        } else {
//	            throw new RecordNotFoundException("No Article record exist for given id");
//	        }
//	    }
	 
	   public Article getArticleById(Integer id) {
			boolean trouve = repository.existsById(id);
			if (!trouve)
				return null;
			return repository.getOne(id);
		}
	 
	 public Article createOrUpdateArticle(Article entity) throws RecordNotFoundException
	    {
	        Optional<Article> article = repository.findById(entity.getId());
	         
	        if(article.isPresent())
	        {
	            Article newEntity = article.get();
	            newEntity.setLibelle(entity.getLibelle());
	            newEntity.setDescription(entity.getDescription());
	            newEntity.setPrix(entity.getPrix());
	            newEntity.setQuantite(entity.getQuantite());

	 
	            newEntity = repository.save(newEntity);
	             
	            return newEntity;
	        } else {
	            entity = repository.save(entity);
	             
	            return entity;
	        }
	    }
	 
	 
	 public List<Article> getAllArticles(int pageId, int size) {
			Page<Article> result = repository.findAll(PageRequest.of(pageId, size, Direction.ASC, "libelle"));
			return result.getContent();
		}
	 
	 public List<Article> findByLibelle(String libelle) {
			List<Article> list = repository.findByLibelle(libelle);
			return list;
		}
	 public List<Article> sortBy(String fieldName) {
		 List<Article>  list =repository.findAll(Sort.by(fieldName));
		return list;
	}
	 
		 
	 @Transactional
	    public void save(Article entity) 
	    {
	       
	            repository.save(entity);     
	        
	    }
	 

	  public void deleteArticleById(Integer id) throws RecordNotFoundException
	    {
	        Optional<Article> role = repository.findById(id);
	         
	        if(role.isPresent())
	        {
	            repository.deleteById(id);
	        } else {
	            throw new RecordNotFoundException("No Article record exist for given id");
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