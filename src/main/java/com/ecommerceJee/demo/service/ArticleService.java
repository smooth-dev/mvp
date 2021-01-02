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
import com.ecommerceJee.demo.domaine.ArticleVo;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.model.Article;
import com.ecommerceJee.demo.repository.ArticleRepository;

@Service
public class ArticleService 
{
	 @Autowired
	    ArticleRepository repository;
	 
	 public List<ArticleVo> getAllArticles()
	    {
	        List<ArticleVo> articleList = ArticleConverter.toVoList(repository.findAll());
	         
	        if(articleList.size() > 0) {
	            return articleList;
	        } else {
	            return new ArrayList<ArticleVo>();
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
	 
	   public ArticleVo getArticleById(Integer id) {
			boolean trouve = repository.existsById(id);
			if (!trouve)
				return null;
			return ArticleConverter.toVo(repository.getOne(id));
		}
	 
	 public ArticleVo createOrUpdateArticle(ArticleVo entity) throws RecordNotFoundException
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
	             
	            return entity;
	        } else {
	            repository.save(ArticleConverter.toBo(entity));
	            return entity;
	        }
	    }
	 
	 
	 public List<ArticleVo> getAllArticles(int pageId, int size) {
			Page<Article> result = repository.findAll(PageRequest.of(pageId, size, Direction.ASC, "libelle"));
			return ArticleConverter.toVoList(result.getContent());
		}
	 
	 public List<ArticleVo> findByLibelle(String libelle) {
			List<Article> list = repository.findByLibelle(libelle);
			return ArticleConverter.toVoList(list);
		}
	 public List<ArticleVo> sortBy(String fieldName) {
		 List<Article>  list =repository.findAll(Sort.by(fieldName));
			return ArticleConverter.toVoList(list);
	}
	 
		 
	 @Transactional
	    public Article save(Article entity) 
	    {
	       
	           return repository.save(entity);     
	        
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