package jcms.model;

import javax.persistence.*;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * User DAO.
 * TODO: Keep track of user meta data.
 */

@Data
@Entity
@Table(name="users")
public class User {
    private @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    @Email(message = "Email is invalid.")
    private String email;

    @Column(name = "password")
    @Length(min = 8, message = "Your password must contain at least 8 characters.")
    private String password;

    @Column(name = "userRole")
    private Role role;
}
