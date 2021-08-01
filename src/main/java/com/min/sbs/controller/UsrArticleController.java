package com.min.sbs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	public ResultData<List<Article>> getArticles() {
		List<Article> articles = articleService.getArticles();
		return ResultData.from("S-1", "게시물 리스트 입니다.", articles);
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(Integer id) {

		if (id == null) {
			return ResultData.from("F-A", "id를 입력해주세요");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Util.format("%s번 게시물은 존재하지 않습니다.", id));
		}

		return ResultData.from("S-1", Util.format("%s번 게시물입니다.", id), article);
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(String title, String body, HttpSession session) {

		if (session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-a", "로그인 후 사용해주세요");
		}

		if (Util.isEmpty(title)) {
			return ResultData.from("F-A", "title를 입력해주세요");
		}
		if (Util.isEmpty(body)) {
			return ResultData.from("F-B", "body를 입력해주세요");
		}

		int memberId = (int) session.getAttribute("loginedMemberId");

		ResultData<Integer> addRd = articleService.doAdd(memberId, title, body);
		int id = addRd.getData();

		return ResultData.newData(addRd, articleService.getArticle(id));
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData<Integer> doDelete(Integer id, HttpSession session) {

		if (session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-a", "로그인 후 사용해주세요");
		}

		if (id == null) {
			return ResultData.from("F-A", "id를 입력해주세요");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Util.format("%s번 글은 존재하지 않습니다.", id));
		}

		int memberId = (int) session.getAttribute("loginedMemberId");

		if (article.getMemberId() != memberId) {
			return ResultData.from("F-b", "권한이 없습니다.");
		}

		articleService.doDelete(id);

		return ResultData.from("S-1", Util.format("%s번 글이 삭제되었습니다.", id), id);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(Integer id, String title, String body, HttpSession session) {

		if (session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-a", "로그인 후 사용해주세요");
		}

		if (id == null) {
			return ResultData.from("F-A", "id를 입력해주세요");
		}
		if (Util.isEmpty(title)) {
			return ResultData.from("F-B", "title를 입력해주세요");
		}
		if (Util.isEmpty(body)) {
			return ResultData.from("F-C", "body를 입력해주세요");
		}

		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-C", Util.format("%s번 글은 존재하지 않습니다.", id));
		}

		int memberId = (int) session.getAttribute("loginedMemberId");

		if (article.getMemberId() != memberId) {
			return ResultData.from("F-b", "권한이 없습니다.");
		}

		articleService.doModify(id, title, body);

		article = articleService.getArticle(id);

		return ResultData.from("S-1", Util.format("%s번 글을 수정하였습니다.", id), article);
	}
}
