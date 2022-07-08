package site.bzyl.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import site.bzyl.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;

/**
 * 友链(Link)表数据库访问层
 *
 * @author makejava
 * @since 2022-07-08 21:21:30
 */
@Mapper
public interface LinkDao extends BaseMapper<Link> {

}

