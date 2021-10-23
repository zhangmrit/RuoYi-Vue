package com.ruoyi.system.mapper;

import java.util.List;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.ruoyi.common.core.mapper.MpBaseMapper;
import com.ruoyi.system.domain.SysTenant;

/**
 * 租户Mapper接口
 * 
 * @author ruoyi
 * @date 2021-02-04
 */
@InterceptorIgnore(tenantLine = "true")
public interface SysTenantMapper extends MpBaseMapper<SysTenant>
{
    /**
     * 查询租户
     * 
     * @param id 租户ID
     * @return 租户
     */
    public SysTenant selectSysTenantById(Long id);

    /**
     * 查询租户列表
     * 
     * @param sysTenant 租户
     * @return 租户集合
     */
    public List<SysTenant> selectSysTenantList(SysTenant sysTenant);

    /**
     * 新增租户
     * 
     * @param sysTenant 租户
     * @return 结果
     */
    public int insertSysTenant(SysTenant sysTenant);

    /**
     * 修改租户
     * 
     * @param sysTenant 租户
     * @return 结果
     */
    public int updateSysTenant(SysTenant sysTenant);

    /**
     * 删除租户
     * 
     * @param id 租户ID
     * @return 结果
     */
    public int deleteSysTenantById(Long id);

    /**
     * 批量删除租户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysTenantByIds(Long[] ids);
}
