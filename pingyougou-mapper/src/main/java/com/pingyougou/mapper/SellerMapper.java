package com.pingyougou.mapper;

import tk.mybatis.mapper.common.Mapper;

import com.pingyougou.pojo.Seller;

import java.util.List;

/**
 * SellerMapper 数据访问接口
 * @date 2019-10-15 11:12:26
 * @version 1.0
 */
public interface SellerMapper extends Mapper<Seller>{

    /** 多条件查询商家 */
 List<Seller>findAll(Seller seller);
}