package com.gzncloud.domain;

public class Cabinets {
    private Long cabinet;

    private Short sequence;

    private Long device;

    private Integer lockers_count;

    private String description;

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
}