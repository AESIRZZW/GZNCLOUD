package com.gzncloud.mapper;

import com.gzncloud.domain.Goods;

public interface GoodsMapper {
    int deleteByPrimaryKey(Long good);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long good);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}