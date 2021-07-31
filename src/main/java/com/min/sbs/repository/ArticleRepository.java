package com.min.sbs.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.min.sbs.dto.Article;

@Mapper
public interface ArticleRepository {

	public List<Article> getArticles();

	public Article getArticle(@Param("id") int id);

	public Article getLastArticle();

	public void addArticle(@Param("title") String title, @Param("body") String body);

	public void deleteArticle(@Param("id") int id);

	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

}
