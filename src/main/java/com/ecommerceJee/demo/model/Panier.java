package com.ecommerceJee.demo.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor

public class Panier implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_ID")
	private User user;
	
	@OneToMany(mappedBy = "panier",cascade=CascadeType.ALL)
	@Basic(fetch=FetchType.LAZY)
	private List<Article> articles;
	
	public Panier(Integer user_id) {
	}

	
	
	
	
	
	@Override
	public String toString()
	{
		return "Panier [panier_id="+ id+  "]";
	}




	
	
}