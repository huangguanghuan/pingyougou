package com.pingyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pingyougou.pojo.ItemCat;
import com.pingyougou.service.ItemCatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemCat")
public class ItemCatController {
    /**
     * 引用服务接口代理对象
     * timeout: 调用服务接口方法超时时间毫秒数
     */
    @Reference(timeout = 10000)
    private ItemCatService itemCatService;
    //根据父级Id查询商品
    @GetMapping("/findItemCatByParentId")
    public List<ItemCat> findItemCatByParentId(@RequestParam(value = "parentId",defaultValue = "0") Long parentId){
        return itemCatService.findItemCatByParentId(parentId);
    }
    /** 添加商品类目 */
    @PostMapping("/save")
    public boolean save(@RequestBody ItemCat itemCat){
        try {
            itemCatService.saveItemCat(itemCat);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /** 删除商品类目 */
    @GetMapping("/delete")
    public boolean delete(Long[] ids){
        try {
            itemCatService.deleteItemCat(ids);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /** 查询模版列表 */
    @PostMapping("/update")
  public boolean update(@RequestBody ItemCat itemCat){
        try {
            itemCatService.updateItemCat(itemCat);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
  }
}
