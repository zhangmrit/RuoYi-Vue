package com.ruoyi.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.domain.SysTenant;
import com.ruoyi.system.mapper.SysTenantMapper;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysTenantService;
import com.ruoyi.system.service.ISysUserService;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 租户Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-04
 */
@Service
public class SysTenantServiceImpl implements ISysTenantService
{
    @Autowired
    private SysTenantMapper   sysTenantMapper;

    @Autowired
    private ISysConfigService sysConfigService;

    @Autowired
    private ISysUserService   sysUserService;

    @Autowired
    private ISysDeptService   sysDeptService;

    /**
     * 查询租户
     * 
     * @param id 租户ID
     * @return 租户
     */
    @Override
    public SysTenant selectSysTenantById(Long id)
    {
        return sysTenantMapper.selectSysTenantById(id);
    }

    @Override
    public SysTenant selectSysTenantByTenantId(String tenantId)
    {
        LambdaQueryWrapper<SysTenant> query = new LambdaQueryWrapper<>();
        query.eq(SysTenant::getTenantId, tenantId);
        return sysTenantMapper.selectOne(query);
    }

    /**
     * 查询租户列表
     * 
     * @param sysTenant 租户
     * @return 租户
     */
    @Override
    public List<SysTenant> selectSysTenantList(SysTenant sysTenant)
    {
        return sysTenantMapper.selectSysTenantList(sysTenant);
    }

    /**
     * 新增租户
     * 
     * @param sysTenant 租户
     * @return 结果
     */
    @Override
    @Transactional
    public int insertSysTenant(SysTenant sysTenant)
    {
        if (StrUtil.isBlank(sysTenant.getTenantId()))
        {
            sysTenant.setTenantId(RandomUtil.randomNumbers(6));
        }
        sysTenant.setCreateTime(DateUtils.getNowDate());
        // 1.创建租户
        int index = sysTenantMapper.insertSysTenant(sysTenant);
        // 2.创建部门
        SysDept dept = new SysDept();
        dept.setTenantId(sysTenant.getTenantId());
        dept.setParentId(0L);
        dept.setAncestors("0");
        dept.setDeptName(sysTenant.getTenantName());
        dept.setOrderNum(0);
        dept.setCreateBy("system");
        sysDeptService.insertDept(dept);
        // 3.创建用户
        SysUser user = new SysUser();
        user.setTenantId(sysTenant.getTenantId());
        user.setUserName(
                sysConfigService.selectConfigByKey("tenant.default.account"));
        user.setNickName("租户管理员");
        user.setDeptId(dept.getDeptId());
        user.setTenantAdmin(true);
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(
                sysConfigService.selectConfigByKey("tenant.default.password")));
        Long[] roleIds = new Long[]{Long.valueOf(
                sysConfigService.selectConfigByKey("tenant.default.roleId"))};
        user.setRoleIds(roleIds);
        sysUserService.insertUser(user);
        return index;
    }

    /**
     * 修改租户
     * 
     * @param sysTenant 租户
     * @return 结果
     */
    @Override
    public int updateSysTenant(SysTenant sysTenant)
    {
        sysTenant.setUpdateTime(DateUtils.getNowDate());
        return sysTenantMapper.updateSysTenant(sysTenant);
    }

    /**
     * 批量删除租户
     * 
     * @param ids 需要删除的租户ID
     * @return 结果
     */
    @Override
    public int deleteSysTenantByIds(Long[] ids)
    {
        return sysTenantMapper.deleteSysTenantByIds(ids);
    }

    /**
     * 删除租户信息
     * 
     * @param id 租户ID
     * @return 结果
     */
    @Override
    public int deleteSysTenantById(Long id)
    {
        return sysTenantMapper.deleteSysTenantById(id);
    }
}
