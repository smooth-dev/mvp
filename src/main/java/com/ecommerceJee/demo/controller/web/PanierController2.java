package com.ecommerceJee.demo.controller.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ecommerceJee.demo.model.Panier;
import com.ecommerceJee.demo.model.User;
import com.ecommerceJee.demo.service.PanierService;
import com.ecommerceJee.demo.service.UserService;

@Controller
@RequestMapping("/panier/")
public class PanierController2 {

	@Autowired
	 PanierService service;
	@Autowired
	 UserService serviceUser;

	/**
	 * Lorsqu'on tape le lien http://localhost:8080, la page
	 * /WEB-INF/vues/index.jsp. Aucun objet n'est passé dans le Model.
	 */
	@RequestMapping("/")
	public String showWelcomeFile() {
		return "panier/panierindex";
	}
	
	@RequestMapping("/form")
	public String showform(Model m) {
		List<User> list=serviceUser.getAllUsers();
		m.addAttribute("list",list);
		m.addAttribute("panierVO", new Panier());
		return "panier/panierform"; //page html !!!!!!!!!!!
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("panierVO") Panier emp) {
		
				service.save(emp);
		return "redirect:viewpanier";// will redirect to viewpanier request mapping
	}
	
	@RequestMapping("/viewpanier")
	public String viewpanier(Model m) {
		List<Panier> list = service.getAllPaniers();
		m.addAttribute("list", list);
		return "panier/viewpanier"; //html page !!!!!!!!!!
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable Integer id, Model m) {
		Panier PU = new Panier();
		List<User> list=serviceUser.getAllUsers();
		PU = service.getPanierById(id);
		
		m.addAttribute("list",list);
		m.addAttribute("PU", PU);
		return "panier/paniereditform"; //html apage !!!!!!!!!
	}
	
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("PU") Panier p) {

		service.save(p);
		return "redirect:viewpanier";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:../viewpanier";
	}
	
	
	@RequestMapping("/id/{choosenid}")
	public String getLibelle(@PathVariable Integer user_id, Model m) {
		Optional<Panier> list = service.findById(user_id);
		m.addAttribute("list", list);
		return "panier/viewpanier";
	}
	

	
	@RequestMapping("/pagination/{pageid}/{size}")
	public String pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
		List<Panier> list = service.getAllPaniers(pageid, size);
		m.addAttribute("list", list);
		return "panier/viewpanier";
	}
	
	@RequestMapping("/sort/{fieldName}")
	public String sortBy(@PathVariable String fieldName, Model m) {
		List<Panier> list = service.sortBy(fieldName);
		m.addAttribute("list", list);
		return "panier/viewpanier";
	}
}