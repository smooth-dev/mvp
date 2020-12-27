package com.ecommerceJee.demo.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Role implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private Integer id;
	private String libelle;
	
	 @ManyToMany(mappedBy="roles")
     private List<User> users ;
	
	public Role(String libelle) {
		
		this.libelle = libelle;
	}

	@Override
	public String toString()
	{
		return "Role [id="+ id+ ", libelle="+ libelle+ "]";
	}
	
	


}
