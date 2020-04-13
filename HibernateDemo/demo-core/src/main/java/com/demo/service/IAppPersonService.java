package com.demo.service;

import com.demo.framework.service.BaseService;
import com.demo.model.Person;

/**
 * @Author 静畏人心
 * @Description: 客户端用户管理接口
 * @Date 2020/4/12 13:35
 */
public interface IAppPersonService extends BaseService<Person> {

    public Person getByLoginId(String loginId);

    public Person getByIdNum(String idNum);

    public boolean add(Person person);

    public boolean update(Person person);
}
