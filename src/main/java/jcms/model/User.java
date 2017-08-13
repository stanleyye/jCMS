package jcms.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * User DAO.
 */

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User implements Serializable {
	@JsonProperty
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonIgnore
	@Column(name ="creation_date", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@JsonProperty
	@Column(name = "name")
	@NonNull
	private String name;

    @JsonProperty
    @Column(name = "username")
    @NonNull
    private String username;

    @JsonProperty
    @Column(name = "email")
    @Email(message = "Email is invalid.")
    @NonNull
    private String email;

    @Column(name = "password")
    @Length(min = 8, message = "Your password must contain at least 8 characters.")
    @NonNull
    private String password;

    @JsonIgnore
	public String getPassword() {
    	return this.password;
	}

    @JsonProperty
    public void setPassword(String password) {
    	this.password = password;
	}
}
