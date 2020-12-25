package com.howtodoinjava.demo.model;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private String nom;
	private String prenom;
	private String pseudo;
	
	public User(String nom,String prenom,String pseudo) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.pseudo = pseudo;
	}

	
	

	 

}
