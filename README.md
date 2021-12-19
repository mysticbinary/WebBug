# WebBug
WebBug是用Java语言编写的Web漏洞靶场，包含常见的Web漏洞，也有一些业务逻辑漏洞。相对常见的漏洞靶场，
WebBug的优点是没有明确指明哪个页面有什么类型的漏洞，所以需要测试者自己测试过之后才能知道，相对更接近实战环境。
WebBug还有一个优点就是给出了部分漏洞的修复方案，可以在学习Web漏洞测试的同时，也能学习Java应用程序漏洞的修复方式及思路。

# 运行效果图
![avatar](web/image/WebBug_image.png)


# 漏洞类型
> 包含如下漏洞，以及修复方案。

0. 暴力破解
1. 验证码绕过（前端绕过）
2. SQL注入（Bind）
3. 越权访问（垂直越权）
4. XSS（存储型）
5. XSS（反射型）
6. CSRF
7. 任意文件上传
8. 不安全的直接对象引用
9. 失效的身份认证和会话管理
10. 不安全的配置
11. ...

# Usage
如果你已经安装docker/docker-compose可以跳过这一步
```shell
# 在Ubuntu 20.04下安装docker/docker-compose:

# 安装pip
curl -s https://bootstrap.pypa.io/get-pip.py | python3

# 安装最新版docker
curl -s https://get.docker.com/ | sh

# 启动docker服务
systemctl start docker

# 安装compose
pip install docker-compose 

# 其他操作系统安装docker和docker-compose可能会有些许不同，
# 请阅读Docker文档进行安装。
```

````shell
git clone https://github.com/mysticbinary/WebBug.git
cd WebBug
docker-compose up -d
````

# 警告
不要将此项目运行在外网服务器，除非你想被攻击。
<BR/>
本项目只做Web安全研究用途，任何人不得将其用于非法用途，否则后果自行承担！


# 手动启动时所需环境
- JDK:1.7
- Tomcat:7
- MySQL:5.7

不建议手动编译了(项目太老)，如果不想在Docker环境下使用，
将生成的ROOT.war文件，放到tomcat\webapps\目录下即可。

