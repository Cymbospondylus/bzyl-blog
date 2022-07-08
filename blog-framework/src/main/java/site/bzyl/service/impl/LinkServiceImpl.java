package site.bzyl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.bzyl.constants.SystemConstants;
import site.bzyl.dao.LinkDao;
import site.bzyl.domain.ResponseResult;
import site.bzyl.domain.entity.Link;
import site.bzyl.domain.vo.LinkVo;
import site.bzyl.service.LinkService;
import site.bzyl.utils.BeanCopyUtils;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2022-07-08 21:21:30
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkDao, Link> implements LinkService {

    @Override
    public ResponseResult<Link> getAllLink() {
        // 查询所有审核通过的友链（status = 0）
        LambdaQueryWrapper<Link> linkLambdaQueryWrapper = new LambdaQueryWrapper<>();
        linkLambdaQueryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_AVAILABLE);
        List<Link> linkList = list(linkLambdaQueryWrapper);
        // 拷贝成vo对象
        List<LinkVo> linkVoList = BeanCopyUtils.copyBeanList(linkList, LinkVo.class);
        // 封装为响应结果
        return ResponseResult.okResult(linkVoList);
    }
}

