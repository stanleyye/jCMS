package jcms.service;

import jcms.model.UserRole;
import jcms.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Cacheable(value = "user_role", key = "#username", unless = "#result == null")
	public UserRole findByForeignKeyUserUsername(String username) {
		return userRoleRepository.findByForeignKeyUserUsername(username);
	}

	public UserRole save(UserRole userRole) {
		return userRoleRepository.save(userRole);
	}
}
