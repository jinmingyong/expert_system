package com.jin.expertsystem.expertsystem.business.common.need;


import tk.mybatis.mapper.common.Mapper;

/**
 * 通用 Mapper, 如果被扫描到会报异常
 *
 * @author GaoLiWei
 * @date 2019/04/10
 */
public interface MyMapper<T> extends Mapper<T> {
}
