package com.hsbc.ins.rec.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {
	
	public static final int FAILURE = 0;
	
	public static final int SUCCESS = 1;
	
	public static final String UNEXPECTED_ERROR_CODE = "501";
	
	private int status;
	
	private String message;
	
	private Object result;
	
	private Object exception;
	
	private String timestamp;
	
	private String errorCode;
	
	private String correlationInd;
	
	public static JsonResult success(final String message) {
		return success(message, null);
	}
	
	public static JsonResult success(final String message, final Object data) {
		return build(SUCCESS, message, data, null);
	}
	
	public static JsonResult fail(final String message, final String errorCode) {
		return build(FAILURE, message, null, errorCode);
	}
	
	public static JsonResult fail(String message, Integer errorCode, String errorMessage) {
		return fail(message, null, errorCode, errorMessage);
	}
	
	public static JsonResult fail(String message, Object data, Integer errorCode, String errorMessage) {
		return build(FAILURE, message, data, errorCode, errorMessage);
	}
	
	public static JsonResult error(String message, String errorMsg, String uri) {
		return build(FAILURE, message, null, new ErrorResponse(uri, errorMsg), UNEXPECTED_ERROR_CODE);
	}
	
	private static JsonResult build(final int status, final String message, final Object data, final String errorCode) {
		return build(status, message, data, null, errorCode);
	}
	
	private static JsonResult build(final int status, final String message, final Object data, final Object exception, final String errorCode) {
		SimpleDateFormat forrmator = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");
		Date date = new Date();
		String timestamp = forrmator.format(date);
		return new JsonResult(status, message, data, exception, timestamp, errorCode, UUID.randomUUID().toString());
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getException() {
		return exception;
	}

	public void setException(Object exception) {
		this.exception = exception;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getCorrelationInd() {
		return correlationInd;
	}

	public void setCorrelationInd(String correlationInd) {
		this.correlationInd = correlationInd;
	}


	@NoArgsConstructor
	@AllArgsConstructor
	static class ErrorResponse{
		
		private String uri;
		
		private String errorMsg;
		
		public String getUri() {
			return uri;
		}

		public void setUri(String uri) {
			this.uri = uri;
		}

		public String getErrorMsg() {
			return errorMsg;
		}

		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}
	}
}
