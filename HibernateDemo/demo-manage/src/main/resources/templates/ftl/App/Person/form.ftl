<!DOCTYPE html>
<html>
    <head>
        <#include "/_header_meta.ftl"/>
        <#include "/_header_res.ftl"/>
        <script src="/js/Util/formUtil.js"></script>
    </head>
    <body>
        <div class="x-body">
            <form class="layui-form" lay-filter="form">
                <input type="hidden" name="id" value="${model.id}"/>
                账户信息
                <hr class="layui-bg-cyan">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label for="loginId" class="layui-form-label"> <span class="x-red">*</span>用户名</label>
                        <div class="layui-input-inline">
                            <input type="text" id="loginId" name="loginId" required="" placeholder="输入用户名"
                                   lay-verify="loginId" autocomplete="off" class="layui-input" value="${model.loginId}">
                        </div>
                    </div>
                    <#if !model.id??>
                        <div class="layui-inline">
                            <label for="pwd" class="layui-form-label"> <span class="x-red">*</span>密码</label>
                            <div class="layui-input-inline">
                                <input type="password" id="pwd" name="pwd" required="" placeholder="输入密码"
                                       lay-verify="pwd" autocomplete="off" class="layui-input" value="">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label for="pwd" class="layui-form-label"> <span class="x-red">*</span>确认密码</label>
                            <div class="layui-input-inline">
                                <input type="password" id="pwd2" name="pwd2" required="" placeholder="确认密码"
                                       lay-verify="pwd2" autocomplete="off" class="layui-input" value="">
                            </div>
                        </div>
                    <#else>
                        <div class="layui-inline">
                            <label for="pwd" class="layui-form-label"> <span class="x-red">*</span>密码</label>
                            <div class="layui-input-inline">
                                <input placeholder="输入密码" autocomplete="off" class="layui-input" value="请自行添加重置密码功能" disabled>
                            </div>
                        </div>
                    </#if>
                </div>
                基本信息
                <hr class="layui-bg-red">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label for="name" class="layui-form-label"> <span class="x-red">*</span>姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" id="name" name="name" required="" placeholder="输入姓名"
                                   lay-verify="name" autocomplete="off" class="layui-input" value="${model.name}">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label for="idNum" class="layui-form-label"><span class="x-red">*</span>身份证</label>
                        <div class="layui-input-inline">
                            <input type="text" id="idNum" name="idNum" required="" placeholder="输入身份证号码"
                                   lay-verify="idNum" autocomplete="off" class="layui-input" value="${model.idNum}">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label for="mobile" class="layui-form-label"> <span class="x-red">*</span>手机号</label>
                        <div class="layui-input-inline">
                            <input type="mobile" id="mobile" name="mobile" required="" placeholder="输入手机号"
                                   lay-verify="mobile" autocomplete="off" class="layui-input"  value="${model.mobile}">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label for="email" class="layui-form-label"> <span class="x-red">*</span>电子邮箱</label>
                        <div class="layui-input-inline">
                            <input type="email" id="email" name="email" placeholder="输入电子邮箱"
                                   lay-verify="email" autocomplete="off" class="layui-input" value="${model.email}">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label for="gender" class="layui-form-label"> <span class="x-red">*</span>性别</label>
                        <div class="layui-input-block">
                            <input type="radio" name="gender" value="男" title="男" <#if model.gender == '男'>checked</#if>>
                            <input type="radio" name="gender" value="女" title="女" <#if model.gender == '女'>checked</#if>>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item" align="center">
                    <button class="layui-btn" lay-filter="save" lay-submit="">保存</button>
                </div>
            </form>
        </div>
        <script>
            layui.use(['form','layer'], function(){
                var form = layui.form;
                var layer = layui.layer;

                //自定义验证规则
                form.verify({
                    loginId: [/^[A-Za-z0-9]{3,16}$/, '用户名必须为3-16位数字或英文字母'],
                    idNum: function(value){
                        if(value.isIdCard()!="true"){
                            return '请输入合法的身份证号码';
                        }
                    },
                    pwd: [/(.+){6,16}$/, '密码必须6到16位'],
                    pwd2: function(value){
                        if($('#pwd').val()!=$('#pwd2').val()){
                            return '两次密码不一致';
                        }
                    }
                });

                //监听提交
                form.on('submit(save)', function(data){
                    $.ajax({
                        url: "/${PAGE_REQUEST_MARK}/${PAGE_MODEL}/json/${model.id!?then('Edit','Add')}" ,
                        type: "post",
                        async: false,
                        data: data.field,
                        success: function(data) {
                            if(data.statusCode == 1){
                                parent.layer.msg(data.message, {
                                    time: 2000,
                                    icon: 1
                                });
                                var index = parent.layer.getFrameIndex(window.name);
                                parent.layer.close(index);
                                parent.tableModule.reload({});
                            }else {
                                parent.layer.msg(data.message, {
                                    time: 2000,
                                    icon: 5
                                });
                            }
                        }
                    });
                    return false;
                });
            });
        </script>
    </body>
</html>
