package site.bzyl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import site.bzyl.domain.ResponseResult;
import site.bzyl.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-07-08 21:21:30
 */
@Service
public interface LinkService extends IService<Link> {

    ResponseResult<Link> getAllLink();
}

