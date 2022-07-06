package site.bzyl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.bzyl.domain.entity.Article;
import site.bzyl.domain.ResponseResult;
import site.bzyl.service.ArticleService;


@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 热门文章列表
     * @return
     */
    @GetMapping("/hotArticleList")
    public ResponseResult<Article> getHotArticleList() {
        return articleService.hotArticleList();
    }
}
