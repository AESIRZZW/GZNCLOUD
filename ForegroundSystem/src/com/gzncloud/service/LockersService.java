package com.gzncloud.service;

import java.util.HashMap;
import java.util.List;

import com.gzncloud.domain.Cabinets;
import com.gzncloud.domain.Lockers;
import com.gzncloud.domain.OrderedLockers;
import com.gzncloud.domain.RequestLocker;

public interface LockersService {
	HashMap<String, Object> showInfoByRequestLocker(RequestLocker requestLocker);

	List<Lockers> selectLockersListByCabinet(Long cabinet);

	List<Lockers> selectLockersListByCabinetsList(List<Cabinets> cabinets);

	List<Lockers> selectLockersListBySequence(Integer sequence);

	Boolean openLockers(OrderedLockers lockersNeedOpen);

	Lockers selectByDeviceAndSequence(Long device, Short cabinetSequence, Integer lockerSequence);
}
