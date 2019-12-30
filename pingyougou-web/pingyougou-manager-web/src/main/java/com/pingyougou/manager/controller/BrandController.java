package com.pingyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pingyougou.common.pojo.PageResult;
import com.pingyougou.pojo.Brand;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.pingyougou.service.BrandService;
import org.springframework.web.context.ContextLoader;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/brand")
public class BrandController {
    /**
     * 引用服务接口代理对象
     * timeout: 调用服务接口方法超时时间毫秒数
     */
  @Reference(timeout = 10000)
  private BrandService brandService;


  //分页查询
    @GetMapping("/findByPage")
    public PageResult findByPage(Brand brand ,Integer page,Integer rows){
        //Get中文转码
        if(brand !=null && StringUtils.isNoneBlank(brand.getName())){
             try {
                 brand.setName(new String(brand.getName().getBytes("ISO8859-1"),"UTF-8"));
             }catch (Exception ex){
                ex.printStackTrace();
             }

        }
       return brandService.findByPage(brand, page, rows);
    }
 // 查询全部品牌
    @GetMapping("/findAll")
    public List<Brand>findAll(){
        return brandService.findAll();
    }

    @PostMapping("/update")
    public boolean update(@RequestBody Brand brand){
        try {
            brandService.update(brand);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
            return false;
    }
    @GetMapping("/delete")
    public boolean delete(Integer[] ids){
        try {
            brandService.deleteAll(ids);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    @PostMapping("/save")
    public boolean save(@RequestBody Brand brand){

        try {
            brandService.save(brand);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /** 查询所有的品牌 */
    @GetMapping("/findBrandList")
    public List<Map<String,Object>> findBrandList(){
        return brandService.findAllByIdAndName();
    }

}
