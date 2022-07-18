package com.ruoyi.framework.tenant;

import com.alibaba.ttl.TransmittableThreadLocal;

import lombok.extern.slf4j.Slf4j;

/**
 * 租户holder
 */
@Slf4j
public class TenantContextHolder
{
    /**
     * 支持父子线程之间的数据传递
     */
    private static final ThreadLocal<String> CONTEXT = new TransmittableThreadLocal<>();

    public static void setTenantId(String tenant)
    {
        CONTEXT.set(tenant);
    }

    public static String getTenantId()
    {
        return CONTEXT.get();
    }

    public static void clear()
    {
        CONTEXT.remove();
    }
}