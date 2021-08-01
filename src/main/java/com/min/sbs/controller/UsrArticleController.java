package com.min.sbs.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.min.sbs.dto.Article;
import com.min.sbs.dto.ResultData;
import com.min.sbs.service.ArticleService;
import com.min.sbs.util.Util;

@Controller
public class UsrArticleController {
	private ArticleService articleService;

	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articleService.getArticles();
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(Integer id) {
		if(id == null) {
			return ResultData.from("F-A", "id를 입력해주세요");
		}
		Article article = articleService.getArticle(id);
		if(article == null) {
			return ResultData.from("F-1", Util.format("%s번 게시물은 존재하지 않습니다.", id));
		}
		return ResultData.from("S-", Util.format("%s번 게시물입니다.", id), article); 
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body) {
		if(Util.isEmpty(title)) {
			return ResultData.from("F-A", "title를 입력해주세요");
		}
		if(Util.isEmpty(body)) {
			return ResultData.from("F-B", "body를 입력해주세요");
		}
		ResultData addRd = articleService.doAdd(title, body);
		int id = (int)addRd.getData();
		return ResultData.newData(addRd, articleService.getArticle(id));
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(Integer id) {
		if(id == null) {
			return "id를 입력해주세요";
		}
		return articleService.doDelete(id);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(Integer id, String title, String body) {
		if(id == null) {
			return "id를 입력해주세요";
		}
		if(Util.isEmpty(title)) {
			return "title를 입력해주세요";
		}
		if(Util.isEmpty(body)) {
			return "body를 입력해주세요";
		}
		return articleService.doModify(id, title, body);
	}
}
