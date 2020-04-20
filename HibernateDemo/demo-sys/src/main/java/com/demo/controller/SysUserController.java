package com.demo.controller;

import com.demo.entity.Search;
import com.demo.framework.annotate.Log;
import com.demo.framework.dao.entity.CriteriaCondition;
import com.demo.framework.dao.entity.LaPage;
import com.demo.framework.dao.entity.OrderCondition;
import com.demo.framework.dao.enums.CriteriaEnum;
import com.demo.framework.dao.enums.OrderEnum;
import com.demo.framework.enums.ActionEnums;
import com.demo.framework.model.Message;
import com.demo.framework.utils.GsonUtil;
import com.demo.framework.utils.PasswordUtil;
import com.demo.model.User;
import com.demo.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 静畏人心
 * @Description: 添加注释
 * @Date 2020/4/8 21:41
 */
@Controller("SysUserController")
@RequestMapping("/Sys/User")
public class SysUserController extends SysBaseController{

    private final static String FOLDER = "Sys/User/";

    private final static String MODEL = "User";

    private final static String REQUEST_MARK = "Sys";

    @Autowired
    private ISysUserService sysUserService;
    /**
     * @Author 静畏人心
     * @Date 2020/4/8 22:00
     * @Description 系统用户管理首页
     * @param modelMap
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public Object index(ModelMap modelMap) {
        modelMap.put("PAGE_MODEL", MODEL);
        modelMap.put("PAGE_REQUEST_MARK", REQUEST_MARK);
        return FOLDER + "index";
    }

    /**
     * 获取报名人员单位数据-表格
     *
     * @param search
     * @return
     */
    @Log(description = "获取报名人员单位数据", action = ActionEnums.Visit)
    @RequestMapping("json/Table")
    @ResponseBody
    public Object jsonTable(Search search) {
        LaPage<User> laPage = new LaPage<>();
        laPage.setPage(search.getPage());
        laPage.setPageSize(search.getLimit());
        List<CriteriaCondition> criteriaConditions = new ArrayList<CriteriaCondition>();
        criteriaConditions.add(CriteriaCondition.getCriteria(CriteriaEnum.Equal, "deleted", "deleted", false));
        if (!StringUtils.isEmpty(search.getSearchField()) && !StringUtils.isEmpty(search.getKeyword())) {
            criteriaConditions.add(CriteriaCondition.getCriteria(CriteriaEnum.Like, search.getSearchField(),
                    search.getSearchField(), search.getKeyword()));
        }
        List<OrderCondition> orderConditions = new ArrayList<OrderCondition>();
        orderConditions.add(OrderCondition.addOrder(OrderEnum.desc, "id"));
        laPage = sysUserService.getPage(laPage, criteriaConditions, orderConditions);
        return GsonUtil.toJson(laPage);
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/8 22:03
     * @Description 系统用户新增页面
     * @param modelMap
     */
    @RequestMapping(value = "html/Add", method = RequestMethod.GET)
    public Object htmlAdd(ModelMap modelMap) {
        modelMap.put("model", new User());
        modelMap.put("PAGE_MODEL", MODEL);
        modelMap.put("PAGE_REQUEST_MARK", REQUEST_MARK);
        return FOLDER + "form";
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/8 22:05
     * @Description 系统用户修改页面
     * @param modelMap
     */

    @RequestMapping(value = "html/Edit/{modelId}", method = RequestMethod.GET)
    public Object htmlEdit(ModelMap modelMap, @PathVariable Long modelId) {
        User user = sysUserService.get(modelId);
        modelMap.put("model", user);
        modelMap.put("PAGE_MODEL", MODEL);
        modelMap.put("PAGE_REQUEST_MARK", REQUEST_MARK);
        return FOLDER + "form";
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/12 0:19
     * @Description 系统用户添加数据
     * @param model
     */
    @RequestMapping("/json/Add")
    @ResponseBody
    public Object jsonAdd(User model) {
        Message msg = new Message();
        model.setId(null);
        try {
            User curUser = getCurAdmin();
            msg.setSucceedMsg("验证通过");
            if (StringUtils.isBlank(model.getName())) {
                msg.setFailMsg("请填写姓名");
            } else if (StringUtils.isBlank(model.getIdNum())) {
                msg.setFailMsg("请填写证件号码");
            }
            if(checkLoginId(model.getLoginId()) == "0"){
                msg.setFailMsg("用户名已经被注册");
            }else if (checkIdNum(model.getIdNum()) == "0") {
                msg.setFailMsg("证件号码已经被注册");
            }else {
                model.setSecretKey(PasswordUtil.generateString(PasswordUtil.PASSWORD_SALT_LENGTH));
                model.setPwd(PasswordUtil.passwordToPasswordSalt(model.getPwd(), model.getSecretKey()));
                model.setAlive(true);
                model.setCreatorId(curUser.getId());
                model.setCreatorName(curUser.getName());
                sysUserService.save(model);
                msg.setSucceedMsg("保存成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setFailMsg("保存失败");
        }
        return msg;
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/12 16:26
     * @Description 检查身份证是否已被注册
     * @param idNum
     */
    @RequestMapping(value = "/checkIdNum", method = RequestMethod.GET)
    @ResponseBody
    public String checkIdNum(String idNum) {
        User user = sysUserService.getByIdNum(idNum);
        if (user == null) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/12 16:26
     * @Description 检查用户名是否已被注册
     * @param loginId
     */
    @RequestMapping(value = "/checkLoginId", method = RequestMethod.GET)
    @ResponseBody
    public String checkLoginId(String loginId) {
        User user = sysUserService.getByLoginId(loginId);
        if (user == null) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/12 0:19
     * @Description 系统用户修改数据
     * @param model
     */
    @RequestMapping("/json/Edit")
    @ResponseBody
    public Object jsonEdit(User model) {
        Message msg = new Message();
        User old = sysUserService.get(model.getId());
        if (old == null || old.getDeleted()) {
            msg.setMessage(getMessage("data.deleted"));
        }else{
            try {
                User curUser = getCurAdmin();
                if (StringUtils.isBlank(model.getName())) {
                    msg.setFailMsg("请填写姓名");
                } else if (StringUtils.isBlank(model.getIdNum())) {
                    msg.setFailMsg("请填写证件号码");
                }
                if(!old.getLoginId().equals(model.getLoginId()) && checkLoginId(model.getLoginId()) == "0"){
                    msg.setFailMsg("用户名已经被注册");
                }else if (!old.getIdNum().equals(model.getIdNum()) && checkIdNum(model.getIdNum()) == "0") {
                    msg.setFailMsg("证件号码已经被注册");
                }else {
                    model.setAlive(true);
                    model.setMenderId(curUser.getId());
                    model.setMenderName(curUser.getName());
                    sysUserService.saveOrUpdate(model);
                    msg.setSucceedMsg("保存成功");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msg.setFailMsg("保存失败");
            }
        }
        return msg;
    }

    /**
     * @Author 静畏人心
     * @Date 2020/4/11 21:39
     * @Description 系统用户删除数据
     * @param search
     */
    @RequestMapping("json/Delete")
    @ResponseBody
    public Object jsonDelete(Search search) {
        Message msg = new Message();
        User curUser = getCurAdmin();
        String[] delIds = search.getDelIds().split(",");
        List<User> userList = new ArrayList<>(delIds.length);
        for (String delId : delIds) {
            User user = sysUserService.get(Long.valueOf(delId));
            user.setDeleted(true);
            user.setMenderId(curUser.getId());
            user.setMenderName(curUser.getName());
            userList.add(user);
        }
        try {
            sysUserService.saveOrUpdate(userList);
            msg.setSucceedMsg(delIds.length > 1 ? "批量删除成功" : "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            msg.setFailMsg("删除失败");
        }
        return msg;
    }
}
