package com.zhuhuibao.mybatis.memCenter.mapper;

import com.zhuhuibao.mybatis.memCenter.entity.Exhibition;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

public interface ExhibitionMapper {
    int publishExhibition(Exhibition exhibition);

    Exhibition queryExhibitionInfoById(String id);

    int updateByPrimaryKeySelective(Exhibition record);

    List<Exhibition> findAllMeetingOrderInfo(RowBounds rowBounds,Map<String, Object> map);

}