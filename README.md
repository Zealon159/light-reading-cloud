<h1 align="center"> 轻松阅读 - API - 2.0 </h1>

<p align="center">
  <a href="https://github.com/spring-cloud">
    <img src="https://img.shields.io/badge/spring--cloud-2.1.5-blue" alt="spring-cloud">
  </a>
  <a href="https://github.com/Netflix/Hystrix">
    <img src="https://img.shields.io/badge/hystrix-1.5.18-blue" alt="hystrix">
  </a>
  <a href="https://github.com/jwtk/jjwt">
    <img src="https://img.shields.io/badge/jwt-0.9.1-blue" alt="jwt">
  </a>
  <a href="https://github.com/Zealon159/light-reading-cloud/blob/master/LICENSE">
    <img src="https://img.shields.io/badge/License-MIT-yellow" alt="license">
  </a>
</p>


## 项目介绍

light reading cloud（轻松阅读）是一款图书阅读类APP，基于 SpringCloud 生态开发的微服务实践项目，涉及 SpringCloud-Gateway、Nacos、Hystrix、OpenFeign、Jwt、ElasticSearch 等技术栈的应用。

项目的侧重点主要是基于实际业务场景使用微服务架构落地的思路，会采用图文的方式介绍每个服务或接口的原理以及为什么使用这种方式实现，希望会对想入门微服务的同学有所帮助。

