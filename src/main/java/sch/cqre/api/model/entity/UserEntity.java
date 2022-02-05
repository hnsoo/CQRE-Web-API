package sch.cqre.api.model.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User", schema = "main")
public class UserEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "user_id")
	private int userId;
	@Basic
	@Column(name = "student_id")
	private int studentId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserEntity that = (UserEntity)o;
		return userId == that.userId && studentId == that.studentId && Objects.equals(password, that.password)
			&& Objects.equals(email, that.email) && Objects.equals(nickname, that.nickname)
			&& Objects.equals(userType, that.userType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, studentId, password, email, nickname, userType);
	}
}
