package com.coolweather.app.util;

public interface HttpCallbackListhener {

	void onFinish(String response);
	
	void onError(Exception e);
	
}
