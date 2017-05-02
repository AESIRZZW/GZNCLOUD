package com.gzncloud.service;


import java.util.HashMap;

import com.gzncloud.domain.Devices;

public interface DevicesService {

	HashMap<String, Object> showAllInfoByDevice(Long device);

	Devices selectDevicesByDevice(Long device);
}
