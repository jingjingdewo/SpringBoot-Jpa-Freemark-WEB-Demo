<!DOCTYPE html>
<html>
	<head>
		<#assign PAGE_TITLE="首页"/>
		<#include "/_header_meta.ftl"/>
		<#include "/_header_res.ftl"/>
  	</head>
  	<body>
		<!-- 顶部开始 -->
		<div class="container">
			<div class="logo">
				<a href="index">Demo</a>
			</div>
			<div class="left_open">
				<i title="展开左侧栏" class="iconfont">&#xe699;</i>
			</div>
			<ul class="layui-nav left fast-add" lay-filter="">
				<li class="layui-nav-item">
					<a href="javascript:;">+新增</a>
					<dl class="layui-nav-child">
						<!-- 二级菜单 -->
						<dd>
							<a onclick="x_admin_show('百度','http://www.baidu.com')"><i class="iconfont">&#xe6a2;</i>百度</a>
						</dd>
						<dd>
							<a onclick="x_admin_show('图片','http://image.baidu.com')"><i class="iconfont">&#xe6a8;</i>图片</a>
						</dd>
					</dl>
				</li>
			</ul>
			<ul class="layui-nav right" lay-filter="">
				<li class="layui-nav-item">
					<a href="javascript:;">${curAdminName}</a>
					<dl class="layui-nav-child">
						<!-- 二级菜单 -->
						<dd>
							<a onclick="x_admin_show('个人信息','http://www.baidu.com')">个人信息</a>
						</dd>
						<dd>
							<a onclick="x_admin_show('切换帐号','http://www.baidu.com')">切换帐号</a>
						</dd>
						<dd>
							<a href="/Sys/exit">退出</a>
						</dd>
					</dl>
				</li>
				<li class="layui-nav-item to-index">
					<a href="index">前台首页</a>
				</li>
			</ul>

		</div>
		<!-- 顶部结束 -->
		<!-- 中部开始 -->
		<!-- 左侧菜单开始 -->
		<div class="left-nav">
			<div id="side-nav">
				<ul id="nav">
					<li>
						<a href="javascript:;">
							<i class="layui-icon">&#xe653;</i>
							<cite>系统管理</cite>
							<i class="iconfont nav_right">&#xe697;</i>
						</a>
						<ul class="sub-menu">
							<li>
								<a _href="/Sys/User/index">
									<i class="iconfont">&#xe6a7;</i>
									<cite>用户管理</cite>
								</a>
							</li>
							<li>
								<a _href="/Sys/Role/index">
									<i class="iconfont">&#xe6a7;</i>
									<cite>站点管理</cite>
								</a>
							</li>
							<li>
								<a _href="/Sys/Right/index">
									<i class="iconfont">&#xe6a7;</i>
									<cite>配置管理</cite>
								</a>
							</li>
						</ul>
					</li>
					<li>
						<a href="javascript:;">
							<i class="layui-icon">&#xe614;</i>
							<cite>客户端管理</cite>
							<i class="iconfont nav_right">&#xe697;</i>
						</a>
						<ul class="sub-menu">
							<li>
								<a _href="/App/Person/index">
									<i class="iconfont">&#xe6a7;</i>
									<cite>普通用户管理</cite>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<!-- <div class="x-slide_left"></div> -->
		<!-- 左侧菜单结束 -->
		<!-- 右侧主体开始 -->
		<div class="page-content">
			<div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
				<ul class="layui-tab-title">
					<li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<iframe src='/Sys/Home/frame' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
					</div>
				</div>
			</div>
		</div>
		<div class="page-content-bg"></div>
		<!-- 右侧主体结束 -->
		<!-- 中部结束 -->
		<!-- 底部开始 -->
		<div class="footer">
			<div class="copyright">Copyright ©2020 静畏人心 All Rights Reserved</div>
		</div>
		<!-- 底部结束 -->
		<script>
		</script>
	</body>
</html>
