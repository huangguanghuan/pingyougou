package com.pingyougou.mapper;



import com.pingyougou.pojo.Brand;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {
    /** 查询全部品牌*/
    @Select("select * from tb_brand order by id asc")
    List<Brand> findAll();
}
