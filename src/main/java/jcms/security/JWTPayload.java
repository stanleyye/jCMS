package jcms.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTPayload {
	private String username;
	private Integer roleLevel;
}
