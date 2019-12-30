package com.pingyougou.mapper;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import com.pingyougou.pojo.ItemCat;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemCatMapper 数据访问接口
 * @date 2019-10-15 11:12:26
 * @version 1.0
 */
public interface ItemCatMapper extends Mapper<ItemCat>{

    //根据父级Id查询商品
    @Select("select * from`tb_item_cat`where parent_id=#{parentId}")
    List<ItemCat> findItemCatByParentId(Long parentId);
    /** 批量删除商品类目  */
    void deleteById (List<Long> idLists);
}