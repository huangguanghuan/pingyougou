package com.pingyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pingyougou.common.pojo.PageResult;
import com.pingyougou.mapper.SpecificationOptionMapper;
import com.pingyougou.mapper.TypeTemplateMapper;
import com.pingyougou.pojo.SpecificationOption;
import com.pingyougou.pojo.TypeTemplate;
import com.pingyougou.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service(interfaceName = "com.pingyougou.service.TypeTemplateService")
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {
    @Autowired
    private TypeTemplateMapper typeTemplateMapper;
    @Autowired
    private SpecificationOptionMapper specificationOptionMapper;


    @Override
    public void save(TypeTemplate typeTemplate) {
        typeTemplateMapper.insertSelective(typeTemplate);

    }

    // 修改类型模型
    @Override
    public void update(TypeTemplate typeTemplate) {
        typeTemplateMapper.updateByPrimaryKeySelective(typeTemplate);
    }

    @Override
    public void delete(Serializable id) {

    }

    @Override
    public void deleteAll(Serializable[] ids) {
        try {
          // 创建 示范对象
            Example example = new Example(TypeTemplate.class);
            // 创建条件对象
            Example.Criteria criteria = example.createCriteria();
            //添加in条件
            criteria.andIn("id",Arrays.asList(ids));
            //条件删除
            typeTemplateMapper.deleteByExample(example);
        }catch (Exception e){
                throw new RuntimeException(e);
        }

    }
    /** 根据主键id查询类型模版 */
    @Override
    public TypeTemplate findOne(Serializable id) {
        return typeTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TypeTemplate> findAll() {
        return null;
    }

    //分页查询
    @Override
    public PageResult findByPage(TypeTemplate typeTemplate, int page, int rows) {
        try {
            PageInfo<TypeTemplate> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(new ISelect() {
                @Override
                public void doSelect() {
                    typeTemplateMapper.findAll(typeTemplate);
                }
            });
            return new PageResult(pageInfo.getTotal(),pageInfo.getList());
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
    /** 查询类型模版 */
    @Override
    public List<Map<String, Object>> findTypeTemplateList() {
       try {
           return typeTemplateMapper.findTypeTemplateList();
       }catch (Exception e){
           throw new RuntimeException(e);
       }
    }

    /** 根据模版id查询所有的规格与规格选项 */
    @Override
    public List<Map> findSpecByTemplateId(Long id) {
        try {
            /** 根据主键id查询模版 */
            TypeTemplate typeTemplate = findOne(id);
            /**
             * [{"id":33,"text":"电视屏幕尺寸"}]
             * 获取模版中所有的规格，转化成  List<Map>
             *     JSON.parseArray() :[{},{}]
             *    JSON.parseObject(): {}
             *    */
            String specIds = typeTemplate.getSpecIds();
            List<Map> specLists = JSON.parseArray(specIds, Map.class);

            //迭代模板中所有的规格
            for (Map spc : specLists) {
                //创建查询条件对象
                SpecificationOption so= new SpecificationOption();
                Long specId = Long.valueOf(spc.get("id").toString());
                so.setSpecId(specId);
                //通过Id,查询规格选项数据
                List<SpecificationOption> options = specificationOptionMapper.select(so);
                spc.put("options",options);
        }
            return specLists;
        } catch (Exception ex) {
           throw new RuntimeException(ex);
        }
    }
}
