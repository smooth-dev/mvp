package com.ecommerceJee.demo.domaine;

import java.util.ArrayList;
import java.util.List;
import com.ecommerceJee.demo.model.Panier;

public class PanierConverter
{

	public static PanierVo toVo(Panier bo) {
		if (bo == null)
			return null;
		PanierVo vo = new PanierVo();
		vo.setId(bo.getId());
		//vo.setUser(UserConverter.toVo(bo.getUser()));
		vo.setArticles(ArticleConverter.toVoList(bo.getArticles()));

		return vo;
	}

	public static Panier toBo(PanierVo vo) {
		if (vo == null)
			return null;
		Panier bo = new Panier();
		if (vo.getId() != null)
			bo.setId(vo.getId());
		bo.setId(vo.getId());
		//bo.setUser(UserConverter.toBo(vo.getUser()));
		bo.setArticles(ArticleConverter.toBoList(vo.getArticles()));

		return bo;
	}

	public static List<PanierVo> toVoList(List<Panier> boList) {
		if (boList == null || boList.isEmpty())
			return null;
		List<PanierVo> voList = new ArrayList<>();
		for (Panier Panier : boList) {
			voList.add(toVo(Panier));
		}
		return voList;
	}
	
	
	
	public static List<Panier> toBoList(List<PanierVo> voList) {
		if (voList == null || voList.isEmpty())
			return null;
		List<Panier> boList = new ArrayList<>();
		for (PanierVo PanierVo : voList) {
			boList.add(toBo(PanierVo));
		}
		return boList;
	}
}
