package jcms.service;

import jcms.model.User;

import java.util.List;

public interface UserService {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    List<User> findAll();
    User findByEmail(String email);
    User findByUsername(String username);
    void save(User user);
}
