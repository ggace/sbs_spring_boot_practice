package com.min.sbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.min.sbs.dao.ArticleDao;
import com.min.sbs.dto.Article;
import com.min.sbs.dto.ResultData;
import com.min.sbs.util.Util;

@Service
public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService(ArticleDao articleRepository) {
		this.articleDao = articleRepository;
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	public ResultData doAdd(String title, String body) {
		articleDao.addArticle(title, body);
		int id = articleDao.getLastArticleId();
		return ResultData.from("S-1", Util.format("%s번 게시물이 추가되었습니다.", id), id);
	}

	public void doDelete(int id) {

		articleDao.deleteArticle(id);

	}

	public String doModify(int id, String title, String body) {
		Article article = getArticle(id);

		if (article == null) {
			return id + "번 글은 존재하지 않습니다.";
		}

		articleDao.modifyArticle(id, title, body);

		return id + "번 글을 수정하였습니다.";
	}

}
