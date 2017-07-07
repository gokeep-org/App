# App
=== 作者： 徐宁===

=== Email： 1748373312@qq.com===

=== Copyright ©2017 xuning 

### 项目结构
    APP---------------------PROJECT
    |--data(数据目录)
    |--docs（文档目录）
    |--src（源码）
       |--main
          |--java
             |--com.app
                |--action(事件仓库)
                |--config(配置)
                |--domain(领域模型)
                |--exception(异常及处理)
                |--filter(事件过滤)
                |--libeary(内部引用库)
                |--rest(REST-API接口)
                |--service(业务接口及实现)
                |--util(通用组件)
          |--resources(配置文件)
          |--test(单元测试)
             |--com.app

### 部署
    1：使用Intellij IDEA打开此项目
    2：引入pom文件

### 快速实践
    1：运行src/main/java/com/app/AppStart.java文件
    2：postmain或浏览器GET请求http://localhost:10001/test/info?flag=true
    3：返回以下结果，说明实践成功

```jaon```

    {
        "flag": true,
        "msg": "This is a rest test interface",
        "success": true
    }
``````