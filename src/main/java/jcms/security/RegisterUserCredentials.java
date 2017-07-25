package jcms.security;

import lombok.Data;

@Data
public class RegisterUserCredentials {
	private String name;
	private String username;
	private String email;
	private String password;
	private int roleId;
}
