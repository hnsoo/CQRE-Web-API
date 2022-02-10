package sch.cqre.api.domain;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "User", schema = "main")
public class UserEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "user_id")
	private Long userId;
	@Basic
	@Column(name = "student_id")
	private Long studentId;
	@Basic
	@Column(name = "password")
	private String password;
	@Basic
	@Column(name = "email")
	private String email;
	@Basic
	@Column(name = "nickname")
	private String nickname;
	@Basic
	@Column(name = "user_type")
	private String userType;
	@Basic
	@Column(name = "profile")
	private String profile;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserEntity that = (UserEntity)o;
		return userId == that.userId && studentId == that.studentId && Objects.equals(password, that.password)
			&& Objects.equals(email, that.email) && Objects.equals(nickname, that.nickname)
			&& Objects.equals(userType, that.userType) && Objects.equals(profile, that.profile);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, studentId, password, email, nickname, userType, profile);
	}
}
