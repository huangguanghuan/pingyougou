package com.pingyougou.shop.service;

import com.pingyougou.pojo.Seller;
import com.pingyougou.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService{
    // 注入商家服务接口代理对象
    private SellerService sellerService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 创建List集合封装角色
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        //添加角色
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        //根据商家登录名查询商家
        Seller seller = sellerService.findOne(username);
        if (seller != null && seller.getStatus().equals("1")){
            //返回用户信息对象
            return new User(username,seller.getPassword(),grantedAuthorities);
        }
        return null;
    }

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }
}
