package com.gzncloud.service;

import java.util.HashMap;
import java.util.List;

import com.gzncloud.domain.Cabinets;

public interface CabinetsService {
	HashMap<String, Object> showInfoByCabinets(Cabinets cabinets);

	List<Cabinets> selectCabinetsListByDevice(Long device);

	List<Cabinets> selectCabinetsListBySequence(Short sequence);
}
