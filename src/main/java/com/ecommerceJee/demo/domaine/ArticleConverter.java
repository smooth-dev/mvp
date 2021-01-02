package com.ecommerceJee.demo.domaine;

import java.util.ArrayList;
import java.util.List;
import com.ecommerceJee.demo.model.Article;

public class ArticleConverter
{

	
	public static ArticleVo toVo(Article bo) {
		if (bo == null)
			return null;
		ArticleVo vo = new ArticleVo();
		vo.setId(bo.getId());
		vo.setLibelle(bo.getLibelle());
		vo.setDescription(bo.getDescription());
		vo.setPrix(bo.getPrix());
		vo.setQuantite(bo.getQuantite());

		//vo.setPanier(PanierConverter.toVo(bo.getPanier()));
	

		return vo;
	}

	public static Article toBo(ArticleVo vo) {
		if (vo == null)
			return null;
		Article bo = new Article();
		if (vo.getId() != null)
			bo.setId(vo.getId());		
		bo.setLibelle(vo.getLibelle());
		bo.setDescription(vo.getDescription());
		bo.setPrix(vo.getPrix());
		bo.setQuantite(vo.getQuantite());

		//bo.setPanier(PanierConverter.toBo(vo.getPanier()));

		return bo;
	}

	public static List<ArticleVo> toVoList(List<Article> boList) {
		if (boList == null || boList.isEmpty())
			return null;
		List<ArticleVo> voList = new ArrayList<>();
		for (Article Article : boList) {
			voList.add(toVo(Article));
		}
		return voList;
	}

	public static List<Article> toBoList(List<ArticleVo> voList) {
		if (voList == null || voList.isEmpty())
			return null;
		List<Article> boList = new ArrayList<>();
		for (ArticleVo ArticleVo : voList) {
			boList.add(toBo(ArticleVo));
		}
		return boList;
	}
}
