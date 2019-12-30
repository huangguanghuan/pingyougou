package com.pingyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pingyougou.common.pojo.PageResult;

import com.pingyougou.pojo.Specification;
import com.pingyougou.pojo.SpecificationOption;
import com.pingyougou.service.SpecificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/specification")
public class SpecificationController {
    /**
     * 引用服务接口代理对象
     * timeout: 调用服务接口方法超时时间毫秒数
     */
    @Reference(timeout = 10000)
    private SpecificationService specificationService;
    //分页查询
    @GetMapping("/findByPage")
    public PageResult findByPage(Specification specification, Integer page, Integer rows){
        //GEI请求中文转码
        if (specification !=null && StringUtils.isNoneBlank(specification.getSpecName())){
            try {
                specification.setSpecName(new String(specification.getSpecName()
                        .getBytes("ISO8859-1"),"UTF-8"));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
              return specificationService.findByPage(specification,page,rows);
    }
    @PostMapping("/save")
    public boolean save(@RequestBody Specification specification){
        try {
         specificationService.save(specification);
            return true;
        }catch (Exception ex){
          ex.printStackTrace();
        }
        return false;
}
    /** 根据规格主键查询规格选项 */
    @GetMapping("/findSpecOption")
    private List<SpecificationOption>findSpecOption(Long id){
        return specificationService.findSpecOption(id);
    }
 //修改规格
    @PostMapping("/update")
    private boolean update(@RequestBody Specification specification){
        try {
            specificationService.update(specification);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    // 批量删除
    @GetMapping("/delete")
    private boolean delete(Long[] ids){
        try {
            specificationService.deleteAll(ids);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /** 查询所有的规格(id与specName) */
    @GetMapping("/findSpecList")
    public List<Map<String,Object>> findSpecList(){
        return specificationService.findAllByIdAndName();
    }
}
