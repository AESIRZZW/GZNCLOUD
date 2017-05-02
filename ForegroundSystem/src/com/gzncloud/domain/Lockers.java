package com.gzncloud.domain;

import java.util.Date;

public class Lockers {
	private Long locker;

	private Integer sequence;

	private Long cabinet;

	private Long good;

	private Boolean failure;

	private Boolean opening;

	private Date latest_sell;

	private Date latest_restocking;

	public Long getLocker() {
		return locker;
	}

	public void setLocker(Long locker) {
		this.locker = locker;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Long getCabinet() {
		return cabinet;
	}

	public void setCabinet(Long cabinet) {
		this.cabinet = cabinet;
	}

	public Long getGood() {
		return good;
	}

	public void setGood(Long good) {
		this.good = good;
	}

	public Boolean getFailure() {
		return failure;
	}

	public void setFailure(Boolean failure) {
		this.failure = failure;
	}

	public Boolean getOpening() {
		return opening;
	}

	public void setOpening(Boolean opening) {
		this.opening = opening;
	}

	public Date getLatest_sell() {
		return latest_sell;
	}

	public void setLatest_sell(Date latest_sell) {
		this.latest_sell = latest_sell;
	}

	public Date getLatest_restocking() {
		return latest_restocking;
	}

	public void setLatest_restocking(Date latest_restocking) {
		this.latest_restocking = latest_restocking;
	}
}