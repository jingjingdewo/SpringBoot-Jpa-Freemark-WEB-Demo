<!DOCTYPE html>
<html lang="en">
<head>
  <#assign PAGE_TITLE="about"/>
  <#include "/App/_header_meta.ftl"/>
  <#include "/App/_header_res.ftl"/>
</head>
<body>
  <div class="header">
    <div class="menu-btn">
      <div class="menu"></div>
    </div>
    <h1 class="logo">
      <a href="index">
        <span>MYBLOG</span>
        <img src="/demo/img/logo.png">
      </a>
    </h1>
    <div class="nav">
      <a href="index">文章</a>
      <a href="whisper">微语</a>
      <a href="leacots">留言</a>
      <a href="album">相册</a>
      <a href="about"  class="active">关于</a>
    </div>
    <ul class="layui-nav header-down-nav">
      <li class="layui-nav-item"><a href="index">文章</a></li>
      <li class="layui-nav-item"><a href="whisper">微语</a></li>
      <li class="layui-nav-item"><a href="leacots">留言</a></li>
      <li class="layui-nav-item"><a href="album">相册</a></li>
      <li class="layui-nav-item"><a href="about"  class="active">关于</a></li>
    </ul>
    <p class="welcome-text">
      欢迎来到<span class="name">静畏人心</span>的博客~
    </p>
  </div>

  <div class="about-content">
    <div class="w1000">
      <div class="item info">
        <div class="title">
          <h3>我的介绍</h3>
        </div>
        <div class="cont">
          <img src="/demo/img/xc_img1.jpg">
          <div class="per-info">
            <p>
              <span class="name">静畏人心</span><br />
              <span class="age">24岁</span><br />
              <span class="Career">设计师, 前端工程师</span><br />
              <span class="interest">爱好旅游,打游戏</span>
            </p>
          </div>
        </div>
      </div>
      <div class="item tool">
        <div class="title">
          <h3>我的技能</h3>
        </div>
        <div class="layui-fluid">
          <div class="layui-row">
            <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
              <div class="cont-box">
                <img src="/demo/img/gr_img2.jpg">
                <p>80%</p>
              </div>
            </div>
            <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
              <div class="cont-box">
                <img src="/demo/img/gr_img3.jpg">
                <p>80%</p>
              </div>
            </div>
            <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
              <div class="cont-box">
                <img src="/demo/img/gr_img4.jpg">
                <p>80%</p>
              </div>
            </div>
            <div class="layui-col-xs6 layui-col-sm3 layui-col-md3">
              <div class="cont-box">
                <img src="/demo/img/gr_img5.jpg">
                <p>80%</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="item contact">
        <div class="title">
          <h3>联系方式</h3>
        </div>
        <div class="cont">
          <img src="/demo/img/erweima.jpg">
          <div class="text">
            <p class="WeChat">微信：<span>1234567890</span></p>
            <p class="qq">qq：<span>123456789</span></p>
            <p class="iphone">电话：<span>123456789</span></p>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  

  <div class="footer-wrap">
    <div class="footer w1000">
      <div class="qrcode">
        <img src="/demo/img/erweima.jpg">
      </div>
      <div class="practice-mode">
        <img src="/demo/img/down_img.jpg">
        <div class="text">
          <h4 class="title">我的联系方式</h4>
          <p>微信<span class="WeChat">1234567890</span></p>
          <p>手机<span class="iphone">1234567890</span></p>
          <p>邮箱<span class="email">1234567890@qq.com</span></p>
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
    layui.config({
      base: '/demo/js/util/'
    }).use(['element','laypage','form','layer','menu'],function(){
      element = layui.element,laypage = layui.laypage,form = layui.form,layer = layui.layer,menu = layui.menu;
      menu.init();
    })
  </script>
</body>
</html>