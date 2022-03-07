package sch.cqre.api.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@DynamicInsert
//@Cacheable
@Table(name = "User", schema = "main")
public class UserEntity {


	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "user_id")
	private long userId;



	@Basic(optional = false)
	@Column(name = "student_id")
	private int studentId;
	@Basic(optional = false)
//	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password")
//	@JsonIgnore
	private String password;
	@Basic()
	@Column(name = "email", unique = true)
	private String email;
	@Basic(optional = false)
	@Column(name = "nickname")
	private String nickname;

	@Basic
	@Column(name = "user_type") // nullable, signup때 포함하지 않을 것임
	//@Enumerated(EnumType.STRING)
	//private String userType; // defaultValue = "Guest"
	private String role;



	//@OneToOne()
	//@JoinColumn(name = "User_user_id",)
	//private UserEntity user;

	@Builder
	public UserEntity(long userId, int studentId, String password, String email, String nickname, String role){
	//	String profile) {
		this.studentId = studentId;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.role = role;
	//	this.profile = profile;
	}

}
