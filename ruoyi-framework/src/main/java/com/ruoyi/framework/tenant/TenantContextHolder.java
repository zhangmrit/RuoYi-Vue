package com.ruoyi.framework.tenant;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * 租户holder
 */
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