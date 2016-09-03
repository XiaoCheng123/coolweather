package com.coolweather.app.util;

import java.util.concurrent.ConcurrentHashMap;

import android.R.integer;
import android.R.string;
import android.text.TextUtils;

import com.coolweather.app.modei.City;
import com.coolweather.app.modei.CoolWeatherDB;
import com.coolweather.app.modei.County;
import com.coolweather.app.modei.Province;

public class Utility {

	/**
	 * �����ʹ�����������ص�ʡ������ 
	 */
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response){
		if (!TextUtils.isEmpty(response)) {
			String[] allProvicnes = response.split(",");
			for (String p : allProvicnes) {
				String[] array = p.split("\\|");
				Province province = new Province();
				province.setProvinceCode(array[0]);
				province.setProvinceName(array[1]);
				//��������������ݴ洢��Province��
				coolWeatherDB.savePronvince(province);
			}
			return true;
		}
		return false;
	}
	/**
	 * �����ʹ�����������ص��м����� 
	 */
	public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId){
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length >0) {
				for (String c : allCities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					//���������������ݴ洢��County��
					coolWeatherDB.saveCity(city);
				}
			}
			return true;
		}
		return false;
	}
	/**
	 * �����ʹ�����������ص��ؼ����� 
	 */
	public synchronized static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId){
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length >0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					//���������������ݴ洢��County��
					coolWeatherDB.saveCounty(county);
				}
			}
			
			return true;
		}
		return false;
	}
}
