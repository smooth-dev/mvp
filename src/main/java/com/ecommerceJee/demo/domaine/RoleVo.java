package com.ecommerceJee.demo.domaine;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleVo
{
	private Integer id;
	private String libelle;
    private List<UserVo> users ;

	
	public RoleVo(String libelle) {
		this.libelle = libelle;
	}
	
}
