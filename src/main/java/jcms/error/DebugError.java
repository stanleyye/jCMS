package jcms.error;

import java.util.Map;

/*
 * Used to display error details when debug mode is on (specified in the config files)
 * Based off of Joni Karppinen's Github gist (https://gist.github.com/jonikarppinen/662c38fb57a23de61c8b)
 */
public class DebugError {
	public String error;
	public String message;
	public Integer httpStatus;
	public String timestamp;
	public String trace;

	public DebugError(int httpStatus, Map<String, Object> errorAttributes) {
		this.httpStatus = httpStatus;
		this.error = (String) errorAttributes.get("error");
		this.message = (String) errorAttributes.get("message");
		this.timestamp = errorAttributes.get("timestamp").toString();
		this.trace = (String) errorAttributes.get("trace");
	}
}
