<!DOCTYPE html>
<html>

<head>
    <#assign PAGE_TITLE="登录"/>
    <#include "/_header_meta.ftl"/>
    <#include "/_header_res.ftl"/>
    <style>
        .text {
            width: 400px;
            position: absolute;
            bottom: 20px;
            right: 20px;
            height: 10px;
            z-index: 3;
            margin: auto;
            text-align: center;
        }
    </style>
</head>
<body class="login-bg">
<div class="login layui-anim layui-anim-up">
    <div class="message">SpringBoot Demo 管理端</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form">
        <input name="username" placeholder="用户名" type="text" lay-verify="required" class="layui-input">
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码" type="password" class="layui-input">
        <hr class="hr15">
        <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20">
    </form>
    <div class="text">
        <span>Copyright ©2020 静畏人心 All Rights Reserved</span>
    </div>
</div>
<script type="text/javascript">
    layui.use(['layer', 'jquery', 'form'], function () {
        var form = layui.form, $ = layui.jquery;
        form.on('submit(login)', function(data) {
            $("[lay-filter=login]").attr("disabled","disabled");
            $("[lay-filter=login]").val("正在登录中...");
            window.setTimeout(function () {
                $.ajax({
                    url: "/Sys/json/login",
                    data: data.field,
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if(data.statusCode == 1){
                            $("[lay-filter=login]").val("验证通过，正在跳转中...");
                            window.setTimeout(function () {
                                window.location.href = "/Sys/Home/index";
                            }, 1000);
                        }else{
                            layer.msg(data.message,{icon: 5});
                            $("[lay-filter=login]").removeAttr("disabled","disabled");
                            $("[lay-filter=login]").val("登录");
                        }
                    }
                });
            }, 500);
            return false;
        });
    });
</script>
</body>
</html>
