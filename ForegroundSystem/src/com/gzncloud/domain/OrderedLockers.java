package com.gzncloud.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class OrderedLockers {
	private Long device;
	private ArrayList<HashMap<String, Long>> lockers;

	public Long getDevice() {
		return device;
	}

	public void setDevice(Long device) {
		this.device = device;
	}

	public ArrayList<HashMap<String, Long>> getLockers() {
		return lockers;
	}

	public void setLockers(ArrayList<HashMap<String, Long>> lockers) {
		this.lockers = lockers;
	}

	// 返回购物盒序号列表
	public List<Long> getCabinetsSquence(ArrayList<HashMap<String, Long>> lockers) {
		ArrayList<Long> cabinetsSquence = new ArrayList<Long>();
		Iterator<HashMap<String, Long>> it = lockers.iterator();
		while (it.hasNext()) {
			HashMap<String, Long> hashMap = (HashMap<String, Long>) it.next();
			cabinetsSquence.add(hashMap.get("cabinet"));
		}
		return cabinetsSquence;
	}

	// 返回购物柜序号列表
	public List<Long> getLockersSquence(ArrayList<HashMap<String, Long>> lockers) {
		ArrayList<Long> lockersSquence = new ArrayList<Long>();
		Iterator<HashMap<String, Long>> it = lockers.iterator();
		while (it.hasNext()) {
			HashMap<String, Long> hashMap = (HashMap<String, Long>) it.next();
			lockersSquence.add(hashMap.get("locker"));
		}
		return lockersSquence;
	}

	// 返回购物盒编号列表
	public List<Long> getLockersId(ArrayList<HashMap<String, Long>> lockers) {
		ArrayList<Long> lockersId = new ArrayList<Long>();
		Iterator<HashMap<String, Long>> it = lockers.iterator();
		while (it.hasNext()) {
			HashMap<String, Long> hashMap = (HashMap<String, Long>) it.next();
			lockersId.add(hashMap.get("locker_id"));
		}
		return lockersId;
	}
	
	// 返回商品价格列表
	public List<Long> getPrices(ArrayList<HashMap<String, Long>> lockers) {
		ArrayList<Long> lockersId = new ArrayList<Long>();
		Iterator<HashMap<String, Long>> it = lockers.iterator();
		while (it.hasNext()) {
			HashMap<String, Long> hashMap = (HashMap<String, Long>) it.next();
			lockersId.add(hashMap.get("price"));
		}
		return lockersId;
	}
	
}