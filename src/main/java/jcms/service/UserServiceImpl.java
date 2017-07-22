package jcms.service;

import jcms.model.User;
import jcms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public List<User> findAll() {
    	return userRepository.findAll();
	}

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User removeById(Integer id) {
    	return userRepository.removeById(id);
	};

    public User save(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
