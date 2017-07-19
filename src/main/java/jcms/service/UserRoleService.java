package jcms.service;

import jcms.model.UserRole;

public interface UserRoleService {
	UserRole findByForeignKeyUsername(String username);
}
