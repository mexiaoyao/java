参考地址：
https://space.bilibili.com/380474484
1:创建实体类；
在Idea首页依次打开 File - Settings - Plugins
<!--lombok @Data-->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.12</version>
</dependency>

2：安装mybatis-plus
<!-- 数据库驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<!-- mybatis-plus -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.3.1</version>
</dependency>

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ylm?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: root
    password: 123456
#其中url中，数据库名称？是否使用安全链接，字符集编码，是否使用解码，设置时区
#mysql数据库用的是gbk编码，而项目数据库用的是utf-8编码。这时候如果添加了useUnicode=true&characterEncoding=UTF-8
#存数据时：
#数据库在存放项目数据的时候会先用UTF-8格式将数据解码成字节码，然后再将解码后的字节码重新使用GBK编码存放到数据库中。
#取数据时：
#在从数据库中取数据的时候，数据库会先将数据库中的数据按GBK格式解码成字节码，然后再将解码后的字节码重新按UTF-8格式编码数据，最后再将数据返回给客户端。


@PathVariable和@RequestParam
@RequestParam 和 @PathVariable 注解是用于从request中接收请求的，两个都可以接收参数，关键点不同的是@RequestParam 是从request里面拿取值，而 @PathVariable 是从一个URI模板里面来填充
@RequestParam：

@RequestParam 和 @PathVariable 注解是用于从request中接收请求的，两个都可以接收参数，关键点不同的是@RequestParam 是从request里面拿取值，而 @PathVariable 是从一个URI模板里面来填充

@RequestParam
看下面一段代码：

http://localhost:8080/springmvc/hello/101?param1=10¶m2=20

根据上面的这个URL，你可以用这样的方式来进行获取

public String getDetails(
    @RequestParam(value="param1", required=true) String param1,
        @RequestParam(value="param2", required=false) String param2){
...
}

Param 支持下面四种参数

defaultValue 如果本次请求没有携带这个参数，或者参数为空，那么就会启用默认值
name 绑定本次参数的名称，要跟URL上面的一样
required 这个参数是不是必须的
value 跟name一样的作用，是name属性的一个别名

@PathVariable：

@PathVariable绑定URI模板变量值

@PathVariable是用来获得请求url中的动态参数的

@PathVariable用于将请求URL中的模板变量映射到功能处理方法的参数上。//配置url和方法的一个关系@RequestMapping("item/{itemId}")

/* @RequestMapping 来映射请求，也就是通过它来指定控制器可以处理哪些URL请求,类似于struts的action请求
* @responsebody表示该方法的返回结果直接写入HTTP response body中
*一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，加上@responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response *body中。
*比如异步获取json数据，加上@responsebody后，会直接返回json数据。*
*@Pathvariable注解绑定它传过来的值到方法的参数上
*用于将请求URL中的模板变量映射到功能处理方法的参数上，即取出uri模板中的变量作为参数
*/

这个注解能够识别URL里面的一个模板，我们看下面的一个URL
http://localhost:8080/springmvc/hello/101?param1=10&param2=20

上面的一个url你可以这样写：

@RequestMapping("/hello/{id}")
    public String getDetails(@PathVariable(value="id") String id,
    @RequestParam(value="param1", required=true) String param1,
    @RequestParam(value="param2", required=false) String param2){
.......
}

//@Transactional事务 当出错时，会回滚