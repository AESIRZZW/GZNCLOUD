package com.gzncloud.domain;

import java.util.Arrays;
import java.util.List;

public class Devices {
	private Long device;

    @Override
	public String toString() {
		return "Devices [device=" + device + ", user=" + user + ", team=" + team + ", status=" + status + ", led_width="
				+ led_width + ", led_height=" + led_height + ", action_type=" + action_type + ", action_speed="
				+ action_speed + ", action_border=" + action_border + ", content_text=" + content_text
				+ ", content_status=" + content_status + ", server_feature=" + server_feature + ", wechat_enabled="
				+ wechat_enabled + ", alipay_enabled=" + alipay_enabled + ", content_matrix="
				+ Arrays.toString(content_matrix) + ", cabinets_count=" + cabinets_count + ", cabinets=" + cabinets
				+ "]";
	}

	private Long user;

    private Long team;

    private String status;

    private Integer led_width;

    private Integer led_height;

    private Short action_type;

    private Short action_speed;

    private Short action_border;

    private String content_text;

    private Short content_status;

    private String server_feature;

    private Boolean wechat_enabled;

    private Boolean alipay_enabled;

    private byte[] content_matrix;
    
    private Integer cabinets_count;
    
    private List<Cabinets> cabinets;

    public Integer getCabinets_count() {
		return cabinets_count;
	}

	public void setCabinets_count(Integer cabinets_count) {
		this.cabinets_count = cabinets_count;
	}

	public Long getDevice() {
        return device;
    }

    public void setDevice(Long device) {
        this.device = device;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getTeam() {
        return team;
    }

    public void setTeam(Long team) {
        this.team = team;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getLed_width() {
        return led_width;
    }

    public void setLed_width(Integer led_width) {
        this.led_width = led_width;
    }

    public Integer getLed_height() {
        return led_height;
    }

    public void setLed_height(Integer led_height) {
        this.led_height = led_height;
    }

    public Short getAction_type() {
        return action_type;
    }

    public void setAction_type(Short action_type) {
        this.action_type = action_type;
    }

    public Short getAction_speed() {
        return action_speed;
    }

    public void setAction_speed(Short action_speed) {
        this.action_speed = action_speed;
    }

    public Short getAction_border() {
        return action_border;
    }

    public void setAction_border(Short action_border) {
        this.action_border = action_border;
    }

    public String getContent_text() {
        return content_text;
    }

    public void setContent_text(String content_text) {
        this.content_text = content_text == null ? null : content_text.trim();
    }

    public Short getContent_status() {
        return content_status;
    }

    public void setContent_status(Short content_status) {
        this.content_status = content_status;
    }

    public String getServer_feature() {
        return server_feature;
    }

    public void setServer_feature(String server_feature) {
        this.server_feature = server_feature == null ? null : server_feature.trim();
    }

    public Boolean getWechat_enabled() {
        return wechat_enabled;
    }

    public void setWechat_enabled(Boolean wechat_enabled) {
        this.wechat_enabled = wechat_enabled;
    }

    public Boolean getAlipay_enabled() {
        return alipay_enabled;
    }

    public void setAlipay_enabled(Boolean alipay_enabled) {
        this.alipay_enabled = alipay_enabled;
    }

    public byte[] getContent_matrix() {
        return content_matrix;
    }

    public void setContent_matrix(byte[] content_matrix) {
        this.content_matrix = content_matrix;
    }

	public List<Cabinets> getCabinets() {
		return cabinets;
	}

	public void setCabinets(List<Cabinets> cabinets) {
		this.cabinets = cabinets;
	}
}