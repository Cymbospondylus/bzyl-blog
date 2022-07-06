package site.bzyl.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.bzyl.domain.entity.Article;

@SpringBootTest
@Slf4j
public class ArticleTest {

    @Autowired
    private ArticleDao articleDao;

    @Test
    public void getAllTest() {
        Article article = articleDao.selectById(5L);
        System.out.println(article.toString());
    }
}
