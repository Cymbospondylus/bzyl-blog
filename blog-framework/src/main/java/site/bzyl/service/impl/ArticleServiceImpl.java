package site.bzyl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.bzyl.constants.SystemConstants;
import site.bzyl.dao.ArticleDao;
import site.bzyl.dao.CategoryDao;
import site.bzyl.domain.entity.Article;
import site.bzyl.domain.ResponseResult;
import site.bzyl.domain.entity.Category;
import site.bzyl.domain.vo.ArticleDetailVo;
import site.bzyl.domain.vo.ArticleVo;
import site.bzyl.domain.vo.HotArticleListVo;
import site.bzyl.domain.vo.PageVo;
import site.bzyl.enums.AppHttpCodeEnum;
import site.bzyl.service.ArticleService;
import site.bzyl.utils.BeanCopyUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {
    @Autowired
    private CategoryDao categoryDao;
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

    @Override
    public ResponseResult<Article> getArticleList(Long pageNum, Long pageSize, Long categoryId) {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 正式发布的文章（状态status = 1）
        articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED);
        // 置顶的文章在最前面（is_top降序排列）
        articleLambdaQueryWrapper.orderByDesc(Article::getIsTop, Article::getCreateTime);
        // 根据分类id查询（如果存在分类id）
        articleLambdaQueryWrapper.eq(
                Objects.nonNull(categoryId) && categoryId > 0,
                Article::getCategoryId,
                categoryId
        );
        // 分页查询
        Page<Article> articlePage = new Page<>(pageNum, pageSize);
        Page<Article> page = page(articlePage, articleLambdaQueryWrapper);
        // TODO 此处将来可以用redis或者并行流优化, 循环中写sql是大忌, 或者考虑改变表结构进行多表查询
        // 封装categoryName到每个article
        List<Article> articles = page.getRecords();
        // 直接对 articles操作会影响page中的数据（why）
        articles.stream()
                .map(article -> article.setCategoryName(categoryDao.selectById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        // 封装结果
        List<ArticleVo> articleVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleVo.class);
        PageVo<ArticleVo> articlePageVo = new PageVo<>(articleVos, page.getTotal());
        //返回结果
        return ResponseResult.okResult(articlePageVo);
    }

    @Override
    public ResponseResult<Article> getArticleDetail(Long id) {
        // 根据id查询文章
        Article article = getById(id);
        if (article == null) {
            // id查找不到对应文章
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        // 根据分类id获取分类名
        Category category = categoryDao.selectById(article.getCategoryId());
        if (category != null) {
            article.setCategoryName(category.getName());
        }
        // 封装成vo对象
        ArticleVo articleVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        // 封装成响应结果
        return ResponseResult.okResult(articleVo);
    }
}
