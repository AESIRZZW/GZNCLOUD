package com.gzncloud.mapper;

import com.gzncloud.domain.Accounts;

public interface AccountsMapper {
    int deleteByPrimaryKey(Long user);

    int insert(Accounts record);

    int insertSelective(Accounts record);

    Accounts selectByPrimaryKey(Long user);

    int updateByPrimaryKeySelective(Accounts record);

    int updateByPrimaryKey(Accounts record);
}