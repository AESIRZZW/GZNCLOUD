package com.gzncloud.domain;

public class RequestLocker {
	private Long device;
	private Short cabinet;
	private Integer locker;

	public Long getDevice() {
		return device;
	}

	public void setDevice(Long device) {
		this.device = device;
	}

	public Short getCabinet() {
		return cabinet;
	}

	public void setCabinet(Short cabinet) {
		this.cabinet = cabinet;
	}

	public Integer getLocker() {
		return locker;
	}

	public void setLocker(Integer locker) {
		this.locker = locker;
	}
}
