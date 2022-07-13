package site.bzyl.service;


import site.bzyl.domain.ResponseResult;
import site.bzyl.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);
}
