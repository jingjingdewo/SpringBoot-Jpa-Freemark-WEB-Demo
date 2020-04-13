package com.demo.service;

import com.demo.framework.service.BaseService;
import com.demo.model.User;

/**
 * @Author 静畏人心
 * @Description: 用户管理接口
 * @Date 2020/4/8 22:08
 */
public interface ISysUserService extends BaseService<User> {

    public User getByLoginId(String loginId);

    public User getByIdNum(String idNum);

    public boolean add(User user);

    public boolean update(User user);
}
