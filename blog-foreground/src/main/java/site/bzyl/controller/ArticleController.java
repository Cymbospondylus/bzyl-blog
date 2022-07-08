package site.bzyl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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


    @GetMapping("/hotArticleList")
    public ResponseResult<Article> getHotArticleList() {
        return articleService.hotArticleList();
    }


    @GetMapping("/articleList")
    public ResponseResult<Article> getArticleList (Long pageNum, Long pageSize, Long categoryId) {
        // get方法query形式传参，参数名必须和请求体的名字相同，否则读不到数据
        return articleService.getArticleList(pageNum, pageSize, categoryId);
    }
}


