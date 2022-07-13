package site.bzyl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import site.bzyl.domain.ResponseResult;
import site.bzyl.domain.entity.BlogUserLoginVo;
import site.bzyl.domain.entity.LoginUser;
import site.bzyl.domain.entity.User;
import site.bzyl.domain.vo.UserInfoVo;
import site.bzyl.service.BlogLoginService;
import site.bzyl.utils.BeanCopyUtils;
import site.bzyl.utils.JwtUtil;
import site.bzyl.utils.RedisCache;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {
    // 在SecurityConfig中将Authentication注入, 用于用户认证
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User user) {
        // 将user中的username和password封装成authenticationToken对象用于认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        // 重写AuthenticationManager的实现类ProviderManager, 调用authenticate方法认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        // 如果认证失败, 抛出异常
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 认证成功, 将authentication中的LoginUser取出
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User selectedUser = loginUser.getUser();
        String userId = selectedUser.getId().toString();
        // 将UserId和用户信息LoginUser存入Redis
        redisCache.setCacheObject("blogLogin:" + userId, loginUser);
        // 用Userid生成Jwt
        String jwt = JwtUtil.createJWT(userId);
        // 将UserId和Jwt封装返回前端
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(selectedUser, UserInfoVo.class);
        BlogUserLoginVo blogUserLoginVo = new BlogUserLoginVo(jwt, userInfoVo);
        return ResponseResult.okResult(blogUserLoginVo);
    }
}
