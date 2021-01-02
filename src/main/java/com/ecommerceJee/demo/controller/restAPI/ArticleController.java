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
import com.ecommerceJee.demo.domaine.ArticleVo;
import com.ecommerceJee.demo.exception.RecordNotFoundException;
import com.ecommerceJee.demo.service.ArticleService;
 
@RestController
@RequestMapping("/articles")
public class ArticleController
{
    @Autowired
    ArticleService service;
 
    @GetMapping
    public ResponseEntity<List<ArticleVo>> getAllArticles() {
        List<ArticleVo> list = service.getAllArticles();
 
        return new ResponseEntity<List<ArticleVo>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<ArticleVo> getArticleById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
        ArticleVo entity = service.getArticleById(id);
 
        return new ResponseEntity<ArticleVo>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<ArticleVo> createOrUpdateArticle(ArticleVo article)
                                                    throws RecordNotFoundException {
        ArticleVo updated = service.createOrUpdateArticle(article);
        return new ResponseEntity<ArticleVo>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deleteArticleById(@PathVariable("id") Integer id)
                                                    throws RecordNotFoundException {
    	ArticleVo empVoFound = service.getArticleById(id);
		if (empVoFound == null)
			return new ResponseEntity<>("Article doesn't exist", HttpStatus.OK);

        service.deleteArticleById(id);
		return new ResponseEntity<>("Article is deleted successsfully", HttpStatus.OK);

    }
    
    @GetMapping(value = "/sort/{fieldName}")
   	public ResponseEntity<List<ArticleVo>> sortBy(@PathVariable String fieldName) {
       	 List<ArticleVo> list = service.sortBy(fieldName);
       	 System.out.println(list);
       	 
            return new ResponseEntity<List<ArticleVo>>(list, new HttpHeaders(), HttpStatus.OK);
   	}
       
       @GetMapping("/{pageid}/{size}")
   	public List<ArticleVo> pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
   		return service.getAllArticles(pageid, size);
   	}
 
}