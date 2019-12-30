package com.pingyougou.mapper;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import com.pingyougou.pojo.Specification;

import java.util.List;
import java.util.Map;

/**
 * SpecificationMapper 数据访问接口
 * @date 2019-10-15 11:12:26
 * @version 1.0
 */
public interface SpecificationMapper extends Mapper<Specification>{
/** 多条件查询规格 */
       List<Specification>findAll(Specification specification);
    /** 查询所有的规格(id与specName) */
    @Select("SELECT id ,spec_name as text FROM tb_specification ORDER by id asc")
    List<Map<String,Object>> findAllByIdAndName();
}