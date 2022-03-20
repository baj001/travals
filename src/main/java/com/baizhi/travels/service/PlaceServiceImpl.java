package com.baizhi.travels.service;

import com.baizhi.travels.dao.PlaceDAO;
import com.baizhi.travels.entity.Place;
import com.baizhi.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sfkstart
 * @create 2022-02-27-15:56
 */
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService{

    @Autowired
    private PlaceDAO placeDAO;
    @Autowired
    private ProvinceService provinceService;

    @Override
    public List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId) {
        int start = (page - 1) * rows;
        return placeDAO.findByProvinceIdPage(start, rows, provinceId);
    }

    @Override
    public Integer findByProvinceIdCounts(String provinceId) {
        return placeDAO.findByProvinceIdCounts(provinceId);
    }

    @Override
    public void save(Place place) {
        //保存景点
        placeDAO.save(place);
        //查询原有的省份信息
        Province province = provinceService.findOne(place.getProvinceid());
        //更新省份信息的景点个数
        province.setPlacecounts(province.getPlacecounts()+1);
        provinceService.update(province);

    }

    @Override
    public void delete(String id) {
        //不能直接删除景点
        //先要更新省份的个数，然后删除景点
        Place place = placeDAO.findOne(id);
        Province province = provinceService.findOne(place.getProvinceid());
        province.setPlacecounts(province.getPlacecounts()-1);
        provinceService.update(province);
        //删除景点信息
        placeDAO.delete(id);

    }
}
