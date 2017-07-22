package jcms.service;

import jcms.model.Role;

import java.util.List;

public interface RoleService {
	List<Role> findAll();
	Role getOne(Integer id);
}
