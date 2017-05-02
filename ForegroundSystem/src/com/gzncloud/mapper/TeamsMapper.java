package com.gzncloud.mapper;

import com.gzncloud.domain.Teams;

public interface TeamsMapper {
    int deleteByPrimaryKey(Long team);

    int insert(Teams record);

    int insertSelective(Teams record);

    Teams selectByPrimaryKey(Long team);

    int updateByPrimaryKeySelective(Teams record);

    int updateByPrimaryKey(Teams record);
}