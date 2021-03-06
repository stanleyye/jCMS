package jcms.service;

import jcms.model.Role;
import jcms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepository roleRepository;

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Role getOne(Integer id) {
		return roleRepository.getOne(id);
	}
}
