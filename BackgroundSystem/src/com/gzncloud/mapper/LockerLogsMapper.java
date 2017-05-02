package com.gzncloud.mapper;

import com.gzncloud.domain.LockerLogs;

public interface LockerLogsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LockerLogs record);

    int insertSelective(LockerLogs record);

    LockerLogs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LockerLogs record);

    int updateByPrimaryKey(LockerLogs record);
}