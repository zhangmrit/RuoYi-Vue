package com.ruoyi.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.ruoyi.common.extension.MpSqlInjector;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
public class MybatisPlusConfig
{
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor()
    {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // interceptor.addInnerInterceptor(tenantLineInnerInterceptor());
        // 如果用了分页插件注意先 add TenantLineInnerInterceptor 再 add
        // PaginationInnerInterceptor
        // 用了分页插件必须设置 MybatisConfiguration#useDeprecatedExecutor = false
        // 分页插件
        // interceptor.addInnerInterceptor(paginationInnerInterceptor());
        // 乐观锁插件
        interceptor.addInnerInterceptor(optimisticLockerInnerInterceptor());
        // 阻断插件
        interceptor.addInnerInterceptor(blockAttackInnerInterceptor());
        return interceptor;
    }
    // @SuppressWarnings("deprecation")
    // @Bean
    // public ConfigurationCustomizer configurationCustomizer()
    // {
    // return configuration ->
    // configuration.setUseDeprecatedExecutor(Boolean.FALSE);
    // }

    @Bean
    public MpSqlInjector mpSqlInjector()
    {
        return new MpSqlInjector();
    }

    /**
     * 分页插件，自动识别数据库类型
     * https://baomidou.com/guide/interceptor-pagination.html
     */
    public PaginationInnerInterceptor paginationInnerInterceptor()
    {
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        // 设置数据库类型为mysql
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInnerInterceptor.setMaxLimit(-1L);
        return paginationInnerInterceptor;
    }

    /**
     * 乐观锁插件
     * https://baomidou.com/guide/interceptor-optimistic-locker.html
     */
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor()
    {
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     * 如果是对全表的删除或更新操作，就会终止该操作
     * https://baomidou.com/guide/interceptor-block-attack.html
     */
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor()
    {
        return new BlockAttackInnerInterceptor();
    }
    /**
     * sql性能规范插件(垃圾SQL拦截)
     * 如有需要可以启用
     */
    // public IllegalSQLInnerInterceptor illegalSQLInnerInterceptor() {
    // return new IllegalSQLInnerInterceptor();
    // }
    /**
     * 自定义主键策略
     * https://baomidou.com/guide/id-generator.html
     */
    // @Bean
    // public IdentifierGenerator idGenerator() {
    // return new CustomIdGenerator();
    // }
    /**
     * 元对象字段填充控制器
     * https://baomidou.com/guide/auto-fill-metainfo.html
     */
    // @Bean
    // public MetaObjectHandler metaObjectHandler() {
    // return new MyMetaObjectHandler();
    // }
    /**
     * sql注入器配置
     * https://baomidou.com/guide/sql-injector.html
     */
    // @Bean
    // public ISqlInjector sqlInjector() {
    // return new DefaultSqlInjector();
    // }
    /**
     * TenantLineInnerInterceptor 多租户插件
     * 本项目已经实现完整的多租户功能，但应用不广泛，基本框架中删除
     */
    // @Bean
    // public TenantLineInnerInterceptor tenantLineInnerInterceptor()
    // {
    // return new TenantLineInnerInterceptor(new TenantLineHandler()
    // {
    // /**
    // * 获取租户ID
    // * @return
    // */
    // @Override
    // public Expression getTenantId()
    // {
    // String tenant = TenantContextHolder.getTenantId();
    // if (tenant != null)
    // {
    // return new StringValue(TenantContextHolder.getTenantId());
    // }
    // return new StringValue("99999");
    // }
    //
    // /**
    // * 获取多租户的字段名
    // * @return String
    // */
    // @Override
    // public String getTenantIdColumn()
    // {
    // return tenantProperties.getColumn();
    // }
    //
    // /**
    // * 过滤不需要根据租户隔离的表
    // * 这是 default 方法,默认返回 false 表示所有表都需要拼多租户条件
    // * @param tableName 表名
    // */
    // @Override
    // public boolean ignoreTable(String tableName)
    // {
    // return tenantProperties.getIgnores().stream().anyMatch((t) ->
    // t.equalsIgnoreCase(tableName));
    // }
    // });
    // }
}