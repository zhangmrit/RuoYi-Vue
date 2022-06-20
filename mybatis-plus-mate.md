# mybatis-plus mate替代方案

[TOC]

## 前请说明
不是说mp-mate不好,首先这个是企业级收费框架，日常能用到的功能不多,即使买了授权也是没有源码的，对于学习没什么帮助，下面收录的文字可以帮你大概的了解这些过程是怎么实现的

## 数据敏感词过滤
主要是一些文章的敏感词检测，这不应该封装到数据库来
需要检测的地方调用方法就行了


## 数据范围（数据权限）
若依自己的 @datascope


## 表结构自动维护

个人感觉用不上

## 字段数据绑定（字典回写）
[mybatis自定义插件处理数据字典](https://blog.csdn.net/qq_25863973/article/details/106183181)

个人想法也不应该是mybatis层处理的东西


## 字段加密解密
[使用mybatis的BaseTypeHandler来给敏感字段进行AES加密](https://www.cnblogs.com/java-spring/p/14676670.html)

[动态代理Mybatis Mapper类](https://blog.csdn.net/why_still_confused/article/details/113060605)

[Spring Boot Mybatis 优雅解决敏感信息加解密问题](https://ld246.com/article/1587991687126)

## 字段脱敏

[interceptor方式](https://blog.csdn.net/lemon_csdn/article/details/121627878)

[Mybatis拦截器实现数据脱敏](https://blog.csdn.net/chengbinbbs/article/details/105879611)

[mybatis-cipher](https://gitee.com/Jerry.hu/mybatis-cipher)

[敏感数据加解密以及数据脱敏mybatis插件](https://github.com/chenhaiyangs/mybatis-encrypt-plugin)

提供一些思路,跟加密其实很像

## 多数据源分库分表（读写分离）
[shardingsphere](https://gitee.com/zhangmrit/RuoYi-Vue/tree/shardingsphere/)