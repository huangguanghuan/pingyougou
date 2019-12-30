package com.pingyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pingyougou.common.pojo.PageResult;
import com.pingyougou.mapper.SpecificationMapper;
import com.pingyougou.mapper.SpecificationOptionMapper;
import com.pingyougou.pojo.Specification;
import com.pingyougou.pojo.SpecificationOption;
import com.pingyougou.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service(interfaceName = "com.pingyougou.service.SpecificationService")
@Transactional
public class SpecificationServiceImpl implements SpecificationService {
    /** 注入数据访问接口代理对象 */
    @Autowired
    private SpecificationMapper specificationMapper;

    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;
    // 添加规格选项
    @Override
    public void save(Specification specification) {

   try {
       specificationMapper.insertSelective(specification);
       specificationOptionMapper.save(specification);

   }catch (Exception e){
       throw new RuntimeException(e);
   }
    }
    /** 修改规格与规格选项  */
    @Override
    public void update(Specification specification) {
        // 修改往规格表数据
        try {
            /**########### 修改规格选项表数据 ###########*/
            // 第一步：删除规格选项表中的数据 spec_id
            // delete from tb_specification_option where spec_id = ?
            // 创建规格选项对象，封装删除条件 通用Mapper
            SpecificationOption so = new SpecificationOption();
            so.setSpecId(specification.getId());
            specificationOptionMapper.delete(so);
            specificationOptionMapper.save(specification);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Serializable id) {

    }
    // 批量删除规格与规格选项
    @Override
    public void deleteAll(Serializable[] ids) {
     try {
         for (Serializable id : ids) {
             SpecificationOption so = new SpecificationOption();
             so.setSpecId((long)id);
             specificationOptionMapper.delete(so);
             specificationMapper.deleteByPrimaryKey(id);
         }
     }catch (Exception e){
         throw  new RuntimeException(e);

     }
    }

    @Override
    public Specification findOne(Serializable id) {
        return null;
    }

    @Override
    public List<Specification> findAll() {
        return null;
    }

    /** 多条件分页查询规格 */
    @Override
    public PageResult findByPage(Specification specification,
                                 int page, int rows) {

        try {
            /** 开始分页 */
            PageInfo<Specification> pageInfo = PageHelper.startPage(page,rows)
                    .doSelectPageInfo(new ISelect() {
                @Override
                public void doSelect() {
                    specificationMapper.findAll(specification);
                }
            });
            return new PageResult(pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception ex) {
                  throw new RuntimeException(ex);
        }
    }
    /** 根据规格主键查询规格选项 */
    @Override
    public List<SpecificationOption> findSpecOption(Long id) {
        try {
            // 创建规格选项对象封装查询条件
            SpecificationOption so = new SpecificationOption();
            so.setSpecId(id);
            return specificationOptionMapper.select(so);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Map<String, Object>> findAllByIdAndName() {
        try {
            return specificationMapper.findAllByIdAndName();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
