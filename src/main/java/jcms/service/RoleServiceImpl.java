package jcms.service;

import jcms.model.Role;
import jcms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepository roleRepository;

	public Role getOne(Integer id) {
		return roleRepository.getOne(id);
	}
}
