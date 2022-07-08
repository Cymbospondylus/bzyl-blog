package site.bzyl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.bzyl.constants.SystemConstants;
import site.bzyl.dao.ArticleDao;
import site.bzyl.dao.CategoryDao;
import site.bzyl.domain.ResponseResult;
import site.bzyl.domain.entity.Article;
import site.bzyl.domain.entity.Category;
import site.bzyl.domain.vo.CategoryListVo;
import site.bzyl.service.ArticleService;
import site.bzyl.service.CategoryService;
import site.bzyl.utils.BeanCopyUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-07-07 21:06:06
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {

    // 注入service而不是dao
    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult<Category> getCategoryList() {
        // 从bzyl_article表中查询status=0的所有文章对应的category_id, 返回数组并去重
        LambdaQueryWrapper<Article> lqwArticle = new LambdaQueryWrapper<>();
        // 查询所有正常发布(status=0)的文章
//        lqwArticle.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED);
        // 获取所有文章的分类id
        List<Article> articleList = articleService.list(lqwArticle);
        List<Long> categoryIds = articleList.
                stream()
                .map(article -> article.getCategoryId())
                .distinct()
                .collect(Collectors.toList());
        // 根据id查询所有可用分类, 因为是在CategoryServiceImpl里写的, 所以不用注入直接调用mp提供的方法
        // 同时进行去重和判断分类是否可用
        List<Category> categoryList = listByIds(categoryIds)
                .stream()
                .distinct()
                .filter(category -> SystemConstants.CATEGORY_STATUS_ENABLED.equals(category.getStatus()))
                .collect(Collectors.toList());

       // 封装成vo
        List<CategoryListVo> categoryListVos = BeanCopyUtils.copyBeanList(categoryList, CategoryListVo.class);

        return ResponseResult.okResult(categoryListVos);
    }

}


