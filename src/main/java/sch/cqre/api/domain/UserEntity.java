package sch.cqre.api.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private Long userId;
	@Basic(optional = false)
	@Column(name = "student_id")
	private Long studentId;
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
	public UserEntity(Long userId, Long studentId, String password, String email, String nickname, String role){
	//	String profile) {
		this.studentId = studentId;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.role = role;
	//	this.profile = profile;
	}

}
