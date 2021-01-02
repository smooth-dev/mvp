package com.ecommerceJee.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ecommerceJee.demo.model.Article;
import com.ecommerceJee.demo.model.Panier;
import com.ecommerceJee.demo.model.Role;
import com.ecommerceJee.demo.model.User;
import com.ecommerceJee.demo.service.ArticleService;
import com.ecommerceJee.demo.service.PanierService;
import com.ecommerceJee.demo.service.RoleService;
import com.ecommerceJee.demo.service.UserService;

@SpringBootApplication
public class DemoApplication  extends SpringBootServletInitializer implements CommandLineRunner{


	@Autowired
	private UserService serviceUser;
	@Autowired
	private ArticleService serviceArticle;
	@Autowired
	private PanierService servicePanier;
	@Autowired
	private RoleService serviceRole;
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Application démarrée");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	/**
	 * vararg:
	 */
	public void run(String... args) throws Exception {

		serviceArticle.deleteAll();
		servicePanier.deleteAll();

		serviceUser.deleteAll();
		serviceRole.deleteAll();

		
 	
		Article art1 = new Article("article1","article1",1,1);
		Article art2 = new Article("article2","article2",2,2);
		Article art3 = new Article("article3","article3",3,3);
		Article art4 = new Article("article4","article4",4,4);
		Article art5 = new Article("article1","article1",1,1);
		Article art6 = new Article("article2","article2",2,2);
		Article art7 = new Article("article3","article3",3,3);
		Article art8 = new Article("article4","article4",4,4);
		
		Panier panier1= new Panier();
		Panier panier2= new Panier();
 
	
		
		User  user1 = new User("admin","admin","admin","admin",true,true,true,true); 
		User  user2 = new User("client","client","client","client",true,true,true,true);
		
		Role role1 = new Role("ADMIN");
		Role role2 = new Role("CLIENT");
		
		serviceRole.save(role1);
		serviceRole.save(role2);


		//il faut creer une liste car ManyToMany
		List<Role> roles1= new ArrayList<Role>();
		List<Role> roles2= new ArrayList<Role>();
		roles1.add(role1);
	//	roles2.add(role1);
		roles2.add(role2);
		user1.setRoles(roles1);
		user2.setRoles(roles2);

		
		serviceUser.save(user1);
		serviceUser.save(user2);
		
		panier1.setUser(user1);
		panier2.setUser(user2);
		
		servicePanier.save(panier1);
		servicePanier.save(panier2);



		art1.setPanier(panier1);
		art2.setPanier(panier1);
		art3.setPanier(panier2);
		art4.setPanier(panier2);
		art5.setPanier(panier1);
		art6.setPanier(panier1);
		art7.setPanier(panier2);
		art8.setPanier(panier2);
		
		
		serviceArticle.save(art1);
		serviceArticle.save(art2);
		serviceArticle.save(art3);
		serviceArticle.save(art4); 
		serviceArticle.save(art5);
		serviceArticle.save(art6);
		serviceArticle.save(art7);
		serviceArticle.save(art8); 
		
		//*/
	} 
	
	

}