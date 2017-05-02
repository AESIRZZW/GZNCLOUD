package com.gzncloud.mapper;

import com.gzncloud.domain.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(Long user);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long user);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}