package site.bzyl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.bzyl.constants.SystemConstants;
import site.bzyl.dao.ArticleDao;
import site.bzyl.domain.entity.Article;
import site.bzyl.domain.ResponseResult;
import site.bzyl.domain.vo.HotArticleListVo;
import site.bzyl.service.ArticleService;
import site.bzyl.utils.BeanCopyUtils;

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
        // 调用工具类, 拷贝成vo对象
        List<HotArticleListVo> hotArticleListVos = BeanCopyUtils.copyBeanList(articleList, HotArticleListVo.class);
        // 返回响应对象
        return ResponseResult.okResult(hotArticleListVos);

    }
}
