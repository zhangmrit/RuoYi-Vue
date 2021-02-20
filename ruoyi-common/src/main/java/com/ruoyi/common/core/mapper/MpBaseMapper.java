/*
 * @(#)MpBaseMapper.java 2021年2月6日 下午5:36:44
 * Copyright 2021 zmr, Inc. All rights reserved. 
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ruoyi.common.core.mapper;

import java.util.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>File：MpBaseMapper.java</p>
 * <p>Title: 扩展mp基础方法</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2021 2021年2月6日 下午5:36:44</p>
 * <p>Company: zmrit.com </p>
 * @author zmr
 * @version 1.0
 */
public interface MpBaseMapper<T> extends BaseMapper<T>
{
    /**
     * 自定义批量插入取代默认循环插入
     * @param list
     * @return
     * @author zmr
     */
    int insertBatchSomeColumn(Collection<T> list);
}
