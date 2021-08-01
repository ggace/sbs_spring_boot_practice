package com.min.sbs.dto;

import lombok.Getter;

public class ResultData {
	@Getter
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private Object data;

	private ResultData() {
		// TODO Auto-generated constructor stub
	}

	public static ResultData from(String resultCode, String msg, Object data) {
		ResultData resultData = new ResultData();
		resultData.resultCode = resultCode;
		resultData.msg = msg;
		resultData.data = data;

		return resultData;
	}

	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}

	public boolean isFail() {
		return !isSuccess();
	}

	public static ResultData from(String resultCode, String msg) {
		return from(resultCode, msg, null);
	}

	public static ResultData newData(ResultData rd, Object obj) {
		return from(rd.resultCode, rd.msg, obj);
	}
}
