package jcms.service;

import jcms.model.User;

public interface UserService {
    public User findByName(String name);
    public User findByEmail(String email);
    public void saveUser(User user);
}
