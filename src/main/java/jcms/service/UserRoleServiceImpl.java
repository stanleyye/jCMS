package jcms.service;

import jcms.model.UserRole;
import jcms.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	private UserRoleRepository userRoleRepository;

	public UserRole findByForeignKeyUserUsername(String username) {
		return userRoleRepository.findByForeignKeyUserUsername(username);
	}
}
