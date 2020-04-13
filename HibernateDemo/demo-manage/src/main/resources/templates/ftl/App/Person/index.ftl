<!DOCTYPE html>
<html>
    <head>
        <#include "/_header_meta.ftl"/>
        <#include "/_header_res.ftl"/>
    </head>
    <body class="layui-anim layui-anim-up">
        <input type="hidden" id="id" value="${beanUser.id}"/>
        <div class="x-nav">
            <span class="layui-breadcrumb">
                <a>首页</a>
                <a>客户端管理</a>
                <a><cite>普通普通用户管理</cite></a>
            </span>
            <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
               href="javascript:location.replace(location.href);" title="刷新">
                <i class="layui-icon" style="line-height:35px">&#xe9aa;</i>
            </a>
        </div>
        <div class="x-body">
            <div class="layui-row">
                <form class="layui-form layui-col-md12">
                    <div class="layui-inline">
                        <div class="layui-input-inline" style="margin-right: 0px;width: 100px;">
                            <select name="searchField">
                                <option value="name">普通用户姓名</option>
                                <option value="idNum">身份证</option>
                                <option value="mobile">手机号</option>
                            </select>
                        </div>
                        <div class="layui-input-inline">
                            <input type="text" name="keyword" placeholder="请输入关键字查询" autocomplete="off" class="layui-input" />
                        </div>
                        <div class="layui-input-inline">
                            <button id="btnSearch" class="layui-btn" lay-submit lay-filter="search">查询</button>
                            <button type="reset" id="btnRetSet" type="button" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </form>
            </div>

            <script type="text/html" id="toolBar">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="add">添加</button>
                    <button class="layui-btn layui-btn-sm layui-btn-danger" lay-event="delete">批量删除</button>
                </div>
            </script>
            <table id="tableModule" lay-filter="tableModule"></table>
            <script type="text/html" id="tableBar">
                <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
            </script>
        </div>
        <script>
            var tableModule;
            layui.use(['table','laypage','form','layer'], function (){
                var table = layui.table; //表格
                var laypage = layui.laypage; //分页
                var form = layui.form; //表单
                var layer = layui.layer; //弹层

                tableModule = table.render({
                    elem: '#tableModule',
                    id: 'table',
                    height: 'full-160',
                    url: '/${PAGE_REQUEST_MARK}/${PAGE_MODEL}/json/Table', //数据接口
                    method: 'post',
                    page: true, //开启分页
                    toolbar: '#toolBar',	//左侧按钮
                    //defaultToolbar:[],    //右侧筛选/导出/打印图标,可自定义
                    cols: [[ //表头
                        {type: 'checkbox', fixed: 'left'},
                        {field: 'name', title: '姓名', width: 120},
                        {field: 'idNum', title: '身份证号', width: 220},
                        {field: 'mobile', title: '手机号', width: 200},
                        {field: 'email', title: '电子邮箱', width: 180, sort: true},
                        {field: 'gender', title: '性别', width: 100, sort: true},
                        {fixed: 'right', title: '操作', align: 'center', toolbar: '#tableBar'}
                    ]]
                });

                //分页
                laypage.render({
                    elem: 'pageDemo', //分页容器的id
                    count: 100, //总页数
                    skin: '#1E9FFF', //自定义选中色值
                    //skip: true, //开启跳页
                    jump: function (obj, first) {
                        if (!first) {
                            layer.msg('第' + obj.curr + '页', {
                                offset: 'b'
                            });
                        }
                    }
                });

                //监听头工具栏事件
                table.on('toolbar(tableModule)', function (obj) {
                    var checkStatus = table.checkStatus(obj.config.id);
                    var data = checkStatus.data; //获取选中的数据
                    var ids = [];
                    for (var i = 0; i < data.length; i++) {
                        ids.push(data[i].id);
                    }
                    switch (obj.event) {
                        case 'add':
                            x_admin_show('添加普通用户', '/${PAGE_REQUEST_MARK}/${PAGE_MODEL}/html/Add', 720, 500);
                            break;
                        case 'delete':
                            if (data.length === 0) {
                                layer.msg('请选择一行');
                            } else {
                                layer.confirm('确定要删除选中的' + data.length + '条数据?', function (index) {
                                    $.ajax({
                                        url: '/${PAGE_REQUEST_MARK}/${PAGE_MODEL}/json/Delete',
                                        type: "post",
                                        async: false,
                                        traditional: true,
                                        data: {
                                            ids: ids
                                        },
                                        success: function (data) {
                                            if(data.statusCode == 1){
                                                table.reload('table', {page: {page: 1}});
                                                layer.close(index);
                                            } else {
                                                layer.msg("删除失败", {icon: 5})
                                            }
                                        }
                                    });
                                });
                            }
                            break;
                    }
                });

                //监听行工具事件
                table.on('tool(tableModule)', function (obj) {
                    var data = obj.data;
                    if (obj.event === 'del') {
                        layer.confirm('确定要删除普通用户"' + data.name + '"?', function (index) {
                            $.ajax({
                                url: '/${PAGE_REQUEST_MARK}/${PAGE_MODEL}/json/Delete',
                                type: "post",
                                async: false,
                                data: {
                                    id: data.id
                                },
                                success: function (data) {
                                    if(data.statusCode == 1){
                                        layer.close(index);
                                        tableModule.reload({page: {page: 1}});
                                    } else {
                                        layer.msg("删除失败", {icon: 5})
                                    }
                                }
                            });
                        });
                    } else if (obj.event == 'edit') {
                        x_admin_show('编辑普通用户', '/${PAGE_REQUEST_MARK}/${PAGE_MODEL}/html/Edit/' + data.id, 720, 500);
                    }
                });

                form.on('submit(search)', function (data) {
                    tableModule.reload({where: data.field, page: {page: 1}});
                    return false;
                });
            });
        </script>
    </body>
</html>