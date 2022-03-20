package com.baizhi.travels.service;

import com.baizhi.travels.dao.ProvinceDAO;
import com.baizhi.travels.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author sfkstart
 * @create 2022-01-20-20:01
 */
@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDAO provinceDAO;

    @Override
    public List<Province> findByPage(Integer page, Integer rows) {
        //此处定义起始位置start
        int start = (page - 1) * rows;
        return provinceDAO.findByPage(start, rows);
    }

    @Override
    public Integer findTotals() {
        return provinceDAO.findTotals();
    }

    @Override
    public void save(Province province) {
        province.setPlacecounts(0);//景点个数为0
        provinceDAO.save(province);

    }


    @Override
    public void delete(String id) {
        provinceDAO.delete(id);
    }

    @Override
    public Province findOne(String id) {
        return provinceDAO.findOne(id);
    }


    @Override
    public void update(Province province) {
        provinceDAO.update(province);
    }
}
