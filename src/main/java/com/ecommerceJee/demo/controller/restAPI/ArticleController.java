package com.ecommerceJee.demo.controller.restAPI;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.model.Article;
import com.ecommerceJee.demo.service.ArticleService;
 
@RestController
@RequestMapping("/articles")
public class ArticleController
{
    @Autowired
    ArticleService service;
 
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> list = service.getAllArticles();
 
        return new ResponseEntity<List<Article>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
        Article entity = service.getArticleById(id);
 
        return new ResponseEntity<Article>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<Article> createOrUpdateArticle(Article article)
                                                    throws RecordNotFoundException {
        Article updated = service.createOrUpdateArticle(article);
        return new ResponseEntity<Article>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deleteArticleById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
    	Article empVoFound = service.getArticleById(id);
		if (empVoFound == null)
			return new ResponseEntity<>("Article doesn't exist", HttpStatus.OK);

        service.deleteArticleById(id);
		return new ResponseEntity<>("Article is deleted successsfully", HttpStatus.OK);

    }
    
    @GetMapping(value = "/sort/{fieldName}")
   	public ResponseEntity<List<Article>> sortBy(@PathVariable String fieldName) {
       	 List<Article> list = service.sortBy(fieldName);
       	 System.out.println(list);
       	 
            return new ResponseEntity<List<Article>>(list, new HttpHeaders(), HttpStatus.OK);
   	}
       
       @GetMapping("/{pageid}/{size}")
   	public List<Article> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
   		return service.getAllArticles(pageid, size);
   	}
 
}