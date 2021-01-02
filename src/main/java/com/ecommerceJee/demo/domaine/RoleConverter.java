package com.ecommerceJee.demo.domaine;

import java.util.ArrayList;
import java.util.List;
import com.ecommerceJee.demo.model.Role;

public class RoleConverter
{

	public static RoleVo toVo(Role bo) {
		if (bo == null)
			return null;
		RoleVo vo = new RoleVo();
		vo.setId(bo.getId());
		vo.setLibelle(bo.getLibelle());
		return vo;
	}

	public static Role toBo(RoleVo vo) {
		if (vo == null)
			return null;
		Role bo = new Role();
		bo.setId(vo.getId());
		bo.setLibelle(vo.getLibelle());
		return bo;
	}

	public static List<RoleVo> toVoList(List<Role> boList) {
		if (boList == null || boList.isEmpty())
			return null;
		List<RoleVo> voList = new ArrayList<>();
		for (Role role : boList) {
			voList.add(toVo(role));
		}
		return voList;
	}

	public static List<Role> toBoList(List<RoleVo> voList) {
		if (voList == null || voList.isEmpty())
			return null;
		List<Role> boList = new ArrayList<>();
		for (RoleVo roleVo : voList) {
			boList.add(toBo(roleVo));
		}
		return boList;
	}

}
