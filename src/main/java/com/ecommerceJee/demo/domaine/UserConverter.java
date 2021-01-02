package com.ecommerceJee.demo.domaine;



import java.util.ArrayList;
import java.util.List;
import com.ecommerceJee.demo.model.User;


public class UserConverter {
	public static UserVo toVo(User bo) {
		if (bo == null)
			return null;
		UserVo vo = new UserVo();
		vo.setId(bo.getId());
		vo.setNom(bo.getNom());
		vo.setPrenom(bo.getPrenom());
		vo.setUsername(bo.getUsername());
		vo.setPassword(bo.getPassword());
		vo.setRoles(RoleConverter.toVoList(bo.getRoles()));
		vo.setPaniers(PanierConverter.toVoList(bo.getPaniers()));

		vo.setAccountNonExpired(bo.isAccountNonExpired());
		vo.setAccountNonLocked(bo.isAccountNonLocked());
		vo.setEnabled(bo.isEnabled());
		vo.setCredentialsNonExpired(bo.isCredentialsNonExpired());

		
		return vo;
	}

	public static User toBo(UserVo vo) {
		if (vo == null)
			return null;
		User bo = new User();
		if (vo.getId() != null)
			bo.setId(vo.getId());
		bo.setNom(vo.getNom());
		bo.setPrenom(vo.getPrenom());
		bo.setUsername(vo.getUsername());
		bo.setPassword(vo.getPassword());
		bo.setRoles(RoleConverter.toBoList(vo.getRoles()));
		bo.setPaniers(PanierConverter.toBoList(vo.getPaniers()));

		
		
		bo.setAccountNonExpired(vo.isAccountNonExpired());
		bo.setAccountNonLocked(vo.isAccountNonLocked());
		bo.setEnabled(vo.isEnabled());
		bo.setCredentialsNonExpired(vo.isCredentialsNonExpired());
		return bo;
	}

	public static List<UserVo> toVoList(List<User> boList) {
		if (boList == null || boList.isEmpty())
			return null;
		List<UserVo> voList = new ArrayList<>();
		for (User user : boList) {
			voList.add(toVo(user));
		}
		return voList;
	}

	public static List<User> toBoList(List<UserVo> voList) {
		if (voList == null || voList.isEmpty())
			return null;
		List<User> boList = new ArrayList<>();
		for (UserVo userVo : voList) {
			boList.add(toBo(userVo));
		}
		return boList;
	}
}
