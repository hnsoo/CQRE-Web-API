package sch.cqre.api.domain;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Notification", schema = "main", catalog = "")
public class NotificationEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "noti_id")
	private Long notiId;
	@Basic
	@Column(name = "receiver_id")
	private Long receiverId;
	@Basic
	@Column(name = "sender_id")
	private Long senderId;
	@Basic
	@Column(name = "noti_post")
	private Long notiPost;
	@Basic
	@Column(name = "noti_content")
	private String notiContent;
	@Basic
	@Column(name = "noti_datetime")
	private Timestamp notiDatetime;
	@Basic
	@Column(name = "whether")
	private Boolean whether;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		NotificationEntity that = (NotificationEntity)o;
		return notiId == that.notiId && receiverId == that.receiverId && senderId == that.senderId
			&& notiPost == that.notiPost
			&& Objects.equals(notiContent, that.notiContent)
			&& Objects.equals(notiDatetime, that.notiDatetime)
			&& Objects.equals(whether,
			that.whether);
	}

	@Override
	public int hashCode() {
		return Objects.hash(notiId, receiverId, senderId, notiPost, notiContent, notiDatetime,
			whether);
	}
}