客户端采用 Vue.js 、Vuetify  开发：[点击进入仓库](https://github.com/Zealon159/light-reading-cloud-client)

### 版本

 `2.0` 版本，主要更新了 SpringCloud Alibaba 的 Nacos 组件，替代了 SpringCloud Config 以及 Eureka 

 `1.0` 版本，采用 SpringCloud Config、Eureka 为配置中心、注册中心。获取此版本，请查看 `reading-1.0` 分支的说明，并 `Checkout` 此分支 

### 演示

演示地址：[http://reading-cloud.zealon.cn/#/index](http://reading-cloud.zealon.cn/#/index) ，`手机访问效果佳 ^_^`

数据库地址：`47.104.241.41` ，端口 `3306`

数据库账户：`hello_developer` ，密码：`Bestyou2020.com`

Nacos地址：`http://reading-cloud.zealon.cn:8848/nacos/`，账户密码同上

由于云服务器单机部署，可能内存不足导致Nacos宕机而看不见配置文件，这里专门把配置文件放置在 `bootstrap-config` 目录下，使用静态配置文件方式启动项目（手动更换各个项目对应的配置文件）。

或者切换到 `reading-1.0` 分支，该分支使用了eureka 实现的注册中心。

部分截图：

![](http://reading.zealon.cn/index_1.jpg)

### 架构图

客户端访问接口由统一流量入口 SpringCloud-Gateway 接收请求、响应结果，网关与微服务基于异步IO Netty通信，微服务获取配置文件启动后通过 Nacos 完成服务注册与发现，微服务之间的相互调用基于http协议的 FeignClient客户端。

核心架构图如下：

![](http://reading.zealon.cn/framework.png)

### 系统模块

微服务拆分策略：

- 业务先行，理清楚业务边界和依赖
- 先有独立的模块，后有分布式服务
- 模块之间的依赖关系要清晰、参数简单、耦合要少
- 最重要的是需求，根据需求判断具体价值，再按价值建立设计原则，最后按照设计原则来选择落地的技术方案，而不是根据技术来套业务需求

> 阅读APP，如果你有阅读的习惯，相信对此类产品并不陌生，其核心功能是阅读，当然在阅读之前需要发现想要读的图书，这就需要精品(榜单)页、排行、分类、搜索等功能的支撑，而用户数据主要分为账户、会员、书架、积分、评论等功能。
>
> 所以根据业务场景可进行最基础的拆分服务：图书服务、精品页服务、排行榜服务、搜索服务、账户服务、会员服务、消息服务、积分服务、活动服务、评论服务、支付服务等等（实际上会有比这更多的功能哈）

本项目进行以下拆分：

| No   | 工程模块                   | 说明                                     | 依赖    |
| ---- | -------------------------- | ---------------------------------------- | ------- |
| 1    | reading-cloud-common       | 公共模块，存放通用的POJO、工具类等文件。 | -       |
| 2    | reading-cloud-gateway      | 服务网关，流量入口、权限验证等           | -       |
| 3    | reading-cloud-book         | 图书中心，提供图书基础数据接口           | 1       |
| 4    | reading-cloud-account      | 账户中心，提供账户授权、用户服务等接口   | 1、3    |
| 5    | reading-cloud-homepage     | 精品页中心，提供App精品页接口            | 1、3、4 |
| 6    | reading-cloud-feign-client | Feign客户端，提供微服务的公用客户端      | 1       |

这样拆分的粒度比较适中，其中每个服务相对都比较独立。由于个人精力有限，只实现了最核心的业务：图书、精品页、账户、书架等服务。

从依赖中可以看出，除了common之外，图书中心被依赖的次数最多，由此可见图书中心是最基础的服务，为此需要对这类底层的服务分配更多的容器，具体的还需要根据 `DAU`、`QPS` 等综合衡量，决策更合适的数值，是否要进一步拆分微服务等等。

## 快速开始

### step1 - 创建数据库

导入数据库脚本，分别创建数据库 `reading_cloud_resource`、`reading_cloud_account`，然后导入建表脚本。

需要示例数据的话，可以到阿里云数据库导出数据哈，在上面有数据库连接信息。

### step2 - 配置文件

由于我的服务器内存不够用了，就没搭建配置中心，可以直接修改每个工程的 `bootstrap.yml` 文件，更新数据库信息、redis配置信息等。

### step3 - 启动程序

首先启动注册中心，然后依次启动图书中心、账户中心、精品页中心、服务网关，可以在配置文件中自行修改端口哈。

## 指南

工程模块主要划分为2个类型：**基础服务** 和 **业务服务**。

其中配置中心、注册中心、服务网关为基础服务，图书中心、账户中心、精品页中心为业务服务，这里会侧重说明业务部分。

### 公共模块 - reading-cloud-common

主要存放Pojo、Constant、工具类等公共资源，作为独立的Jar包供其他工程依赖使用。

相当于单体项目里的 common 包独立出来，实现同等的价值，这样不需要每个微服务项目冗余公共代码资源，需要注意只存放公共代码，从而得到更好的抽离和复用。

### 配置中心/注册中心 - Alibaba-Nacos

#### 配置中心

从上面的架构图中我们可以得知，几乎所有的工程都要从配置中心获取配置信息。其目的是用来统一管理配置，配置中心可以在微服务等场景下极大地减轻配置管理的工作量，增强配置管理的服务能力。

> 单体项目的时候，我们把配置信息放到 `.yml` 或 `.properties` 文件中，随着项目走的，一个项目可能有几个配置文件。当请求量随着增大，项目可能要部署多个节点了，这时候维护起来会越来越麻烦，也容易出错。发布的工作降低了整体的工作效率，为了能够提升工作效率，配置中心应运而生了，我们可以将配置统一存放在配置中心来进行管理。

目前主流的配置中心有 Apollo、SpringCloud-Config、Nacos 等开源产品，每款配置中心都能满足统一管理配置的需求，本项目的1.0版本中使用 SpringCloud-Config 作为配置中心、Eureka为注册中心，2.0使用了 Nacos，因为它除了可以做配置中心，还可以做服务注册发现，替代了 Eureka 和 SpringCloud-Config 两个产品。

#### 注册中心

注册中心，是一个独立的服务组件，核心功能是服务治理，集中存储、监控、我们的服务信息。

工作过程简单来说，首先服务提供者启动时，向注册中心提供自己的服务信息，然后消费者服务要请求某个接口时，不是直接去请求具体的服务地址，而是在注册中心拉取得到要请求的服务地址，最后再通过这个地址、端口信息远程调用服务。大体过程如下图：

![](http://reading.zealon.cn/register.jpg)

当然服务注册与服务发现的过程并不仅仅只有注册和拉取这两个动作，还有一些其他相关的动作。如注册中心存储数据的缓存更新、提供者服务故障处理、消费者心跳检测等等。

### 服务网关 - reading-cloud-gateway

API 网关是对外提供服务的一个入口，并且隐藏了内部架构的实现，是微服务架构中必不可少的一个组件。API 网关可以为我们管理大量的 API 接口，负责对接协议适配、安全认证、路由转发、流量限制、日志监控、防止爬虫、等功能。

主流的开源网关有比较早的 Zuul 以及 SpringCloud 自己研发了一个全新的网关 Spring Cloud Gateway。由于 Zuul1 基于 Servlet 构建，使用的是阻塞的 IO，性能并不是很理想。Spring Cloud Gateway 则基于 Spring 5、Spring boot 2 和 Reactor 构建，使用 Netty 作为运行时环境，比较完美的支持异步非阻塞编程。

<center>没使用网关的情况</center>

![](http://reading.zealon.cn/gateway-01.jpg)

<center>使用网关后</center>

![](http://reading.zealon.cn/gateway-02.jpg)

我想，没有网关和使用网关的区别，看见客户端的表情你就明白了其中的奥义了吧（无论服务端多么复杂...）。

项目采用 SpringCloud Gateway 作为网关实现，主要实现了统一认证、动态路由。

SpringCloud Gateway 两大核心，一个是Predicate，路由匹配，一个是Filter，过滤器。

路由匹配的配置方式有 Fluent API 和 yml 两种方式，这里采用 yml 方式。具体见 reading-cloud-gateway 工程里的配置文件。

SpringCloud Gateway 有全局过滤器和局部过滤器之分，对应的接口为 GatewayFilter 和 GlobalFilter。我们统一认证的实现方式是自定义实现全局过滤器，在过滤器里面可以处理白名单放行、认证校验、动态处理请求参数等。位置：`cn.zealon.readingcloud.gateway.filter.AuthFilter`

认证校验过程参考 `账户中心 - reading-cloud-account` 的说明文档，在最下边。

其白名单配置在Nacos中，可通过动态配置进行更新。

### 图书中心 - reading-cloud-book

图书中心作为基础数据提供图书信息服务，另外就是提供图书详情接口、章节目录、章节阅读等接口了。

#### 数据表结构

PS：只列举了关键表和关键字段

1. 图书表（book）

2. 章节表（book_chapter）一对多关系。

![](http://reading.zealon.cn/book-center-table.png)

#### 接口服务

可以看到如下的几个接口，接口描述使用 swagger 实现。

![](http://reading.zealon.cn/book-center.jpg)

其中图书查询接口比较简单，看代码很轻易的就能明白，这里重点说明一下章节阅读接口 `book/chapter/readChapter`。

首先分析一下阅读操作的特征：

- 首次阅读从第一章开始，只有下一章，没有上一章操作
- 普通章节，可操作上一章、下一章
- 末尾章节，只有上一章，没有下一章

分析得出，阅读章节的数据结构几乎就是一个双向链表，所以接口可以采用这种模式来存储一本书的阅读数据。

> Q：为什么非要使用链表存储呢？阅读当前章节的时候同时查询上一章和下一章不是也可以吗？
>
> A：没错啊，利用当前章节计算上一章和下一章是可行的，但是这种方式每访问一章都需要进行上下章查询与计算，而通过链表这种方式，只需要第一次生成一次链表，后面每次在链表中读取即可，相比每次计算和一次计算，当然要选择后者啦，而且随着章节越多耗费的性能差距也就越大。

按着这个思路，接下来就是要设计具体数据了。我们看一下下边的数据结构，key 代表当前章节ID，value代表上下章关系数据，都有一个 pre 和 next 指向前驱章节和后继章节，这样当请求任意章节时，通过传入的章节ID就直接获得了前后章节信息了。

```
[
    {
        "key":"519",
        "value":{
            "id":529,
            "name":"第一章 装B的乞丐",
            "pre":null,
            "next":[
                {
                    "id":530,
                    "name":"第二章 资格"
                }
            ]
        }
    },
    {
        "key":"530",
        "value":{
            "id":530,
            "name":"第二章 资格",
            "pre":[
                {
                    "id":529,
                    "name":"第一章 装B的乞丐"
                }
            ],
            "next":[
                {
                    "id":531,
                    "name":"第三章 开始修炼清心诀"
                }
            ]
        }
    },
    {
        "key":"530",
        "value":{
            "id":531,
            "name":"第三章 开始修炼清心诀",
            "pre":[
                {
                    "id":530,
                    "name":"第二章 资格"
                }
            ],
            "next":[
                {
                    "id":532,
                    "name":"第四章 暴打恶霸"
                }
            ]
        }
    }
]
```

设计好了数据结构，想想Redis哪些类型能存储我们的章节数据呢，String自然是不行的了，Hash 貌似可行哎，可以通过 K / V 的形式存储，key即我们的章节ID，value即我们的链表内容，获取的时候只需要提供 key 即可，时间复杂度 O(1)，不错的赶脚吧。好了，那就实现它吧~

数据结构类是 `BookPreviousAndNextChapterNode` ，实现函数是 `BookChapterServiceImpl.getChapterNodeData`，那么有了章节基础数据了，剩下的就是完成整个接口的设计了。接口响应的结果数据除了前后章信息之外，就是当前章节内容了，而前后章的内容万万不能返回的，浪费资源啊。

大致流程图（蓝色环节只在没有缓存时执行一次）：

![](http://reading.zealon.cn/process-chapter-read.jpg)

其中，没有缓存时，会查询一次数据库，计算整个链表存到缓存，后面再请求时，直接redis的hash返回，不需要再计算前后章节了。

### 精品页中心 - reading-cloud-homepage

精品页主要提供app首页数据接口，换一换、图书列表接口。所以依赖于图书中心和账户中心。

#### 数据表设计

1. 精品页配置表(index_page_config)

   这个表为精品页推荐、男生、女生的配置总表，根据page_type存储对应的banner或booklist

   page_type=1 即书单，page_type=2 则Banner

2. Banner轮播表(index_banner)

3. Banner轮播明细表(index_banner_item)

4. 书单配置表(index_booklist)

5. 书单配置明细表(index_booklist_item)

![](http://reading.zealon.cn/homepage-db.jpg)

表详细说明可见数据库SQL建表后的注释。

#### 接口服务

主页的接口主要是读为主，就那么3个，是不是感觉挺简单的哈。

![](http://reading.zealon.cn/homepage.jpg)

这里主要说明下精品页接口，首先看下精品页的需求：

- 按配置有序加载对应的栏目(也就是Banner或书单)
- 书单可按类型加载不同的样式
- 书单可按更多配置项呈现换一换或更多或无

所以分析的大致接口逻辑图如下

![](http://reading.zealon.cn/index-process.jpg)

有了这个结构图，开发功能就清晰多了。

其中获取图书部分内部还有一些细节，随机获取时，每次随机得到的book不能重复，本次随机结果不能与客户端内的图书重复，如果配置数量不够随机的话还不能进行随机等等。想了解细节见代码：`cn.zealon.readingcloud.homepage.service.impl.IndexPageConfigServiceImpl.java` 中 `getIndexPageByType` 函数。

关于获取图书信息，是通过Feign客户端实现的，也就是我们说的微服务之间远程调用，下面是请求链调用的过程

![](http://reading.zealon.cn/index-seq.jpg)

其中精品页服务内部逻辑也就是我们的流程图部分，而调用链的过程使用时序图更为清晰。

:star: 看蓝色文字调用，这两处即我们的微服务之间的调用了，使用的是FeignClient，注意这里的调用返回结果，如果不是要求100%的实时性，一定要加上缓存，不用每次都去远程调用而减少服务提供者的压力。

:star: 是否注意到了，流程图更适合用于业务逻辑；而时序图适合用于调用链，通常每个节点代表了一个端点（即独立的服务或组件）​ 

####  ---------------------- 2020-06-01 增加搜索接口 ----------------------

搜索服务基于ElasticSearch搜索引擎实现，采用版本6.3.1，搜索客户端使用Jest，实际上搜索功能需要独立出来一个微服务工程，必定用户找书是一个很独立的、常用的功能，搜索的背后还要处理很多用户埋点数据，因此接口请求量会比较多。

这里放到了精品页工程中，主要是服务器没有更多内存空间来启动独立的Java进程了 ，ε=(´ο｀*)))唉。。。

搜索功能的实现，主要根据搜索需求，然后利用合适的ESAPI来实现，这里就不多说了。搜索数据的同步，目前我是手动执行存储到ES索引库里的，一般情况下，有两种同步手段：

1. 定时任务同步更新
2. 基于MQ准实时更新

**第一种定时任务同步**

这种方式相对来说更传统一些，可以写一个增量同步的脚本，然后使用CRON表达式指定执行间隔，来处理数据同步到ES索引库。缺点一是数据更新不及时，而是定时任务执行中，若无数据则会每次运行会浪费CPU资源。

所以如果要实时性比较高的话，最好采用第二种方式利用MQ处理。

**第二种基于MQ更新**

MQ（MessageQueue）即消息队列，多用于解耦、削峰、异步处理等场景。主流的开源项目有ActiveMQ、RabbitMQ、RocketMQ、Kafka等等，这里例举采用RabbitMQ来实现。

首先看一下基于MQ的同步过程：

![](http://reading.zealon.cn/MQ.jpg)

基于MQ的点对点通信，完成整个同步过程，即运营同学对图书进行了写操作，这时候将数据发送至指定的队列（RabbitMQ实际上是绑定交换器，交换器在绑定队列），然后消费者服务监听这个队列，若有数据及时消费处理了（也就是将数据同步到ES索引库中哈），这样就实现了准时更新了。

### 账户中心 - reading-cloud-account

账户中心提供用户注册、登录验证、用户书架、喜欢看等服务。其中授权验证使用了jwt。

#### 数据表结构

1. 用户表(user)
2. 用户书架表(user_bookshelf)
3. 用户喜欢看表(user_like_see)

![](http://reading.zealon.cn/account-center-table.jpg)

#### 接口服务

其中用户服务接口复制登录认证与注册，用户书架、喜欢看都是用户行为的接口

![](http://reading.zealon.cn/account-center.jpg)

**安全认证**

常用的认证方式主要有三种：Session、HTTP Basic Authentication 和 Token。

- session 是认证中最常用的一种方式，也是最简单的。用户登录后将信息存储在后端，客户端则通过 Cookie 中的 SessionId 来标识对应的用户。
- HTTP Basic Authentication 也就是 HTTP 基本认证，它是 HTTP 1.0 提出的一种认证机制。HTTP 基本认证的原理是客户端在请求时会在请求头中增加 Authorization，Authorization 是用户名和密码用 Base64 加密后的内容。服务端获取 Authorization Header 中的用户名与密码进行验证。
- Token 中会存储用户的信息，然后通过加密算法进行加密，只有服务端才能解密，服务端拿到 Token 后进行解密获取用户信息。

相比之下，Token更适用于微服务的安全认证。所以采用了基于Token实现的 JWT作为本项目的安全认证规范。

JWT（JSON Web Token）是一个非常轻巧的规范。这个规范允许我们使用JWT在用户和服务器之间传递安全可靠的信息。在HTTP通信过程中，进行身份认证。

比如在用户登录时，基本思路就是用户提供用户名和密码给认证服务器，服务器验证用户提交信息的合法性；如果验证成功，会产生并返回一个 Token，客户端将Token保存起来。

![](http://reading.zealon.cn/jwt-1.jpg)

再次请求服务端时，一般会将 Token 放入请求头中进行传递。当请求到达网关后，会在网关中对 Token 进行校验，如果校验成功，则将该请求转发到后端的服务中，在转发时会将 Token 解析出的用户信息也一并带过去，这样在后端的服务中就不用再解析一遍 Token 获取的用户信息，这个操作统一在网关进行的。如果校验失败，那么就直接返回对应的结果给客户端，不会将请求进行转发。

![](http://reading.zealon.cn/jwt-2.jpg)

:star: ​我们知道，网关是唯一入口，所有一般情况下，微服务之间的请求，就不需要再进行认证了。

:star: 有一些服务是不需要认证的，这时候，我们可以在网关中加白名单进行处理。

:star: 我们知道，jwt认证的过程主要是加密​，然后结果和Token匹配，而加密运算会耗费CPU资源，如果请求量比较大，可以将Token存储缓存，缓存失败再进行实时认证，可以大幅度的提升网关服务器的CPU性能。

## 附录

附录1.在线UML编辑工具：https://app.diagrams.net/

附录2.在线数据表关系编辑工具：https://dbdiagram.io/

## License

[MIT](https://github.com/Zealon159/book-ms-interface/blob/master/LICENSE)

Copyright (c) 2020 光彩盛年