package com.gzncloud.domain;

import java.util.List;

public class Cabinets {
    private Long cabinet;

    private Short sequence;

    private Long device;

    private Integer lockers_count;

    private String description;
    
	private List<Lockers> lockers;

    public Long getCabinet() {
        return cabinet;
    }

    public void setCabinet(Long cabinet) {
        this.cabinet = cabinet;
    }

    public Short getSequence() {
        return sequence;
    }

    public void setSequence(Short sequence) {
        this.sequence = sequence;
    }

    public Long getDevice() {
        return device;
    }

    public void setDevice(Long device) {
        this.device = device;
    }

    public Integer getLockers_count() {
        return lockers_count;
    }

    public void setLockers_count(Integer lockers_count) {
        this.lockers_count = lockers_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public List<Lockers> getLockers() {
		return lockers;
	}

	public void setLockers(List<Lockers> lockers) {
		this.lockers = lockers;
	}
}