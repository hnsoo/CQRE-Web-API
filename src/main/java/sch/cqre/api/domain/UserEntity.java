package sch.cqre.api.domain;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Entity
@NoArgsConstructor
@DynamicInsert
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
	// @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password")
	private String password;
	@Basic(optional = false)
	@Column(name = "email", unique = true)
	private String email;
	@Basic(optional = false)
	@Column(name = "nickname")
	private String nickname;
	@Basic
	@Column(name = "user_type") // nullable, signup때 포함하지 않을 것임
	private String userType; // defaultValue = "Guest"
	@Basic(optional = false)
	@Column(name = "profile")
	private String profile;
	@Basic // nullable, 자동으로 들어감
 	@Column(name = "provider", length = 45) // social login
	private String provider; // defaultValue = "local"

	@Builder
	public UserEntity(Long userId, Long studentId, String password, String email, String nickname, String userType,
		String profile) {
		this.studentId = studentId;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.userType = userType;
		this.profile = profile;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserEntity that = (UserEntity)o;
		((UserEntity)o).setUserType("Guest");
		return userId == that.userId && studentId == that.studentId && Objects.equals(password, that.password)
			&& Objects.equals(email, that.email) && Objects.equals(nickname, that.nickname)
			&& Objects.equals(userType, that.userType) && Objects.equals(profile, that.profile);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, studentId, password, email, nickname, userType, profile);
	}
}
