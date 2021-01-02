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
import com.ecommerceJee.demo.domaine.RoleConverter;
import com.ecommerceJee.demo.domaine.RoleVo;
import com.ecommerceJee.demo.model.Role;
import com.ecommerceJee.demo.service.RoleService;

@Controller
@RequestMapping("/role/")
public class RoleController2 {

	@Autowired
	 RoleService service;

	/**
	 * Lorsqu'on tape le lien http://localhost:8080, la page
	 * /WEB-INF/vues/index.jsp. Aucun objet n'est passé dans le Model.
	 */
	@RequestMapping("/")
	public String showWelcomeFile() {
		return "role/roleindex";
	}
	
	@RequestMapping("/form")
	public String showform(Model m) {
		m.addAttribute("empVo", new Role());
		return "role/roleform"; //page html !!!!!!!!!!!
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("empVo") RoleVo emp) {
		
				service.save(RoleConverter.toBo(emp));
		return "redirect:viewrole";// will redirect to viewrole request mapping
	}
	
	@RequestMapping("/viewrole")
	public String viewrole(Model m) {
		List<RoleVo> list = service.getAllRoles();
		m.addAttribute("list", list);
		return "role/viewrole"; //html page !!!!!!!!!!
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable Integer id, Model m) {
		RoleVo emp = null;
		emp = service.getRoleById(id);
		m.addAttribute("empVo", emp);
		return "role/roleeditform"; //html apage !!!!!!!!!
	}
	
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String editsave(@ModelAttribute("empVo") RoleVo emp) {
		service.save(RoleConverter.toBo(emp));
		return "redirect:viewrole";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id) {
		service.delete(id);
		return "redirect:../viewrole";
	}
	
	
	@RequestMapping("/libelle/{choosenlibelle}")
	public String getLibelle(@PathVariable String choosenlibelle, Model m) {
		List<RoleVo> list = service.findByLibelle(choosenlibelle);
		m.addAttribute("list", list);
		return "role/viewrole";
	}
	

	
	@RequestMapping("/pagination/{pageid}/{size}")
	public String pagination(@PathVariable int pageid, @PathVariable int size, Model m) {
		List<RoleVo> list = service.getAllRoles(pageid, size);
		m.addAttribute("list", list);
		return "role/viewrole";
	}
	
	@RequestMapping("/sort/{fieldName}")
	public String sortBy(@PathVariable String fieldName, Model m) {
		List<RoleVo> list = service.sortBy(fieldName);
		m.addAttribute("list", list);
		return "role/viewrole";
	}
}