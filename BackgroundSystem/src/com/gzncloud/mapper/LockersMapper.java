package com.gzncloud.mapper;

import com.gzncloud.domain.Lockers;

public interface LockersMapper {
    int deleteByPrimaryKey(Long locker);

    int insert(Lockers record);

    int insertSelective(Lockers record);

    Lockers selectByPrimaryKey(Long locker);

    int updateByPrimaryKeySelective(Lockers record);

    int updateByPrimaryKey(Lockers record);
}