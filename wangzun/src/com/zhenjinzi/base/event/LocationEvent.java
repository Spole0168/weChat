package com.zhenjinzi.base.event;

import com.zhenjinzi.base.BaseEvent;

public class LocationEvent extends BaseEvent{
//   地理位置维度
	private String Latitude;
//	p地理位置经度
	private String Longitude;
//	地理位置精度
	private String Precision;
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
	
}
