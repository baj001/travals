package com.baizhi.travels.dao;

import com.baizhi.travels.entity.Province;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author sfkstart
 * @create 2022-01-19-20:42
 */
//ProvinceDAO接口继承BaseDAO
@Mapper
public interface ProvinceDAO extends BaseDAO<Province,String>{
}
