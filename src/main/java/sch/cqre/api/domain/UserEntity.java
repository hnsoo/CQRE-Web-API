package sch.cqre.api.domain;

import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sch.cqre.api.jwt.Role;

@Data
@Entity
@NoArgsConstructor
@DynamicInsert
@Table(name = "User", schema = "main")
public class UserEntity {


	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "user_id")
	private int userId;
	@Basic(optional = false)
	@Column(name = "student_id")
	private int studentId;
	@Basic(optional = false)
	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password")
	@JsonIgnore
	private String password;
	@Basic(optional = false)
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

	//엔티티 이름 변경

	//@Basic(optional = false)
	//@Column(name = "profile")
	//private String profile;
	//@Basic // nullable, 자동으로 들어감
 	//@Column(name = "provider", length = 45) // social login
	//private String provider; // defaultValue = "local"

	@Builder
	public UserEntity(int userId, int studentId, String password, String email, String nickname, String role){
	//	String profile) {
		this.studentId = studentId;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.role = role;
	//	this.profile = profile;
	}

	/*
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserEntity that = (UserEntity)o;
		((UserEntity)o).setUserType("guest");
		return userId == that.userId && studentId == that.studentId && Objects.equals(password, that.password)
			&& Objects.equals(email, that.email) && Objects.equals(nickname, that.nickname)
			&& Objects.equals(userType, that.userType) ; //&& Objects.equals(profile, that.profile);
	}*/

	@Override
	public int hashCode() {
		return Objects.hash(userId, studentId, password, email, nickname, role); // profile);
	}
}
