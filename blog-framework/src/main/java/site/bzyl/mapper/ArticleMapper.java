package site.bzyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import site.bzyl.domain.entity.Article;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
