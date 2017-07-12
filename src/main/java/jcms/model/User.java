package jcms.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * User DAO.
 */

@Data
@RequiredArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(name ="creation_date", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "email")
    @Email(message = "Email is invalid.")
    @NonNull
    private String email;

    @Column(name = "password")
    @Length(min = 8, message = "Your password must contain at least 8 characters.")
    @NonNull
    private String password;
}
