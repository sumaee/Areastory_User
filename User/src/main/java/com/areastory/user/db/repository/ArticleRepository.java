package com.areastory.user.db.repository;

import com.areastory.user.db.entity.Article;
import com.areastory.user.db.entity.UserInfo;
import com.areastory.user.db.repository.support.ArticleRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleRepositorySupport {

//    List<Article> findByUser(User user);

    Page<Article> findByUser(UserInfo user, Pageable pageable);
}
