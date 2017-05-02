package com.gzncloud.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gzncloud.amqp.impl.AmqpClient;
import com.gzncloud.domain.Cabinets;
import com.gzncloud.domain.Devices;
import com.gzncloud.domain.Goods;
import com.gzncloud.domain.Lockers;
import com.gzncloud.domain.OrderedLockers;
import com.gzncloud.domain.RequestLocker;
import com.gzncloud.mapper.LockersMapper;
import com.gzncloud.service.CabinetsService;
import com.gzncloud.service.DevicesService;
import com.gzncloud.service.GoodsService;
import com.gzncloud.service.LockersService;

@Service
public class LockersServiceImpl implements LockersService {

	@Resource
	private LockersMapper lockersMapper;
	@Resource
	private DevicesService devicesService;
	@Resource
	private CabinetsService cabinetsService;
	@Resource
	private GoodsService goodsService;
	@Resource
	private AmqpClient amqpClient;

	/**
	 * 根据{前台请求购物盒数据}返回{前台接收购物盒数据}
	 * 
	 * @param requestLocker
	 * @return HashMap<String, Object>
	 */
	@Override
	public HashMap<String, Object> showInfoByRequestLocker(RequestLocker requestLocker) {
		// TODO 自动生成的方法存根
		// 声明变量
		Devices devices = new Devices();
		Goods goods = new Goods();
		Lockers lockers = new Lockers();

		// 调用查询服务获取所需数据
		devices = devicesService.selectDevicesByDevice(requestLocker.getDevice());
		if (devices != null) {
			lockers = selectByDeviceAndSequence(requestLocker.getDevice(), requestLocker.getCabinet(),
					requestLocker.getLocker());
			if (lockers != null) {
				goods = goodsService.selectGoodsByGood(lockers.getGood());
				if (goods != null) {

					// 如果查询成功，生成回传数据
					// 构建回传数据
					// 根数据
					HashMap<String, Object> map = new HashMap<String, Object>();
					// 一级子数据
					HashMap<String, Object> subMap = new HashMap<String, Object>();
					// 填充数据
					map.put("device_status", devices.getStatus());
					map.put("error_message", "请使用微信或支付宝扫描该二维码");
					map.put("pay_url", "www.baidu.com");
					subMap.put("cabinet", requestLocker.getCabinet());
					subMap.put("locker", requestLocker.getLocker());
					subMap.put("locker_id", lockers.getLocker());
					subMap.put("name", goods.getName());
					subMap.put("price", goods.getRetail_price());
					subMap.put("image", goods.getImage1());
					map.put("lockers", subMap);
					System.out.println("     查询到购物盒信息如下:");
					System.out.println(map.toString());
					return map;
				}
			}
		}
		System.out.println("     请求数据不完整，查询失败");
		return null;
	}

	/**
	 * 根据{购物盒数据}发送开盒消息队列
	 * 
	 * @param lockersNeedOpen
	 * @return Boolean
	 */
	@Override
	public Boolean openLockers(OrderedLockers lockersNeedOpen) {
		// TODO 自动生成的方法存根
		if (lockersNeedOpen != null && lockersNeedOpen.getDevice() != null && lockersNeedOpen.getLockers() != null) {
			// 声明变量并初始化
			String deviceData;
			String lockersData = "";
			int count = lockersNeedOpen.getLockers().size();

			// 拼接开盒消息数据
			deviceData = lockersNeedOpen.getDevice().toString() + ":" + count;
			for (int i = 0; i < count; i++) {
				lockersData = lockersData
						+ lockersNeedOpen.getCabinetsSquence(lockersNeedOpen.getLockers()).get(i).toString() + "."
						+ lockersNeedOpen.getLockersSquence(lockersNeedOpen.getLockers()).get(i).toString() + "."
						+ lockersNeedOpen.getLockersId(lockersNeedOpen.getLockers()).get(i).toString() + "." + 0 + ";";
				
			}
			System.out.println("     请求打开的设备信息是:  " + deviceData);
			System.out.println("     请求打开的购物盒信息是:");
			System.out.println("     " + deviceData);
			amqpClient.openLockers(deviceData, lockersData);
			return true;
		}
		System.out.println("     请求打开的购物盒数据有误，请求失败");
		return false;
	}

