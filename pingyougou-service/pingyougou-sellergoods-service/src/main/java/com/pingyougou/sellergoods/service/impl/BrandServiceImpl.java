package com.pingyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pingyougou.common.pojo.PageResult;
import com.pingyougou.mapper.BrandMapper;
import com.pingyougou.pojo.Brand;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;
import com.pingyougou.service.BrandService;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    //删除品牌
    @Override
    public void deleteAll(Serializable[] ids) {
    //创建示范对象
        Example example = new Example(Brand.class);
        //创建条件对象
        Example.Criteria criteria = example.createCriteria();
        //添加in条件
        criteria.andIn("id",Arrays.asList(ids));
        brandMapper.deleteByExample(example);
    }

    @Override
    public Brand findOne(Serializable id) {
        return null;
    }

    public List<Brand> findAll() {

      return null;
         }

    @Override
    public PageResult findByPage(Brand brand, int page, int rows) {
        try {
            PageInfo<Brand> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(new ISelect() {
                @Override
                public void doSelect() { brandMapper.findAll(brand);
                }
            });
            return new PageResult(pageInfo.getTotal(),pageInfo.getList());
        }catch (Exception ex){
            throw  new RuntimeException(ex);
        }
    }
    /** 查询所有的品牌(id与name) */
    @Override
    public List<Map<String, Object>> findAllByIdAndName() {
        try {
            return brandMapper.findAllByIdAndName();
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}