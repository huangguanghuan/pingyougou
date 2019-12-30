package com.pingyougou.service;

import com.pingyougou.common.pojo.PageResult;
import com.pingyougou.pojo.Specification;
import com.pingyougou.pojo.SpecificationOption;

import java.util.List;
import java.io.Serializable;
import java.util.Map;

/**
 * SpecificationService 服务接口
 * @date 2019-10-15 11:19:00
 * @version 1.0
 */
public interface SpecificationService {

	/** 添加方法 */
	void save(Specification specification);

	/** 修改方法 */
	void update(Specification specification);

	/** 根据主键id删除 */
	void delete(Serializable id);

	/** 批量删除 */
	void deleteAll(Serializable[] ids);

	/** 根据主键id查询 */
	Specification findOne(Serializable id);

	/** 查询全部 */
	List<Specification> findAll();

	/** 多条件分页查询 */
	PageResult findByPage(Specification specification, int page, int rows);
	/** 根据规格主键查询规格选项 */
	List<SpecificationOption> findSpecOption(Long id);
	/** 查询所有的规格(id与specName) */
    List<Map<String,Object>> findAllByIdAndName();
}