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
public class Article implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private int id;
	private String libelle;
	private String description;
	private int prix ;
	private int quantite ;
	
	
	public Article(String libelle,String description,int prix,int quantite) {
		this.libelle = libelle;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
	}

	 

}
