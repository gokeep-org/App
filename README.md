# 金特莱数据上报解析上传服务

### 概览
    本项目基于springcloud微服务构建，底层支持服务集群部署以应对网络高并发场景，基本流程为：
    1：设备上报消息发送到本服务的socket服务端口，默认端口为34885
    2：socket接受到消息会进行初步解析，发送到rabbitmq
    3：单节点情况下是自消费模式，多节点会多个客户端处理，解析实体，入库

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
                |--dtu（数据处理的全部核心模块）
          |--resources(配置文件)
          |--test(单元测试)
             |--com.app

### 部署
    1：使用Intellij IDEA打开此项目
    2：引入pom文件
    3：本地开发直接运行主类即可
    4：线上运行可以打包后，使用nohub后台运行
    5：便捷启动，使用/bin下面的启动脚本启动

### 配置
    配置包含两个地方
    1： application.yml, 这个是项目本身的配置
    2： com.app.dtu.config.DtuConfig 这个常量配置为DTU设备处理的全部配置信息，均有注释说明
   
### 文档
    其他的文档信息在docs目录下