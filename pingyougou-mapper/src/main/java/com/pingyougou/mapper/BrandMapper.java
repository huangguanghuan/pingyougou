package com.pingyougou.mapper;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import com.pingyougou.pojo.Brand;

import java.util.List;
import java.util.Map;

/**
 * BrandMapper 数据访问接口
 * @date 2019-10-15 11:12:26
 * @version 1.0
 */
public interface BrandMapper extends Mapper<Brand>{

  //多条件查询
    List<Brand> findAll(Brand brand);
 @Select("SELECT id,name as text FROM tb_brand order by id asc")
    List<Map<String,Object>> findAllByIdAndName();

}