<h1 align="center"> 轻松阅读 - API </h1>

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

light reading cloud（轻松阅读）是一款图书阅读类APP，基于 SpringCloud 生态开发的微服务实战项目，涉及 SpringCloud-Config、Eureka、OpenFeign、Hystrix、Jwt、SpringCloud-Gateway 等技术栈的应用。

项目的侧重点主要是基于实际业务场景使用微服务架构落地的思路，会采用图文的方式介绍每个服务或接口的原理以及为什么使用这种方式实现，希望会对想入门微服务的同学有所帮助。

客户端采用 Vue.js 、Vuetify  开发：[点击进入仓库](https://github.com/Zealon159/book-ms-ui)

### 演示

演示地址：[http://reading.zealon.cn/](http://reading.zealon.cn/)

部分截图：



### 架构图

客户端访问接口由统一流量入口 SpringCloud-Gateway 接收请求、响应结果，网关与微服务基于异步IO Netty通信，微服务获取配置文件启动后通过Eureka完成服务注册与发现，微服务之间的相互调用基于http协议的 FeignClient客户端。

核心架构图如下：

![](http://q94iswz37.bkt.clouddn.com/frameworks.jpg)

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
| 2    | reading-cloud-config       | 配置中心，存储每个微服务的配置           | -       |
| 3    | reading-cloud-eureka       | 注册中心，服务发现与注册                 | -       |
| 4    | reading-cloud-gateway      | 服务网关，流量入口、权限验证等           | -       |
| 5    | reading-cloud-book         | 图书中心，提供图书基础数据接口           | 1       |
| 6    | reading-cloud-account      | 账户中心，提供账户授权、用户服务等接口   | 1、5    |
| 7    | reading-cloud-homepage     | 精品页中心，提供App精品页接口            | 1、5、6 |
| 8    | reading-cloud-feign-client | Feign客户端，提供微服务的公用客户端      | 1       |

这样拆分的粒度比较适中，其中每个服务相对都比较独立。由于个人精力有限，只实现了最核心的业务：图书、精品页、账户、书架等服务。

从依赖中可以看出，除了common之外，图书中心被依赖的次数最多，由此可见图书中心是最基础的服务，为此需要对这类底层的服务分配更多的容器，具体的还需要根据 `DAU`、`QPS` 等综合衡量，决策更合适的数值。

## 快速开始



## 指南

工程模块主要划分为2个类型：**基础服务** 和 **业务服务**。

其中配置中心、注册中心、服务网关为基础服务，图书中心、账户中心、精品页中心为业务服务，这里会侧重说明业务部分。

### 公共模块 - reading-cloud-common

主要存放Pojo、Constant、工具类等公共资源，作为独立的Jar包供其他工程依赖使用。

相当于单体项目里的 common 包独立出来，实现同等的价值，这样不需要每个微服务项目冗余公共代码资源，需要注意只存放公共代码，从而得到更好的抽离和复用。

### 配置中心 - reading-cloud-config

从上面的架构图中我们可以得知，几乎所有的工程都要从配置中心获取配置信息。其目的是用来统一管理配置，配置中心可以在微服务等场景下极大地减轻配置管理的工作量，增强配置管理的服务能力。

> 单体项目的时候，我们把配置信息放到 `.yml` 或 `.properties` 文件中，随着项目走的，一个项目可能有几个配置文件。当请求量随着增大，项目可能要部署多个节点了，这时候维护起来会越来越麻烦，也容易出错。发布的工作降低了整体的工作效率，为了能够提升工作效率，配置中心应运而生了，我们可以将配置统一存放在配置中心来进行管理。

目前主流的配置中心有 Apollo、SpringCloud-Config、Nacos 等开源产品，每款配置中心都能满足统一管理配置的需求，SpringCloud 体系中自带的配置中心是 SpringCloud-Config，所以就地安排了，后面会升级到 Nacos，因为它除了可以做配置中心，还可以做服务注册发现，可以替代 Eureka 和 SpringCloud-Config 两个产品。

SpringCloud-Config 支持动态获取Git、SVN、本地的配置文件，本项目采用Git的方式，具体搭建过程可参考：

https://www.springcloud.cc/spring-cloud-config.html

### 注册中心 - reading-cloud-eureka

注册中心，是一个独立的服务组件，核心功能是服务治理，集中存储、监控、我们的服务信息。

工作过程简单来说，首先服务提供者启动时，向注册中心提供自己的服务信息，然后消费者服务要请求某个接口时，不是直接去请求具体的服务地址，而是在注册中心拉取得到要请求的服务地址，最后再通过这个地址、端口信息远程调用服务。大体过程如下图：

![](http://q94iswz37.bkt.clouddn.com/register.jpg)

当然服务注册与服务发现的过程并不仅仅只有注册和拉取这两个动作，还有一些其他相关的动作。如注册中心存储数据的缓存更新、提供者服务故障处理、消费者心跳检测等等。

> Netflix Eureka 是一款由 Netflix 开源的基于 REST 服务的注册中心，用于提供服务发现功能。Spring Cloud Eureka 是 Spring Cloud Netflix 微服务套件的一部分，基于 Netflix Eureka 进行了二次封装，主要负责完成微服务架构中的服务治理功能，能够非常方便的将服务注册到 Spring Cloud Eureka 中进行统一管理。 

Eureka 设计架构主要分为 Eureka Server 和 Eureka Client 两部分，Eureka Client 又分为 Applicaton Service 和 Application Client，Applicaton Service 就是服务提供者，Application Client 就是服务消费者。

所以我们的 reading-cloud-eureka 项目中，使用注解 `@EnableEurekaServer` 来启用 eureka 服务作为注册中心服务。而在其他子项目中，使用注解 `@EnableEurekaClient` 来启用 eureka 客户端，使用注册中心的服务。

具体搭建过程参考：https://www.springcloud.cc/spring-cloud-netflix.html

### 服务网关 - reading-cloud-gateway





### 图书中心 - reading-cloud-book

图书中心作为基础数据提供图书信息服务，另外就是提供图书详情接口、章节目录、章节阅读等接口了。

####数据表结构

PS：只列举了关键表和关键字段

1. 图书表（book）

2. 章节表（book_chapter）一对多关系。

![](http://q94iswz37.bkt.clouddn.com/book-center-table.png)

####接口服务

可以看到如下的几个接口，接口描述使用 swagger 实现。

![](http://q94iswz37.bkt.clouddn.com/book-center.jpg)

其中图书查询接口比较简单，看代码很轻易的就能明白，这里重点说明一下章节阅读接口 `book/chapter/readChapter`。

首先分析一下阅读操作的特征：

- 首次阅读从第一章开始，只有下一章，没有上一章操作
- 普通章节，可操作上一章、下一章
- 末尾章节，只有上一章，没有下一章

分析得出，阅读章节的数据结构几乎就是一个双向链表，所以接口可以采用这种模式来存储一本书的阅读数据。

> Q：为什么非要使用链表存储呢？阅读当前章节的时候同时查询上一章和下一章不是也可以吗？
>
> A：没错啊，利用当前章节计算上一章和下一章是可行的，但是这种方式没访问一章都需要进行上下章查询与计算，而通过链表这种方式，只需要第一次生成一次链表，后面每次在链表中读取即可，相比每次计算和一次计算，当然要选择后者啦，而且随着章节越多耗费的性能差距也就越大。

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

![](http://q94iswz37.bkt.clouddn.com/process-chapter-read.jpg)

其中，没有缓存时，会查询一次数据库，计算整个链表存到缓存，后面再请求时，直接redis的hash返回，不需要再计算前后章节了。

###精品页中心 - reading-cloud-homepage



####数据表设计



####接口服务



![](http://q94iswz37.bkt.clouddn.com/homepage.jpg)

### 账户中心 - reading-cloud-account

账户中心提供用户注册、登录验证、用户书架、喜欢看等服务。其中授权验证使用了jwt。

#### 数据表结构

1. 用户表(user)
2. 用户书架表(user_bookshelf)
3. 用户喜欢看表(user_like_see)

![](http://q94iswz37.bkt.clouddn.com/account-center-table.jpg)

#### 接口服务

其中用户服务接口复制登录认证与注册，用户纾解、喜欢看都是用户行为的接口，相比book略多一点。

![](http://q94iswz37.bkt.clouddn.com/account-center.jpg)

登录接口

### Feign客户端



## License

[MIT](https://github.com/Zealon159/book-ms-interface/blob/master/LICENSE)

Copyright (c) 2020 光彩盛年