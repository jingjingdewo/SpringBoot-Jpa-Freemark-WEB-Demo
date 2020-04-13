<!DOCTYPE html>
<html>
	<head>
		<#assign PAGE_TITLE="欢迎页"/>
		<#include "/_header_meta.ftl"/>
		<#include "/_header_res.ftl"/>
  	</head>
  	</head>
  	
  	<body>
		<div class="x-body layui-anim layui-anim-up">
			<blockquote class="layui-elem-quote">你好，<span class="x-red">${curAdminName}</span>！
			当前时间:2019-11-06 15:43:53</blockquote>
			<fieldset class="layui-elem-field">
				<legend>数据统计</legend>
				<div class="layui-field-box">
					<div class="layui-col-md12">
						<div class="layui-card">
							<div class="layui-card-body">
								<div class="layui-carousel x-admin-carousel x-admin-backlog" lay-anim="" lay-indicator="inside" lay-arrow="none" style="width: 100%; height: 90px;">
									<div carousel-item="">
										<ul class="layui-row layui-col-space10 layui-this">
											<li class="layui-col-xs2">
												<a href="javascript:;" class="x-admin-backlog-body">
													<h3>文章数</h3>
													<p>
														<cite>66</cite></p>
												</a>
											</li>
											<li class="layui-col-xs2">
												<a href="javascript:;" class="x-admin-backlog-body">
													<h3>会员数</h3>
													<p>
														<cite>12</cite></p>
												</a>
											</li>
											<li class="layui-col-xs2">
												<a href="javascript:;" class="x-admin-backlog-body">
													<h3>回复数</h3>
													<p>
														<cite>99</cite></p>
												</a>
											</li>
											<li class="layui-col-xs2">
												<a href="javascript:;" class="x-admin-backlog-body">
													<h3>商品数</h3>
													<p>
														<cite>67</cite></p>
												</a>
											</li>
											<li class="layui-col-xs2">
												<a href="javascript:;" class="x-admin-backlog-body">
													<h3>文章数</h3>
													<p>
														<cite>67</cite></p>
												</a>
											</li>
											<li class="layui-col-xs2">
												<a href="javascript:;" class="x-admin-backlog-body">
													<h3>文章数</h3>
													<p>
														<cite>6766</cite></p>
												</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</fieldset>
			<fieldset class="layui-elem-field">
				<legend>系统通知</legend>
				<div class="layui-field-box">
					<table class="layui-table" lay-skin="line">
						<tbody>
							<tr>
								<td>
									<a class="x-a" href="/" target="_blank">2020/4/12 第一版Spring boot Demo上线 </a>
								</td>
							</tr>
							<tr>
								<td>
									<a class="x-a" href="/" target="_blank">有问题请发送邮箱：710564610@qq.com</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</fieldset>
			<fieldset class="layui-elem-field">
				<legend>系统信息</legend>
				<div class="layui-field-box">
					<table class="layui-table">
						<tbody>
							<tr>
								<th>版本号</th>
								<td>0.0.1</td>
							</tr>
							<tr>
								<th>管理端网页地址</th>
								<td>http://localhost:8080/Sys/index</td>
							</tr>
							<tr>
								<th>客户端网页地址</th>
								<td>http://localhost:8080/App/index</td>
							</tr>
							<tr>
								<th>数据库</th>
								<td>MySql</td>
							</tr>
							<tr>
								<th>前端框架</th>
								<td>layui/X-admin</td>
							</tr>
							<tr>
								<th>系统框架</th>
								<td>Spring boot / Jpa / Freemarker / Lombok</td>
							</tr>
						</tbody>
					</table>
				</div>
			</fieldset>
			<fieldset class="layui-elem-field">
				<legend>开发团队</legend>
				<div class="layui-field-box">
					<table class="layui-table">
						<tbody>
							<tr>
								<th>版权所有</th>
								<td>@静畏人心</td>
							</tr>
							<tr>
								<th>开发者</th>
								<td>静畏人心(710564610@qq.com)</td>
							</tr>
						</tbody>
					</table>
				</div>
			</fieldset>
			<blockquote class="layui-elem-quote layui-quote-nm">整合了Spring boot + Jpa + Freemarker 的Demo,方便开发人员使用。</blockquote>
		</div>
		<script>
			
		</script>
	</body>
</html>
