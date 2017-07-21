@Service
public class MyUserDetailsService implements UserDetailsService {
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
      getAuthorities(user)
    );
  }
}
