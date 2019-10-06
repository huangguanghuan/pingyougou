package com.pingyougou.sellergoods.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.pingyougou.mapper.BrandMapper;
import com.pingyougou.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import com.pingyougou.service.BrandService;

import java.util.List;
@Service(interfaceName = "com.pingyougou.service.BrandService")
@Transactional
public class BrandServiceImpl implements BrandService {
    /** 注入数据访问接口代理对像*/
    @Autowired
    private BrandMapper brandMapper;
    public List<Brand> findAll() {
        return brandMapper.findAll();
    }
}