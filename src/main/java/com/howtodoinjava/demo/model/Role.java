package com.howtodoinjava.demo.model;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Role implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private int id;
	private String libelle;
	
	
	public Role(String libelle) {
		
		this.libelle = libelle;
	}
	
	


}
