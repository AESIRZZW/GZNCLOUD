package com.gzncloud.mapper;

import com.gzncloud.domain.Cabinets;

public interface CabinetsMapper {
    int deleteByPrimaryKey(Long cabinet);

    int insert(Cabinets record);

    int insertSelective(Cabinets record);

    Cabinets selectByPrimaryKey(Long cabinet);

    int updateByPrimaryKeySelective(Cabinets record);

    int updateByPrimaryKey(Cabinets record);
}