package com.gzncloud.mapper;

import com.gzncloud.domain.Devices;

public interface DevicesMapper {
    int deleteByPrimaryKey(Long device);

    int insert(Devices record);

    int insertSelective(Devices record);

    Devices selectByPrimaryKey(Long device);

    int updateByPrimaryKeySelective(Devices record);

    int updateByPrimaryKeyWithBLOBs(Devices record);

    int updateByPrimaryKey(Devices record);
}