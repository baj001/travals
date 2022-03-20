package com.baizhi.travels.service;

import com.baizhi.travels.entity.Province;
import sun.plugin.viewer.context.IExplorerAppletContext;

import java.util.List;

/**
 * @author sfkstart
 * @create 2022-01-19-21:59
 */
public interface ProvinceService {

    //参数1：当前页 参数2：每页显示记录数
    List<Province> findByPage(Integer page, Integer rows);

    //查询总条数
    Integer findTotals();

    //保存省份方法
    void save(Province province);

    //删除s删除省份的方法
    void delete(String id);

    //查询省份信息
    Province findOne(String id);

    //修改省份信息
    void update(Province province);
}
