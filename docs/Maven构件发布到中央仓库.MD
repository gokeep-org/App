#Maven 构件发布流程

######发现这个比想象中麻烦，整理出来, 用的时候的少些麻烦吧
- 先去 https://issues.sonatype.org 注册一个账号，这里使用JIRA管理申请流程
- 创建一个Issue，issue type：new Project, Summary为issue名称，注意的是Group Id和maven中的一定要正确而且对应，ProjectURl，SCM url等
- 等待人工审核issue，外国人要倒时差还不爱加班，应该会第二天给你邮件回复
- 如果邮件审核通过，可以进行下一步，否则，重复以上步骤
- 这里开始进行相关的配置，先配置maven/conf/setting.xml，添加server，有示例就是填写申请的sonatype账号和密码，示例如下：
```xml
	<server>
      <id>sonatype-nexus-snapshots</id>
      <username>yifangyun_xuning</username>
      <password>*****<password>
    </server>
    <server>
      <id>sonatype-nexus-staging</id>
      <username>yifangyun_xuning</username>
      <password>*****<password>
    </server>
```

- 然后开始配置自己的maven项目pom文件，需要在基础上添加licenses， scm， developers信息，在自己的maven项目中配置发布信息，这个其实主要是所有者的信息
```xml
 <!--Deploy-->
    <parent>
        <groupId>org.sonatype.oss</groupId>
        <artifactId>oss-parent</artifactId>
        <version>7</version>
    </parent>
    <!--Licenses-->
    <licenses>
        <license>
            <name>The Apache Software License, Version 2.0</name>
            <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
            <distribution>repo</distribution>
        </license>
    </licenses>
    <!--Scm Config-->
    <scm>
        <tag>master</tag>
        <url>https://github.com/xuning-yifangyun/platform-sdk.git</url>
        <connection>scm:git:git@github.com:xuning-yifangyun/platform-sdk.git</connection>
        <developerConnection>scm:git:git@github.com:xuning-yifangyun/platform-sdk.git</developerConnection>
    </scm>
    <!--my infoemation-->
    <developers>
        <developer>
            <name>yifangyun_xuning</name>
            <email>1748373312@qq.com</email>
            <organization>fangcloud</organization>
        </developer>
    </developers>
    <!--GPG-->
```
- 这里要注意的是发布的版本号，不建议为SNAPSHOT类型，因为这样会出现可以下载但不能查询到的问题，也不能走正常的JIRA流程管理
- 因为自己的构建保存在natype.org的 https://oss.sonatype.org/ （oss仓库）上面，要先发布到这里
- 发布之前，要进行gpg签名，步骤如下：
- gpg gen-key生成gpg密钥对，gpg --list-keys可查看秘钥对
- 将公钥布到PGP秘钥服务器：gpg --keyserver hkp://pool.sks-keyservers.net --send-keys 公钥
- 项目pom下，运行maven命令mvn clean deploy -P sonatype-oss-release -Darguments="gpg.passphrase=gpg密码"，中间要输入GPG密码获取私钥进行数字签名，不数字签名应该也可以，但估计会查询不到maven项目
- 发布完成时候去sonatype的oss找到自己到自己构件，找不到很可能就是签名和版本问题，先close一下构件，这里其实是验证自己的权限，然后发布就可以了，
- 在oss仓库中已经存在了自己的构件，但是还不能被查询到，需要继续人工审核，还要一天的时间左右，会邮件回复。

