package com.joeladjidan.gestiondestock.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.joeladjidan.gestiondestock.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

  Optional<Article> findArticleByCodeArticle(String codeArticle);

  List<Article> findAllByCategoryId(Integer idCategory);


}
