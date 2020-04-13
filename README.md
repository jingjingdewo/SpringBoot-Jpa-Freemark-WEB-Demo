# SpringBoot-Jpa-Freemark-WEB-Demo
说明： 
这时一个整合了SpringBoot/Jpa/Freemark/Lombok/Layui的Demo，可供大型集成多模块项目开发学习。
  
前端框架为layui/X-admin，数据库为mysql，软件开发框架SpringBoot/Jpa/Freemark/Lombok，内容逻辑较为复杂，但是可重用性极高，自定义程度较高。 
  
目前仅完成了超级管理员/管理员/普通用户登录和管理员/普通用户管理模块，其中包含了封装好的增删改查批处理等操作，能处理基本的业务逻辑内容，命名清晰简洁，方便学习和理解。

1.首先将项目导入到IDEA中，在File>>setting中配置好maven
  
2.在File>>Project Structure>>Project中设置如下图：

![Project](https://github.com/jingjingdewo/SpringBoot-Jpa-Freemark-WEB-Demo/blob/master/pic2.jpg?raw=true)

3.在File>>Modules>>"+">>Import Modules>>选中任意一个子项目如"app-web">>Appley 设置完成后如下图：

![Modules](https://github.com/jingjingdewo/SpringBoot-Jpa-Freemark-WEB-Demo/blob/master/pic3.jpg?raw=true)

4.在mysql中创建一个名为demo的数据库，本项目共有三张表，启动项目后均会自动创建，不需要手动创建

5.等待5-10分钟所有jar包下载完毕后，找到HibernateDemo\demo-web\src\main\java\com\demo\WebApplication.java启动项目即可

6.超级管理员登录页面：http://localhost:8080/Sys，用户名:sadmin,密码:888888

  普通登录页面：http://localhost:8080/App, 用户名和密码自行在管理端的普通用户管理处添加即可
  
7.前端layui开发文档：https://www.layui.com/doc/ （参考demo,需要什么就去网站上复制什么就好了，基本没难度） 

8.超级管理员(服务端)登录页面：
  ![Modules](https://github.com/jingjingdewo/SpringBoot-Jpa-Freemark-WEB-Demo/blob/master/pic4.jpg?raw=true)
  用户管理界面效果图：
  ![Modules](https://github.com/jingjingdewo/SpringBoot-Jpa-Freemark-WEB-Demo/blob/master/pic5.jpg?raw=true)
  普通用户(客户端)登录页面：
  ![Modules](https://github.com/jingjingdewo/SpringBoot-Jpa-Freemark-WEB-Demo/blob/master/pic6.jpg?raw=true)
  客户端主页界面效果图：
  ![Modules](https://github.com/jingjingdewo/SpringBoot-Jpa-Freemark-WEB-Demo/blob/master/pic7.jpg?raw=true)

上面操作的图片加载有一丢丢大，有意向的朋友耐心等待30s左右

如有问题可发邮箱：710564610@qq.com 或者加QQ：710564610(备注-javaweb Demo咨询)
