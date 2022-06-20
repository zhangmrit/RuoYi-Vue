package com.ruoyi.system.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 租户对象 sys_tenant
 * 
 * @author ruoyi
 * @date 2021-02-04
 */
@Data
@Accessors(chain = true)
public class SysTenant implements Serializable
{
    //
    private static final long serialVersionUID = 8061890463653765699L;

    /** 主键 */
    private Long              id;

    /** 租户ID */
    @Excel(name = "租户ID")
    private String            tenantId;

    /** 租户名称 */
    @Excel(name = "租户名称")
    private String            tenantName;

    /** 域名地址 */
    @Excel(name = "域名地址")
    private String            domain;

    /** 系统背景 */
    @Excel(name = "系统背景")
    private String            backgroundUrl;

    /** 联系人 */
    @Excel(name = "联系人")
    private String            linkman;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String            contactNumber;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String            address;

    /** 账号额度 */
    @Excel(name = "账号额度")
    private Long              accountNumber;

    /** 过期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "过期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date              expireTime;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long              createUser;

    /** 修改人 */
    @Excel(name = "修改人")
    private Long              updateUser;

    /** 状态 */
    @Excel(name = "状态")
    private String            status;

    /** 是否已删除 */
    @Excel(name = "是否已删除")
    private Boolean           deleted;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date              createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date              updateTime;
}
