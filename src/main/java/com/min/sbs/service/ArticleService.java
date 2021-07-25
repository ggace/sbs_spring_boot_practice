package com.min.sbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.min.sbs.dto.Article;
import com.min.sbs.repository.ArticleRepository;

@Service
public class ArticleService {

	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}

	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}

	public Article doAdd(String title, String body) {
		articleRepository.addArticle(title, body);
		
		return articleRepository.getLastArticle();
	}

	public String doDelete(int id) {

		Article article = getArticle(id);

		if (article == null) {
			return id + "번 글은 존재하지 않습니다.";
		}
		
		articleRepository.deleteArticle(id);

		return id + "번 글이 삭제되었습니다.";
	}

	public String doModify(int id, String title, String body) {
		Article article = getArticle(id);

		if (article == null) {
			return id + "번 글은 존재하지 않습니다.";
		}
		
		articleRepository.modifyArticle(id, title, body);
		
		return id + "번 글을 수정하였습니다.";
	}

}
