package com.gzncloud.mapper;

import java.util.List;

import com.gzncloud.domain.Lockers;

public interface LockersMapper {
    int deleteByPrimaryKey(Long locker);

    int insert(Lockers record);

    int insertSelective(Lockers record);

    Lockers selectByPrimaryKey(Long locker);

    int updateByPrimaryKeySelective(Lockers record);

    int updateByPrimaryKey(Lockers record);
    
    //根据购物柜编号号查询购物盒列表
    List<Lockers> selectByCabinet(Long cabinet);
    
    //根据购物盒序号查询购物盒列表
    List<Lockers> selectBySequence(Integer sequence);
}