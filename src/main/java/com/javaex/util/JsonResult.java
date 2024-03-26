package com.javaex.util;

public class JsonResult {

	/* 필드 */
	private String result; // "success" or "fail"
	private Object apiData; // 성공일때 데이타
	private String message; // 실패일때 실패메세지

	/* 생성자 */
	// 성공일때 생성자
	private JsonResult(Object apiData) {
		result = "success";
		this.apiData = apiData;
		message = null;
	}

	// 실패일때 생성자
	private JsonResult(String message) {
		result = "fail";
		apiData = null;
		this.message = message;
	}

	/* 메소드-getter setter
	   : set은 생성자가, getter만 있음 
	*/
	public String getResult() {
		return result;
	}

	public Object getApiData() {
		return apiData;
	}

	public String getMessage() {
		return message;
	}
	
	/* 메소드-일반 */
	// 성공일때 스태틱 new JsonResult() 하지 않고 사용
	public static JsonResult success(Object data) {
		return new JsonResult(data);
	}


	// 실패일때 스태틱 new JsonResult() 하지 않고 사용
	public static JsonResult fail(String message) {
		return new JsonResult(message);
	}
}
