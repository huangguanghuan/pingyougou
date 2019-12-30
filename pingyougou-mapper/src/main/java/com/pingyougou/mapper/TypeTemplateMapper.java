package com.pingyougou.mapper;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import com.pingyougou.pojo.TypeTemplate;

import java.util.List;
import java.util.Map;

/**
 * TypeTemplateMapper 数据访问接口
 * @date 2019-10-15 11:12:26
 * @version 1.0
 */
public interface TypeTemplateMapper extends Mapper<TypeTemplate>{

    /** 多条件查询规格 */
    List<TypeTemplate> findAll(TypeTemplate typeTemplate);
    /** 查询类型模版 */
    @Select("SELECT id ,name from tb_type_template order by id asc")
    List<Map<String,Object>> findTypeTemplateList();
}