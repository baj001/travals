package com.baizhi.travels.dao;

import com.baizhi.travels.entity.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sfkstart
 * @create 2022-02-27-15:08
 */
@Mapper
public interface PlaceDAO extends BaseDAO <Place,String> {

    //用于查找某一省份的分页
    List<Place> findByProvinceIdPage(@Param("start") Integer start,@Param("rows") Integer rows,@Param("provinceId") String provinceId);

    //用于查找某一省份的景点数目
    Integer findByProvinceIdCounts(String provinceId);
}
