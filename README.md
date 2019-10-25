# ruoyi_cloud
springcloud版本的若依Layui版本的前端正常开发中

## 平台简介

本项目FORK自  [若依/RuoYi](https://gitee.com/y_project/RuoYi)
暂时花了几分钟时间把自己整合过的功能传上来：
###### springcloud
还是因为有人跃跃欲试，稍微改了下，cloud版本移步 [ruoyi-cloud](https://gitee.com/zhangmrit/ruoyi-cloud)
###### 集成jwt
应很多同学要求写了一个jwt的模块，详情ruoyi-app，若需要使用`shiro-jwt`，见[shiro-jwt分支](https://gitee.com/zhangmrit/RuoYi/tree/shiro-jwt/)
###### 多数据源切面
删了多数据源注解，改成根据方法名自动切换，默认主从分离（当然主从地址一样），当只有一个数据源的时候虽然会打印日志，实际主从还是同一个，膈应就自己改一下
###### 集成通用mapper
一开始想整合mybatis-plus，这玩意太重了，而且crud和本项目很多地方八字不合。mapper继承com.ruoyi.common.base.BaseMapper<T>后就可以策马奔腾啦，谁用谁知道
###### 控制台日志分等级彩色渲染和多环境修改
具体看logback-spring.xml和application.xml改动
###### 集成七牛云，阿里云，腾讯云OSS
- 先去七牛注册一下（推荐，10g免费空间）,演示系统设置了1天删除文件
- 详见sql/oss.sql,在上传页面配置好相关参数即可使用
- 阿里云配置问题：长度必须在 1-1023字节之间，而且不能包含回车、换行、以及xml1.0不支持的字符，同时也不能以“/”或者“\”开头。
- 集成富文本编辑器上传，列表中显示预览图，支持点击放大
###### 集成省市区三级联动
- 演示distpicker插件
- 数据库驱动方式
###### 静态资源动态url
- 实际生产中可能前后端分离，防止缓存css,js链接可以这么写,例：
```
<script th:src="${urls.getForLookupPath('/ruoyi/js/ry-ui.js')}"></script>
```
###### druid去除广告
- 替换doc文件中的jar即可
###### 增加分页栏跳页插件
- options中定义:`showPageGo:true`
- 参见演示系统中系统管理→文件管理
- 该功能已经pr通过
## 在线体验
> admin/admin123  

演示地址： http://ruoyi.zmrit.com
 
若依演示地址：http://ruoyi.vip  

文档地址：http://doc.ruoyi.vip


一直想做一款后台管理系统，看了很多优秀的开源项目但是发现没有合适的。于是利用空闲休息时间开始自己写了一套后台系统。如此有了若依。她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA。所有前端后台代码封装过后十分精简易上手，出错概率低。同时支持移动客户端访问。系统会陆续更新一些实用功能。

性别男，若依是给还没有出生女儿取的名字（寓意：你若不离不弃，我必生死相依）

> 阿里云服务器86元/年，双11冰点底价，错过再等1年 ：[点我进入](https://www.aliyun.com/1111/2019/group-buying-share?ptCode=79B1148C588D554F3E87264DB80E6C90647C88CF896EF535&userCode=brki8iof&share_source=copy_link)

> 如需单应用，请移步 [RuoYi-fast](https://gitee.com/y_project/RuoYi-fast)  `(保持同步更新)`，如需其他版本，请移步 [项目扩展](http://doc.ruoyi.vip/ruoyi/document/xmkz.html)  `(不定时更新)`

> 阿里云通用云产品1888优惠券 ：[点我领取](https://promotion.aliyun.com/ntms/yunparter/invite.html?userCode=brki8iof)&nbsp;&nbsp;&nbsp;&nbsp;腾讯云通用云产品2860优惠券 ：[点我领取](https://cloud.tencent.com/redirect.php?redirect=1025&cps_key=198c8df2ed259157187173bc7f4f32fd&from=console)&nbsp;&nbsp;`(仅限新用户)`

> 阿里云Hi拼购 限量爆款 低至199元/年 [点我进入](https://www.aliyun.com/acts/hi-group-buying?userCode=brki8iof)&nbsp;&nbsp;`(仅限新用户)`

## 内置功能

1.  用户管理：用户是系统操作者，该功能主要完成系统用户配置。
2.  部门管理：配置系统组织机构（公司、部门、小组），树结构展现支持数据权限。
3.  岗位管理：配置系统用户所属担任职务。
4.  菜单管理：配置系统菜单，操作权限，按钮权限标识等。
5.  角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。
6.  字典管理：对系统中经常使用的一些较为固定的数据进行维护。
7.  参数管理：对系统动态配置常用参数。
8.  通知公告：系统通知公告信息发布维护。
9.  操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。
10. 登录日志：系统登录日志记录查询包含登录异常。
11. 在线用户：当前系统中活跃用户状态监控。
12. 定时任务：在线（添加、修改、删除)任务调度包含执行结果日志。
13. 代码生成：前后端代码的生成（java、html、xml、sql）支持CRUD下载 。
14. 系统接口：根据业务代码自动生成相关的api接口文档。
15. 服务监控：监视当前系统CPU、内存、磁盘、堆栈等相关信息。
16. 在线构建器：拖动表单元素生成相应的HTML代码。
17. 连接池监视：监视当前系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。
## 在线体验
> admin/admin123  
> 陆陆续续收到一些打赏，为了更好的体验已用于演示服务器升级。谢谢各位小伙伴。

演示地址：http://ruoyi.vip  

文档地址：http://doc.ruoyi.vip

## 演示图

<table>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/25b5e333768d013d45a990c152dbe4d9d6e.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/e29fd81b2d43b517f99535564af41f9d1d5.jpg"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/629f1510fb6205f773c8c284863406b694f.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/9124eda87df0e72427cd63f458b813e3363.jpg"/></td>
    </tr>
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/438c59467afd0097cfbe9c89db932661687.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/72a015041db6843aca7f7b273688cb346f8.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/ecb5f1c9929f1933f733f796749b2df73d9.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/e4283d500eb10e8dd8701e7742f7facb065.jpg"/></td>
    </tr>	 
    <tr>
        <td><img src="https://oscimg.oschina.net/oscnet/2531dbf419a1b114e1177f8d2a120b8a9c3.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/8b740a42dddc1e5a8a150d97c5060df258b.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/00e642dc3515919b3760968cc496a12a849.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/f72d28a3e60413a4e1b5c7c2f45f962fd65.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/19222e495869a2a99fc31c5d2bd4539e1e7.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/264d25176f4e22b4b38e95fe6ce73775299.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/d85fbb59be27fb33f68bdbb6e8bc967c97b.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/bb902d2c54bad02a052e9a05e5f22a93df1.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/30cda883bb9a7f74f1454314e64f949942d.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/deebaaa8d6b14a419ed5911f49e3f222a6f.jpg"/></td>
    </tr>
	<tr>
        <td><img src="https://oscimg.oschina.net/oscnet/bed2b98a44e7ae820c2885329e711965c28.jpg"/></td>
        <td><img src="https://oscimg.oschina.net/oscnet/5f3d39a141f21f81b90536f391b8408f1fa.jpg"/></td>
    </tr>
</table>


## 若依交流群

QQ群： [![加入QQ群](https://img.shields.io/badge/已满-1389287-blue.svg)](https://jq.qq.com/?_wv=1027&k=5HBAaYN)  [![加入QQ群](https://img.shields.io/badge/已满-1679294-blue.svg)](https://jq.qq.com/?_wv=1027&k=5cHeRVW)  [![加入QQ群](https://img.shields.io/badge/已满-1529866-blue.svg)](https://jq.qq.com/?_wv=1027&k=53R0L5Z)  [![加入QQ群](https://img.shields.io/badge/已满-1772718-blue.svg)](https://jq.qq.com/?_wv=1027&k=5g75dCU)  [![加入QQ群](https://img.shields.io/badge/已满-1366522-blue.svg)](https://jq.qq.com/?_wv=1027&k=58cPoHA)  [![加入QQ群](https://img.shields.io/badge/已满-1382251-blue.svg)](https://jq.qq.com/?_wv=1027&k=5Ofd4Pb)  [![加入QQ群](https://img.shields.io/badge/已满-1145125-blue.svg)](https://jq.qq.com/?_wv=1027&k=5yugASz)  [![加入QQ群](https://img.shields.io/badge/86752435-blue.svg)](https://jq.qq.com/?_wv=1027&k=5Rf3d2P)  点击按钮入群。
