package jcms.service;

import jcms.model.UserRole;

public interface UserRoleService {
	UserRole findByForeignKeyUserUsername(String username);
	UserRole save(UserRole userRole);
}
