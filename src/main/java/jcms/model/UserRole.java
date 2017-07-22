package jcms.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user_role")
public class UserRole implements Serializable {
	@Id
	@Column(name = "id")
	private int id;

	@NonNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@NonNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;
}
