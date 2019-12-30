package com.pingyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pingyougou.pojo.Goods;
import com.pingyougou.service.GoodsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Reference(timeout = 10000)
    private GoodsService goodsService;
    // 添加商品
    @PostMapping("save")
    public boolean save(@RequestBody Goods goods){
        try {
            //获取用户名
            String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
            //设置商家Id
            goods.setSellerId(sellerId);
            goodsService.save(goods);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
