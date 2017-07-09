package jcms.error;

import java.util.Map;

/*
 * Used to display error details when debug mode is on (specified in the config files)
 * Based off of Joni Karppinen's Github gist (https://gist.github.com/jonikarppinen/662c38fb57a23de61c8b)
 */
public class DebugError {
	private String error;
	private String message;
	private Integer httpStatus;
	private String timestamp;
	private String trace;

	public DebugError(int httpStatus, Map<String, Object> errorAttributes) {
		this.httpStatus = httpStatus;
		this.error = (String) errorAttributes.get("error");
		this.message = (String) errorAttributes.get("message");
		this.timestamp = errorAttributes.get("timestamp").toString();
		this.trace = (String) errorAttributes.get("trace");
	}
}
