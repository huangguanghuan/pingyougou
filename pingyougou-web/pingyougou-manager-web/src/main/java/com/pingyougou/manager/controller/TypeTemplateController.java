package com.pingyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pingyougou.common.pojo.PageResult;
import com.pingyougou.pojo.TypeTemplate;
import com.pingyougou.service.TypeTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController{
    @Reference(timeout = 10000)
    private TypeTemplateService typeTemplateService;
    //分页查询
    @GetMapping("/findByPage")
  public PageResult findByPage(TypeTemplate typeTemplate , Integer page, Integer rows){
        // Get请求中文转码
        if(typeTemplate !=null && StringUtils.isNoneBlank(typeTemplate.getName())){
            try {
                typeTemplate.setName(new String(typeTemplate.getName()
                        .getBytes("ISO8859-1"),"UTF-8"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
            return typeTemplateService.findByPage(typeTemplate,page,rows);
    }
    @PostMapping("/save")
    public boolean save(@RequestBody TypeTemplate typeTemplate){
        try {
            typeTemplateService.save(typeTemplate);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @PostMapping("/update")
    public boolean update(@RequestBody TypeTemplate typeTemplate){
        try {
            typeTemplateService.update(typeTemplate);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @GetMapping("delete")
    public Boolean delete(Long[] ids){
        // get请求中文转码
        try {
            typeTemplateService.deleteAll(ids);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /** 查询类型模版 */
    @GetMapping("/findTypeTemplateList")
    public List<Map<String,Object>> findTypeTemplateList(){
        return typeTemplateService.findTypeTemplateList();

    }
}
