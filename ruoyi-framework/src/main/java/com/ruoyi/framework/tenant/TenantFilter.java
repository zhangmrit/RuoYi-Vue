package com.ruoyi.framework.tenant;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.SecurityUtils;

import cn.hutool.core.util.StrUtil;

/**
 * 租户过滤器
 */
@ConditionalOnClass(Filter.class)
public class TenantFilter extends OncePerRequestFilter
{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException
    {
        try
        {
            String tenantId = null;
            Object user = SecurityUtils.getAuthentication().getPrincipal();
            if ("anonymousUser".equals(user))
            {
                // 优先获取请求参数中的tenantId值
                tenantId = request.getParameter(Constants.TENAN_ID);
                if (StrUtil.isEmpty(tenantId))
                {
                    tenantId = request.getHeader(Constants.TENANT_HEADER);
                }
            }
            else
            {
                tenantId = SecurityUtils.getTenantId();
            }
            // 保存租户id
            if (StrUtil.isNotEmpty(tenantId))
            {
                TenantContextHolder.setTenantId(tenantId);
            }
            filterChain.doFilter(request, response);
        }
        finally
        {
            TenantContextHolder.clear();
        }
    }
}