package com.pingyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pingyougou.mapper.GoodsDescMapper;
import com.pingyougou.mapper.GoodsMapper;
import com.pingyougou.pojo.Goods;
import com.pingyougou.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
@Transactional
@Service(interfaceName = "com.pingyougou.service.GoodsService")
public class GoodsServiceImpl implements GoodsService {
   //注入数据访问代理对象
   @Autowired
   private GoodsMapper goodsMapper;
    @Autowired
    protected GoodsDescMapper goodsDescMapper;
    /** 添加商品 */
    @Override
    public void save(Goods goods) {
        try {
            //设置未审核状态
            goods.setAuditStatus("0");
            goodsMapper.insertSelective(goods);
            // 为商品描述对象设置主键Id
            goods.getGoodsDesc().setGoodsId(goods.getId());
            goodsDescMapper.insertSelective(goods.getGoodsDesc());
        } catch (Exception ex) {
           throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Goods goods) {

    }

    @Override
    public void delete(Serializable id) {

    }

    @Override
    public void deleteAll(Serializable[] ids) {

    }

    @Override
    public Goods findOne(Serializable id) {
        return null;
    }

    @Override
    public List<Goods> findAll() {
        return null;
    }

    @Override
    public List<Goods> findByPage(Goods goods, int page, int rows) {
        return null;
    }
}
