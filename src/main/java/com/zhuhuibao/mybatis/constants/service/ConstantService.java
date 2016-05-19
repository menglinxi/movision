package com.zhuhuibao.mybatis.constants.service;


import com.zhuhuibao.mybatis.constants.entity.Constant;
import com.zhuhuibao.mybatis.constants.mapper.ConstantMapper;
import com.zhuhuibao.mybatis.memCenter.mapper.JobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConstantService {
    private static final Logger log = LoggerFactory.getLogger(ConstantService.class);

    @Autowired
    ConstantMapper constantMapper;
    @Autowired
    JobMapper jobMapper;
    /**
     * 根据Type Code查询
     * @param code [1..20...]
     * @return
     */
    @Cacheable(value = "constantCache" ,key="#code+#type")
    public Map<String,String> findByTypeCode(String type, String code){
        log.warn("select by db.....");
        return constantMapper.selectByTypeCode(type,code);
    }


    /**
     * 根据类型查询
     * @param type [1..20...]
     * @return
     */
    @Cacheable(value = "constantCache" ,key="#type")
    public List<Constant> findByType(String type){
        log.warn("select by db.....");
        return constantMapper.selectByType(type);
    }

    /**
     * 新增
     * @param constant  [entity]
     * @return
     */
    @CachePut(value = "constantCache" , key = "#constant.id")
    public int insert(Constant constant){
        return constantMapper.insert(constant);
    }

    @CachePut(value = "constantCache" , key = "#constant.id")
    public int insertSelective(Constant constant){
        return constantMapper.insertSelective(constant);
    }

    /**
     * 更新
     * @param constant [entity]
     * @return
     */
    @CachePut(value = "constantCache" , key = "#constant.id")
    public int updateByPrimaryKey(Constant constant){
        return constantMapper.updateByPrimaryKey(constant);
    }

    @CachePut(value = "constantCache" , key = "#constant.id")
    public int updateByPrimaryKeySelective(Constant constant){
        return constantMapper.updateByPrimaryKeySelective(constant);
    }


    /**
     * 删除
     * @param id [1....]
     * @return
     */
    @CacheEvict(value = "constantCache" , key = "#id")
    public int deleteByPrimaryKey(Integer id){
        return constantMapper.deleteByPrimaryKey(id);
    }

    public Map<String, Object> findJobByID(String id) {
        return jobMapper.findJobByID(id);
    }
}
