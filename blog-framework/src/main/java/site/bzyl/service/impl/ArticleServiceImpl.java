package site.bzyl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.bzyl.dao.ArticleDao;
import site.bzyl.entity.Article;
import site.bzyl.service.ArticleService;
import site.bzyl.service.ArticleService;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {
}
