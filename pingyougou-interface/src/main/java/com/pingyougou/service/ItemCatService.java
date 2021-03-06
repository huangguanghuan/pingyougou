package com.pingyougou.service;

import com.pingyougou.mapper.ItemCatMapper;
import com.pingyougou.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.io.Serializable;
/**
 * ItemCatService 服务接口
 * @date 2019-10-15 11:19:00
 * @version 1.0
 */
public interface ItemCatService {


	/** 添加方法 */
	void save(ItemCat itemCat);

	/** 修改方法 */
	void update(ItemCat itemCat);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	ItemCat findOne(Serializable id);

	/** 查询全部 */
	List<ItemCat> findAll();

	/** 多条件分页查询 */
	List<ItemCat> findByPage(ItemCat itemCat, int page, int rows);

	//根据父级Id查询商品
	List<ItemCat> findItemCatByParentId(Long parentId);

	/** 添加商品类目 */
	void saveItemCat(ItemCat itemCat);
     //	 删除商品类目
    void deleteItemCat(Long[] ids);
	/** 查询模版列表 */
	void updateItemCat(ItemCat itemCat);
}