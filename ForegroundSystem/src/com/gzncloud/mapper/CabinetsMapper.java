package com.gzncloud.mapper;

import java.util.List;

import com.gzncloud.domain.Cabinets;

public interface CabinetsMapper {
    int deleteByPrimaryKey(Long cabinet);

    int insert(Cabinets record);

    int insertSelective(Cabinets record);

    Cabinets selectByPrimaryKey(Long cabinet);

    int updateByPrimaryKeySelective(Cabinets record);

    int updateByPrimaryKey(Cabinets record);
    
  //根据设备编号号查询购物柜列表
    List<Cabinets> selectByDevice(Long device);
    
    //根据购物柜编号查询盒子门数
    Integer selectLockers_countByCabinets(Long cabinet);
    
    //根据购物柜序号查询购物柜列表
    List<Cabinets> selectBySequence(Short sequence);
}