package site.bzyl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import site.bzyl.constant.SystemConstants;
import site.bzyl.dao.ArticleDao;
import site.bzyl.domain.entity.Article;
import site.bzyl.domain.ResponseResult;
import site.bzyl.domain.vo.HotArticleListVo;
import site.bzyl.service.ArticleService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {
    @Override
    public ResponseResult<Article> hotArticleList() {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        // 文章未逻辑删除(yml已配置逻辑删除的字段)
        // 文章状态为已发布
        lqw.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED);
        // 按照浏览量（view_count）降序排列
        lqw.orderByDesc(Article::getViewCount);
        // 前10条，即分页查询第一页十条数据
        Page<Article> page = new Page<>(SystemConstants.HOT_ARTICLE_LIST_CURRENT, SystemConstants.HOT_ARTICLE_LIST_SIZE_PER_PAGE);
        page(page, lqw);
        List<Article> articleList = page.getRecords();
        List<HotArticleListVo> hotArticleListVos = new ArrayList<>();
        for (Article article : articleList) {
            HotArticleListVo vo = new HotArticleListVo();
            BeanUtils.copyProperties(article, vo);
            hotArticleListVos.add(vo);
        }
        // 封装对象并返回
        return ResponseResult.okResult(hotArticleListVos);

    }
}
