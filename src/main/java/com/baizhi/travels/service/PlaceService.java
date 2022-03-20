package com.baizhi.travels.service;

import com.baizhi.travels.entity.Place;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sfkstart
 * @create 2022-02-27-15:53
 */
public interface PlaceService {

    //用于查找某一省份的分页
    List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId);

    //用于查找某一省份的景点数目
    Integer findByProvinceIdCounts(String provinceId);

    void save(Place place);

    void delete(String id);
}
