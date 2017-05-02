package com.gzncloud.service.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gzncloud.domain.Goods;
import com.gzncloud.mapper.GoodsMapper;
import com.gzncloud.service.CabinetsService;
import com.gzncloud.service.GoodsService;
import com.gzncloud.service.LockersService;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Resource
	private GoodsMapper goodsMapper;
	@Resource
	private LockersService lockersService;
	@Resource
	private CabinetsService cabinetsService;

	/**
	 * 根据{商品编号}查询商品信息
	 * 
	 * @param good
	 * @return Goods
	 */
	public Goods selectGoodsByGood(Long good) {
		// 声明变量
		Goods goods = new Goods();
		// 调用DAO层查询
		goods = goodsMapper.selectByPrimaryKey(good);
		if (goods == null) {
			System.out.println("          未找到编号为" + good + "的商品");
			return null;
		}
		return goods;
	}
}
