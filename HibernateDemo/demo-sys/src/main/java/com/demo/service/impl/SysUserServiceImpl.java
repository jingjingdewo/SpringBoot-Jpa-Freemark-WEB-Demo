package com.demo.service.impl;

import com.demo.framework.dao.entity.CriteriaCondition;
import com.demo.framework.dao.entity.OrderCondition;
import com.demo.framework.dao.enums.CriteriaEnum;
import com.demo.framework.service.impl.BaseServiceImpl;
import com.demo.framework.utils.PasswordUtil;
import com.demo.model.User;
import com.demo.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author 静畏人心
 * @Description: 用户管理接口实现
 * @Date 2020/4/8 22:09
 */
@Service("iSysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<User> implements ISysUserService {
    @Override
    public User getByLoginId(String loginId) {
        List<CriteriaCondition> criteriaConditions = new ArrayList<CriteriaCondition>();
        criteriaConditions.add(new CriteriaCondition(CriteriaEnum.Equal, "deleted", "deleted", false));
        criteriaConditions.add(new CriteriaCondition(CriteriaEnum.Equal, "loginId", "loginId", loginId));
        return getFristModel(criteriaConditions, null);
    }

    @Override
    public User getByIdNum(String idNum) {
        List<CriteriaCondition> criteriaConditions = new ArrayList<CriteriaCondition>();
        criteriaConditions.add(CriteriaCondition.getCriteria(CriteriaEnum.Equal, "idNum", "idNum", idNum));
        criteriaConditions.add(CriteriaCondition.getCriteria(CriteriaEnum.Equal, "deleted", "deleted", false));
        return getFristModel(criteriaConditions, null);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean add(User model) {
        try {
//            model.setPwd("888888");
            String salt = PasswordUtil.generateString(PasswordUtil.PASSWORD_SALT_LENGTH); //获取盐值
            model.setSecretKey(salt);
            model.setPwd(PasswordUtil.passwordToPasswordSalt(model.getPwd(), salt)); //设置密码
            model.setCreateTime(new Date());
            dao.save(model);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean update(User model) {
        try {
            dao.saveOrUpdate(model);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
