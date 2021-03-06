<!DOCTYPE html>
<html>

<head>
    <#assign PAGE_TITLE="出错了"/>
    <#include "/templates/ftl/_header_meta.ftl"/>
    <link rel="stylesheet" href="../static/layui/2.3.0/css/layui.css" media="all">  
    <link rel="stylesheet" type="text/css" href="../static/fonts/4.7.0/font-awesome.css">
    <link rel="stylesheet" href="../static/admin/css/app.css" media="all">
    <style type="text/css">
    * {
        word-wrap: break-word;
    }
    p {
        margin-bottom: 10px;
    }
    .pd-20 {
        padding: 20px;
	}
	.cl, .clearfix {
	    zoom: 1;
	}
	header, footer, section, aside, details, menu, article, section, nav, address, hgroup, figure, figcaption, legend {
	    display: block;
	    margin: 0;
	    padding: 0;
	}
	.page-404 {
	    color: #afb5bf;
	    padding-top: 60px;
	    padding-bottom: 90px;
	}
	.text-c {
	    text-align: center;
	}
	.page-404 .error-title {
	    font-size: 80px;
	}
	.page-404 .error-description {
	    font-size: 24px;
	}
	.page-404 .error-info {
	    font-size: 14px;
	}
    </style>
</head>

<body>
    <div class="wf-article">
        <article class="cl pd-20">
            <section class="page-404 minWP text-c">
              <p class="error-title"><i class="layui-icon layui-icon-face-smile" style="font-size:80px"></i><span class="va-m"> 500</span></p>
              <p class="error-description">对不起，服务器发生错误~</p>
              <p class="error-info">${message.message }</p>
            </section>
        </article>
    </div>
</body>
</html>