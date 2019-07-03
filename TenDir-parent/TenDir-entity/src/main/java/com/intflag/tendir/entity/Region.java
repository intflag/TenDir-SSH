package com.intflag.tendir.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * 全国地区实体
 */
@SuppressWarnings("rawtypes")
public class Region implements java.io.Serializable {

	private static final long serialVersionUID = 5379076914747482582L;
	private String regionId;
	private Region region;
	private String regionName;
	private String regionFullName;
	private Integer regionLevel;
	private String regionFlag;
	private BigDecimal locationLat;// 纬度
	private BigDecimal locationLng;// 经度

	private Set children = new HashSet(0);

	public String getResourceId() {
		return this.regionId;
	}

	public String getResname() {
		return this.regionName;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionFullName() {
		return regionFullName;
	}

	public void setRegionFullName(String regionFullName) {
		this.regionFullName = regionFullName;
	}

	public Integer getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(Integer regionLevel) {
		this.regionLevel = regionLevel;
	}

	public String getRegionFlag() {
		return regionFlag;
	}

	public void setRegionFlag(String regionFlag) {
		this.regionFlag = regionFlag;
	}

	public BigDecimal getLocationLat() {
		return locationLat;
	}

	public void setLocationLat(BigDecimal locationLat) {
		this.locationLat = locationLat;
	}

	public BigDecimal getLocationLng() {
		return locationLng;
	}

	public void setLocationLng(BigDecimal locationLng) {
		this.locationLng = locationLng;
	}

	public Set getChildren() {
		return children;
	}

	public void setChildren(Set children) {
		this.children = children;
	}

}