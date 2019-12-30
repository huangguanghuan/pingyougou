package com.pingyougou.mapper;

import com.pingyougou.pojo.Specification;
import tk.mybatis.mapper.common.Mapper;

import com.pingyougou.pojo.SpecificationOption;

/**
 * SpecificationOptionMapper 数据访问接口
 * @date 2019-10-15 11:12:26
 * @version 1.0
 */
public interface SpecificationOptionMapper extends Mapper<SpecificationOption>{

   //批量插入选项
    void save(Specification specification);
}