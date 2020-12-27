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
	private String pseudo;
	
	@OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
	@Basic(fetch=FetchType.LAZY)
	private List<Panier> panier;
	
	 @ManyToMany(cascade=CascadeType.MERGE)
     private List<Role> roles ;
	
	
	public User(String nom,String prenom,String pseudo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
	}

	@Override
	public String toString()
	{
		return "User [id="+ id+ ", nom="+ nom+ ", prenom="+ prenom+ ", pseudo="+ pseudo+ "]";
	}

	
	
	

	
	

	 

}
