package jcms.service;

import jcms.model.User;

public interface UserService {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    User findByName(String name);
    User findByEmail(String email);
    void saveUser(User user);

}
