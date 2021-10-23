package com.ruoyi.web.controller.system;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysTenant;
import com.ruoyi.system.service.ISysTenantService;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 租户Controller
 * 
 * @author ruoyi
 * @date 2021-02-04
 */
@RestController
@RequestMapping("/system/tenant")
public class SysTenantController extends BaseController
{
    @Autowired
    private ISysTenantService sysTenantService;

    /**
     * 查询租户列表
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysTenant sysTenant)
    {
        startPage();
        List<SysTenant> list = sysTenantService.selectSysTenantList(sysTenant);
        return getDataTable(list);
    }

    /**
     * 导出租户列表
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:export')")
    @Log(title = "租户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysTenant sysTenant)
    {
        List<SysTenant> list = sysTenantService.selectSysTenantList(sysTenant);
        ExcelUtil<SysTenant> util = new ExcelUtil<SysTenant>(SysTenant.class);
        return util.exportExcel(list, "tenant");
    }

    /**
     * 获取租户详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysTenantService.selectSysTenantById(id));
    }

    /**
     * 获取租户详细信息
     */
    @GetMapping(value = "/info")
    public AjaxResult info()
    {
        return AjaxResult.success(sysTenantService.selectSysTenantByTenantId(SecurityUtils.getTenantId()));
    }

    /**
     * 新增租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:add')")
    @Log(title = "租户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysTenant sysTenant)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        sysTenant.setCreateUser(user.getUserId());
        return toAjax(sysTenantService.insertSysTenant(sysTenant));
    }

    /**
     * 修改租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:edit')")
    @Log(title = "租户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysTenant sysTenant)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        sysTenant.setUpdateUser(user.getUserId());
        return toAjax(sysTenantService.updateSysTenant(sysTenant));
    }

    /**
     * 删除租户
     */
    @PreAuthorize("@ss.hasPermi('system:tenant:remove')")
    @Log(title = "租户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysTenantService.deleteSysTenantByIds(ids));
    }
}
