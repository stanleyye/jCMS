package jcms.service;

import jcms.model.User;

public interface UserService {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    User findByEmail(String email);
    User findByUsername(String username);
    void saveUser(User user);
}
