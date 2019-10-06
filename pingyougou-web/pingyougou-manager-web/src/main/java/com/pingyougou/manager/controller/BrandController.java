package com.pingyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pingyougou.pojo.Brand;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pingyougou.service.BrandService;

import java.util.List;
@RestController
public class BrandController {
    /**
     * 引用服务接口代理对象
     * timeout: 调用服务接口方法超时时间毫秒数
     */
  @Reference(timeout = 10000)
  private BrandService brandService;
    /** 查询全部品牌*/
    @GetMapping("/brand/findAll")
    public List<Brand> findAll(){
        return brandService.findAll();
    }
}
