package com.ecommerceJee.demo.controller.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ecommerceJee.demo.model.Article;
import com.ecommerceJee.demo.service.ArticleService;

@Controller
@RequestMapping("/article/")
public class ArticleController2 {

	@Autowired
	 ArticleService service;

	/**
	 * Lorsqu'on tape le lien http://localhost:8080, la page
	 * /WEB-INF/vues/index.jsp. Aucun objet n'est passé dans le Model.
	 */
	@RequestMapping("/")
	public String showWelcomeFile() {
		return "article/articleindex";
	}
	
	@RequestMapping("/form")
	public String showform(Model m) {
		m.addAttribute("empVo", new Article());
		return "article/articleform"; //page html !!!!!!!!!!!
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("empVo") Article emp) {
		
				service.save(emp);
		return "redirect:viewarticle";// will redirect to viewarticle request mapping
	}
	
	@RequestMapping("/viewarticle")
	public String viewarticle(Model m) {
		List<Article> list = service.getAllArticles();
		m.addAttribute("list", list);
		return "article/viewarticle"; //html page !!!!!!!!!!
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable Integer id, Model m) {
		Article emp = null;
		emp = service.getArticleById(id);
		m.addAttribute("empVo", emp);
		return "article/articleeditform"; //html apage !!!!!!!!!
	}
	
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("empVo") Article emp) {
		service.save(emp);
		return "redirect:viewarticle";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:../viewarticle";
	}
	
	
	@RequestMapping("/libelle/{choosenlibelle}")
	public String getLibelle(@PathVariable String choosenlibelle, Model m) {
		List<Article> list = service.findByLibelle(choosenlibelle);
		m.addAttribute("list", list);
		return "article/viewarticle";
	}
	

	
	@RequestMapping("/pagination/{pageid}/{size}")
	public String pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
		List<Article> list = service.getAllArticles(pageid, size);
		m.addAttribute("list", list);
		return "article/viewarticle";
	}
	
	@RequestMapping("/sort/{fieldName}")
	public String sortBy(@PathVariable String fieldName, Model m) {
		List<Article> list = service.sortBy(fieldName);
		m.addAttribute("list", list);
		return "article/viewarticle";
	}
}