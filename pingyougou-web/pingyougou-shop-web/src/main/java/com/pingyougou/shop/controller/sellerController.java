package com.pingyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pingyougou.pojo.Seller;
import com.pingyougou.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    //注入商家服务接口
    @Reference(timeout = 10000)
    private SellerService sellerService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    //添加商家
    @PostMapping("/save")
    public boolean save(@RequestBody Seller seller){
        try {
            //密码加密
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(seller.getPassword());
            seller.setPassword(password);
            sellerService.save(seller);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
