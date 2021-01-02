package com.ecommerceJee.demo.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private Integer id;
	private String nom;
	private String prenom;
	private String username;
	private String password;
	
	private boolean enabled;
	private boolean accountNonExpired;
	private boolean credentialsNonExpired;
	private boolean accountNonLocked;
	
	
	@OneToMany(mappedBy = "user",  cascade = {
	        CascadeType.PERSIST, 
	        CascadeType.MERGE
	    })
	@Basic(fetch=FetchType.LAZY)
	private List<Panier> paniers;
	
	 @ManyToMany(cascade=CascadeType.MERGE)
     private List<Role> roles ;
	
	
	public User(String nom,String prenom,String pseudo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.username = pseudo;
	}

	
	@Override
	public String toString()
	{
		return "User [id="+ id+ ", nom="+ nom+ ", prenom="+ prenom+ ", pseudo="+ username+ "]";
	}


	
	public User(String nom,String prenom,String username,String password,boolean enabled,boolean accountNonExpired,
			boolean credentialsNonExpired,boolean accountNonLocked) {
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
	
	}

	
	
	

	
	

	 

}
