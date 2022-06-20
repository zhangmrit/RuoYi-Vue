package com.ruoyi.common.extension;

import java.util.List;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

/**
 * 扩展默认的数据方法
 **/
public class MpSqlInjector extends DefaultSqlInjector
{
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass,
            TableInfo tableInfo)
    {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass,
                tableInfo);
        methodList.add(new InsertBatchSomeColumn());
        return methodList;
    }
}