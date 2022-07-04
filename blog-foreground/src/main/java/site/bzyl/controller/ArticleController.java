package site.bzyl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.bzyl.entity.Article;
import site.bzyl.service.ArticleService;

import java.util.List;

@RestController
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public List<Article> getAll() {
        List<Article> list = articleService.list();
        return list;
    }
}
