package jcms.security;

import jcms.model.Role;
import jcms.model.User;
import jcms.service.RoleService;
import jcms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) {
		final User user = userService.findByUsername(username);
		if (user == null) {
		  throw new UsernameNotFoundException(username);
		}

		return new org.springframework.security.core.userdetails.User(
		  user.getUsername(),
		  user.getPassword(),
		  getAuthorities()
		);
	}

	private Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> listOfAuthorities = new ArrayList<>();
		List<Role> listOfRoles = roleService.findAll();

		// For each role, add them as an authority
		for (Role role : listOfRoles) {
			System.out.println(role.getRoleName());
			listOfAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return listOfAuthorities;
	}
}
