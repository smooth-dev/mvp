package com.ecommerceJee.demo.domaine;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleVo
{
	private Integer id;
	private String libelle;
	private String description;
	private int prix ;
	private int quantite ;
	private PanierVo panier;
	
	public ArticleVo(String libelle,String description,int prix,int quantite,PanierVo panier) {
		this.libelle = libelle;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.panier = panier;
	}
	
	public ArticleVo(String libelle,String description,int prix,int quantite) {
		this.libelle = libelle;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		
	}
	
	
	

}
