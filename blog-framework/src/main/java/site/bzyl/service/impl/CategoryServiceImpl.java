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
import site.bzyl.service.CategoryService;

import java.util.List;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-07-07 21:06:06
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ArticleDao articleDao;

    @Override
    public ResponseResult<Category> getCategoryList() {
        // 从bzyl_article表中查询status=0的所有文章对应的category_id, 返回数组并去重
        LambdaQueryWrapper<Article> lqwArticle = new LambdaQueryWrapper<>();
        // 查询所有status=0的文章
        lqwArticle.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_PUBLISHED);
        List<Article> articles = articleDao.selectList(lqwArticle);
        System.out.println(articles);


        LambdaQueryWrapper<Category> lqwCategory = new LambdaQueryWrapper();
        // 展示正常状态的分类(status = 0)
        lqwCategory.eq(Category::getStatus, SystemConstants.CATEGORY_STATUS_ENABLED);

        return null;
    }

}


