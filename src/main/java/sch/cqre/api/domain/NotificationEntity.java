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

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Notification", schema = "main", catalog = "")
public class NotificationEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "not_id")
	private Long notId;
	@Basic(optional = false)
	@Column(name = "receiver_id")
	private Long receiverId;
	@Basic(optional = false)
	@Column(name = "sender_id")
	private Long senderId;
	@Basic(optional = false)
	@Column(name = "not_type")
	private String notType;
	@Basic(optional = false)
	@Column(name = "not_post")
	private Long notPost;
	@Basic(optional = false)
	@Column(name = "not_content")
	private String notContent;
	@Basic(optional = false)
	@Column(name = "not_url")
	private String notUrl;
	@Basic(optional = false)
	@Column(name = "not_datetime")
	private Timestamp notDatetime;
	@Basic(optional = false)
	@Column(name = "not_read_datetime")
	private Timestamp notReadDatetime;
	@Basic(optional = false)
	@Column(name = "read_or_not")
	private Integer readOrNot;

	@Builder
	public NotificationEntity(Long receiverId, Long senderId, String notType, Long notPost,
		String notContent, String notUrl, Timestamp notDatetime, Timestamp notReadDatetime, Integer readOrNot) {
		this.receiverId = receiverId;
		this.senderId = senderId;
		this.notType = notType;
		this.notPost = notPost;
		this.notContent = notContent;
		this.notUrl = notUrl;
		this.notDatetime = notDatetime;
		this.notReadDatetime = notReadDatetime;
		this.readOrNot = readOrNot;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		NotificationEntity that = (NotificationEntity)o;
		return notId == that.notId && receiverId == that.receiverId && senderId == that.senderId
			&& notPost == that.notPost
			&& Objects.equals(notType, that.notType) && Objects.equals(notContent, that.notContent)
			&& Objects.equals(notUrl, that.notUrl) && Objects.equals(notDatetime, that.notDatetime)
			&& Objects.equals(notReadDatetime, that.notReadDatetime) && Objects.equals(readOrNot,
			that.readOrNot);
	}

	@Override
	public int hashCode() {
		return Objects.hash(notId, receiverId, senderId, notType, notPost, notContent, notUrl, notDatetime,
			notReadDatetime,
			readOrNot);
	}
}
