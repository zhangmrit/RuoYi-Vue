package com.ruoyi.framework.tenant;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "tenant")
public class TenantProperties
{
    /**
     * 多租户字段名称
     */
    private String       column;

    /**
     * 多租户数据表
     */
    private List<String> ignores;

    /**
     * 需要过滤的sql
     */
    private List<String> sqlFilter;
}