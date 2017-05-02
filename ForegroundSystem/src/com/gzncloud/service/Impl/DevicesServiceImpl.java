package com.gzncloud.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.gzncloud.domain.Cabinets;
import com.gzncloud.domain.Devices;
import com.gzncloud.domain.Goods;
import com.gzncloud.domain.Lockers;
import com.gzncloud.mapper.DevicesMapper;
import com.gzncloud.service.CabinetsService;
import com.gzncloud.service.DevicesService;
import com.gzncloud.service.GoodsService;
import com.gzncloud.service.LockersService;

@Service
public class DevicesServiceImpl implements DevicesService {

	@Resource
	private DevicesMapper devicesMapper;
	@Resource
	private CabinetsService cabinetsService;
	@Resource
	private LockersService lockersService;
	@Resource
	private GoodsService goodsService;

	/**
	 * 根据{前台请求设备编号}返回{前台接收设备数据}
	 * 
	 * @param device
	 * @return HashMap<String, Object>
	 */
	@Override
	public HashMap<String, Object> showAllInfoByDevice(Long device) {
		// TODO 自动生成的方法存根
		// 声明变量
		Devices devices = new Devices();
		List<Cabinets> cabinets = new ArrayList<Cabinets>();
		Goods goods = new Goods();

		List<Lockers> lockers = new ArrayList<Lockers>();
		// 调用查询服务获取所需数据
		devices = selectDevicesByDevice(device);
		cabinets = cabinetsService.selectCabinetsListByDevice(device);

		// 如果查询成功，生成回传数据
		if (devices != null && cabinets != null) {
			// 构建回传数据
			// 根数据
			HashMap<String, Object> map = new HashMap<String, Object>();
			// 一级子数据
			List<Object> primarySubList = new ArrayList<Object>();

			// 构建并填充子级数据
			map.put("device_status", devices.getStatus());
			map.put("error_message", "请使用微信或支付宝扫描该二维码");
			map.put("pay_url", "www.baidu.com");
			map.put("cabinets_count", cabinets.size());
			for (int i = 0; i < cabinets.size(); i++) {
				lockers = lockersService.selectLockersListByCabinet(cabinets.get(i).getCabinet());
				if (lockers.isEmpty()) {
					continue;
				} else {
					// 二级子数据
					HashMap<String, Object> secondarySubMap = new HashMap<String, Object>();
					secondarySubMap.put("cabinet" + (i + 1) + "_lockers_count", lockers.size());
					// 三级子数据
					List<Object> thirdarySubList = new ArrayList<Object>();
					for (int j = 0; j < lockers.size(); j++) {
						// 四级子数据
						HashMap<String, Object> fourarySubMap = new HashMap<String, Object>();
						goods = goodsService.selectGoodsByGood(lockers.get(j).getGood());
						if (goods != null) {
							fourarySubMap.put("cabinet", cabinets.get(i).getSequence());
							fourarySubMap.put("locker", lockers.get(j).getSequence());
							fourarySubMap.put("locker_id", lockers.get(j).getLocker());
							fourarySubMap.put("name", goods.getName());
							fourarySubMap.put("price", goods.getRetail_price());
							fourarySubMap.put("image", goods.getImage1());
							thirdarySubList.add(fourarySubMap);
						} else {
							fourarySubMap.put("cabinet", cabinets.get(i).getSequence());
							fourarySubMap.put("locker", lockers.get(j).getSequence());
							fourarySubMap.put("locker_id", lockers.get(j).getLocker());
							fourarySubMap.put("name", "");
							fourarySubMap.put("price", 0);
							fourarySubMap.put("image", "");
							thirdarySubList.add(fourarySubMap);
						}
					}
					secondarySubMap.put("lockers", thirdarySubList);
					primarySubList.add(secondarySubMap);
				}
			}
			map.put("cabinets", primarySubList);
			System.out.println("     查询到设备信息如下:");
			System.out.println(map.toString());
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 根据{设备编号}查询设备信息
	 * 
	 * @param device
	 * @return Devices
	 */
	public Devices selectDevicesByDevice(Long device) {
		Devices devices = devicesMapper.selectByPrimaryKey(device);
		if (devices == null) {
			System.out.println("          未找到该设备");
			return null;
		}
		return devices;
	}
}