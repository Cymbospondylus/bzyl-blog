package site.bzyl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import site.bzyl.domain.entity.Article;
import site.bzyl.domain.ResponseResult;

@Service
public interface ArticleService extends IService<Article> {
    ResponseResult<Article> hotArticleList();

    ResponseResult<Article> getArticleList(Long pageNum, Long pageSize, Long categoryId);
}
