package com.gzncloud.service.Impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gzncloud.domain.Cabinets;
import com.gzncloud.mapper.CabinetsMapper;
import com.gzncloud.service.CabinetsService;

@Service
public class CabinetsServiceImpl implements CabinetsService {

	@Resource
	private CabinetsMapper cabinetsMapper;

	@Override
	public HashMap<String, Object> showInfoByCabinets(Cabinets cabinets) {
		// TODO 自动生成的方法存根
		return null;
	}

	/**
	 * 根据{设备编号}查询购物柜列表
	 * 
	 * @param device
	 * @return List<Cabinets>
	 */
	@Override
	public List<Cabinets> selectCabinetsListByDevice(Long device) {
		// TODO 自动生成的方法存根
		List<Cabinets> cabinets = cabinetsMapper.selectByDevice(device);
		if (cabinets.isEmpty()) {
			System.out.println("          该设备下没有购物柜");
			return null;
		} else {
			return cabinets;
		}
	}

	/**
	 * 根据{购物柜序号}查询购物柜列表
	 * 
	 * @param device
	 * @return List<Cabinets>
	 */
	@Override
	public List<Cabinets> selectCabinetsListBySequence(Short sequence) {
		// TODO 自动生成的方法存根
		if (sequence == null) {
			System.out.println("          购物柜序号为空，查询失败");
			return null;
		} else {
			List<Cabinets> cabinets = cabinetsMapper.selectBySequence(sequence);
			if (cabinets.isEmpty()) {
				System.out.println("          没有该序号的购物柜");
				return null;
			} else {
				return cabinets;
			}
		}
	}
}
