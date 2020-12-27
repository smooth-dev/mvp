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
import com.ecommerceJee.demo.model.User;
import com.ecommerceJee.demo.service.UserService;

@Controller
@RequestMapping("/user/")
public class UserController2 {

	@Autowired
	 UserService service;

	/**
	 * Lorsqu'on tape le lien http://localhost:8080, la page
	 * /WEB-INF/vues/index.jsp. Aucun objet n'est passé dans le Model.
	 */
	@RequestMapping("/")
	public String showWelcomeFile() {
		return "user/userindex";
	}
	
	@RequestMapping("/form")
	public String showform(Model m) {
		m.addAttribute("empVo", new User());
		return "user/userform"; //page html !!!!!!!!!!!
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("empVo") User emp) {
		
				service.save(emp);
		return "redirect:viewuser";// will redirect to viewuser request mapping
	}
	
	@RequestMapping("/viewuser")
	public String viewuser(Model m) {
		List<User> list = service.getAllUsers();
		m.addAttribute("list", list);
		return "user/viewuser"; //html page !!!!!!!!!!
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable Integer id, Model m) {
		User emp = null;
		emp = service.getUserById(id);
		m.addAttribute("empVo", emp);
		return "user/usereditform"; //html apage !!!!!!!!!
	}
	
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("empVo") User emp) {
		service.save(emp);
		return "redirect:viewuser";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:../viewuser";
	}
	
	
	@RequestMapping("/nom/{choosennom}")
	public String getNom(@PathVariable String choosennom, Model m) {
		List<User> list = service.findByNom(choosennom);
		m.addAttribute("list", list);
		return "user/viewuser";
	}
	

	
	@RequestMapping("/pagination/{pageid}/{size}")
	public String pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
		List<User> list = service.getAllUsers(pageid, size);
		m.addAttribute("list", list);
		return "user/viewuser";
	}
	
	@RequestMapping("/sort/{fieldName}")
	public String sortBy(@PathVariable String fieldName, Model m) {
		List<User> list = service.sortBy(fieldName);
		m.addAttribute("list", list);
		return "user/viewuser";
	}
}