	/**
	 * 根据{购物柜编号}查询购物盒列表
	 * 
	 * @param cabinet
	 * @return List<Lockers>
	 */
	@Override
	public List<Lockers> selectLockersListByCabinet(Long cabinet) {
		// 声明变量
		List<Lockers> lockers = new ArrayList<Lockers>();
		// 调用DAO层查询
		lockers = lockersMapper.selectByCabinet(cabinet);
		if (lockers == null) {
			System.out.println("          该购物柜下没有购物盒");
			return null;
		}
		return lockers;
	}

	/**
	 * 根据{购物柜列表}查询购物盒列表
	 * 
	 * @param cabinets
	 * @return List<Lockers>
	 */
	@Override
	public List<Lockers> selectLockersListByCabinetsList(List<Cabinets> cabinets) {
		if (cabinets.isEmpty()) {
			System.out.println("          购物柜列表为空，无法查询");
			return null;
		}
		// 声明变量
		List<Lockers> lockers = new ArrayList<Lockers>();
		List<Lockers> lockersEachCabinet = new ArrayList<Lockers>();
		// 调用DAO层查询
		for (int i = 0; i < cabinets.size(); i++) {
			lockersEachCabinet = selectLockersListByCabinet(cabinets.get(i).getCabinet());
			lockers.addAll(lockersEachCabinet);
		}
		if (lockers.isEmpty()) {
			System.out.println("          该购物柜下没有购物盒");
			return null;
		} else {
			return lockers;
		}
	}

	/**
	 * 根据{购物柜顺序}查询购物盒列表
	 * 
	 * @param sequence
	 * @return List<Lockers>
	 */
	@Override
	public List<Lockers> selectLockersListBySequence(Integer sequence) {
		// TODO 自动生成的方法存根
		if (sequence == null) {
			System.out.println("          购物盒序号为空，查询失败");
			return null;
		} else {
			List<Lockers> lockers = lockersMapper.selectBySequence(sequence);
			if (lockers.isEmpty()) {
				System.out.println("          没有该序号的购物盒");
				return null;
			} else {
				return lockers;
			}
		}
	}

	/**
	 * 根据{设备编号/购物柜序号/购物盒序号}查询购物盒信息
	 * 
	 * @param devices
	 * @param cabinetSequence
	 * @param lockerSequence
	 * @return Lockers
	 */
	@Override
	public Lockers selectByDeviceAndSequence(Long device, Short cabinetSequence, Integer lockerSequence) {
		// 声明变量
		List<Cabinets> cabinetsList = new ArrayList<Cabinets>();
		List<Lockers> lockersList = new ArrayList<Lockers>();
		Lockers lockers = new Lockers();

		// 执行查询
		// 根据购物柜序号查询购物柜列表
		cabinetsList = cabinetsService.selectCabinetsListBySequence(cabinetSequence);
		if (cabinetsList == null) {
			return null;
		}
		// 根据购物盒序号查询购物盒列表
		lockersList = selectLockersListBySequence(lockerSequence);
		if (lockersList == null)
			return null;
		// 查询该设备下是否有该购物柜
		for (int i = 0; i < cabinetsList.size(); i++) {
			System.out.println("          正在扫描" + cabinetsList.get(i).getCabinet() + "号购物柜");
			if (cabinetsList.get(i).getDevice().equals(device)) {
				System.out.println("          已查询到购物柜" + cabinetsList.get(i).getCabinet() + "属于该设备");
				// 查询该购物柜下是否有该购物盒
				for (int j = 0; j < lockersList.size(); j++) {
					System.out.println("          正在扫描" + lockersList.get(j).getLocker() + "号购物盒");
					if (lockersList.get(j).getCabinet() == cabinetsList.get(i).getCabinet()) {
						System.out.println("          已在购物柜" + cabinetsList.get(i).getCabinet() + "查询到该购物盒");
						// 根据购物盒编号查询商品信息
						lockers = lockersList.get(j);
						return lockers;
					}
					System.out.println(
							"          购物柜" + cabinetsList.get(i).getCabinet() + "下未找到序号为" + lockerSequence + "的购物盒");
					continue;
				}
				System.out.println("          该设备下未找到序号为" + cabinetSequence + "的购物柜");
				continue;
			}
		}
		System.out.println("          该设备下未找到符合条件的购物盒");
		return null;
	}
}
