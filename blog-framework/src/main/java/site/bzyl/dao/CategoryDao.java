package site.bzyl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import site.bzyl.domain.entity.Category;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-07 21:06:06
 */
@Mapper
public interface CategoryDao extends BaseMapper<Category> {

}

