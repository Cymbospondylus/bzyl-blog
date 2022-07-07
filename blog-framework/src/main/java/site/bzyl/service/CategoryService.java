package site.bzyl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import site.bzyl.domain.ResponseResult;
import site.bzyl.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-07-07 21:06:06
 */
@Service
public interface CategoryService extends IService<Category> {

    ResponseResult<Category> getCategoryList();
}

