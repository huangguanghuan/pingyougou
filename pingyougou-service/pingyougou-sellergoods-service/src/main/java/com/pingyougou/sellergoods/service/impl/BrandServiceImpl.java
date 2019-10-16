package com.pingyougou.sellergoods.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pingyougou.mapper.BrandMapper;
import com.pingyougou.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import com.pingyougou.service.BrandService;

import java.io.Serializable;
import java.util.List;
@Service(interfaceName = "com.pingyougou.service.BrandService")
@Transactional
public class BrandServiceImpl implements BrandService {
    /** 注入数据访问接口代理对像*/
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public void save(Brand brand) {
       brandMapper.insertSelective(brand);
    }

    @Override
    public void update(Brand brand) {
       brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Serializable id) {

    }

    @Override
    public void deleteAll(Serializable[] ids) {

    }

    @Override
    public Brand findOne(Serializable id) {
        return null;
    }

    public List<Brand> findAll() {

      return brandMapper.selectAll();
         }

    @Override
    public List<Brand> findByPage(Brand brand, int page, int rows) {
        return null;
    }
}